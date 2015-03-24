package edu.asu.c3simulator.simulation.business;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Data object to store data related to, and simulate conduct by a Company. In this model,
 * a company is made up of its employees, assets (including capital, credit, inventory,
 * and other non-liquid assets), {@link ConsumerConnection}s, products, and activity (e.g.
 * marketing campaigns, purchase orders, etc).
 * <p>
 * NOTE: {@link Company} assumes that instances of {@link Product} are unique, and specify
 * a type of product rather than a single manufactured unit.
 * 
 * @author Moore, Zachary
 *
 */
public class Company
{
	private String name;
	private List<Employee> employees;
	
	/**
	 * Products currently manufactured and sold by this company, paired with the
	 * production information about them. If a product is sold but not manufactured, the
	 * associated value will be null.
	 */
	private HashMap<Product, ProductionInformation> productLine;
	
	private HashMap<Product, Integer> inventory;
	private HashMap<Product, Integer> salesInCurrentMonth;
	private HashMap<Product, Float> retailPrice;
	private Credit credit;
	private List<MarketingCampaign> activeMarketing;
	private List<ConsumerConnection> salesOutlets;
	
	private HashMap<Product, Integer> publicOpinionOfProducts;
	private int publicOpinionOfCompany;
	
	/**
	 * Simulates the sale of a product, by calculating profits and adding it to the
	 * company's current capital.
	 * <p>
	 * This method is intended to be called by Simulation and its agents, as part of a
	 * market/economy simulation.
	 * 
	 * @param product
	 *            The product to be bought/sold
	 * @param quantity
	 *            The quantity to buy/sell
	 * @return True if the sale was successful, false if the sale was unable to be
	 *         processed. Note, a false return is indicitive of the quantity being too
	 *         high for this company to support a sale. For instance, the quantity may
	 *         cause the company to exceed its monthly sales potential, as specified by
	 *         {@link #getMonthlySalePotential(Product)}
	 */
	public boolean registerSale(Product product, int quantity)
	{
		int salesInCurrentMonth = getThisMonthsSalesOf(product) + quantity;
		
		if (salesInCurrentMonth > getMonthlySalePotential(product))
		{
			return false;
		}
		else
		{
			float grossProfit = getRetailPriceOf(product) * quantity;
			credit.processGrossProfit(grossProfit);
			this.salesInCurrentMonth.put(product, salesInCurrentMonth);
			return true;
		}
	}
	
	/**
	 * If {@link #salesInCurrentMonth} does not contain the specified product, the product
	 * will be added to the map and initialized with a value of 0, and that value will be
	 * returned.
	 * <p>
	 * Otherwise, the corresponding value in {@link #salesInCurrentMonth} will be
	 * returned.
	 */
	private int getThisMonthsSalesOf(Product product)
	{
		if (salesInCurrentMonth.containsKey(product))
		{
			return salesInCurrentMonth.get(product);
		}
		else
		{
			salesInCurrentMonth.put(product, 0);
			return 0;
		}
	}
	
	/**
	 * Equivalent to {@link #retailPrice}, but handles the case in which
	 * {@link #retailPrice} is not registered for the given product.
	 * <p>
	 * As of version 0.1, all products must register a retail price, so an
	 * {@link IllegalStateException} is thrown if {@link #retailPrice} does not contain
	 * the given product. However, this functionality may be changed in future revisions
	 * and is subject to change.
	 */
	private float getRetailPriceOf(Product product)
	{
		if (retailPrice.containsKey(product))
		{
			return retailPrice.get(product);
		}
		else
		{
			throw new IllegalStateException(
					"Retail price must be registered for all products");
		}
	}
	
	/**
	 * Register all expenses, process production (as specified by
	 * {@link #processProduction()}), and update {@link #publicOpinionOfProducts} and
	 * {@link #publicOpinionOfCompany} as well as all campaigns in
	 * {@link #activeMarketing}
	 */
	public void processMonth()
	{
		processProduction();
		float expense = getMonthlyEmployeeExpense() + getMonthlyMarketingExpense();
		credit.processPayment(expense);
		
		List<MarketingCampaign> completedCampaigns = new LinkedList<>();
		for (MarketingCampaign campaign : activeMarketing)
		{
			Product product = campaign.getTarget();
			int publicityGain = campaign.processMonth();
			
			if (product == null)
			{
				publicOpinionOfCompany += publicityGain;
			}
			else
			{
				int newPublicOpionion = getPublicOpinionOf(product) + publicityGain;
				publicOpinionOfProducts.put(product, newPublicOpionion);
			}
			
			if (campaign.isFinished())
			{
				completedCampaigns.add(campaign);
			}
		}
		
		activeMarketing.removeAll(completedCampaigns);
		salesInCurrentMonth.clear();
	}
	
	/**
	 * Pay the production costs associated with all monthly production specified by
	 * {@link #productLine} and
	 * {@link ProductionInformation#getTotalMonthlyMaterialExpense()}, then update the
	 * {@link #inventory} to include this month's production.
	 */
	private void processProduction()
	{
		for (ProductionInformation productionInformation : productLine.values())
		{
			float cost = productionInformation.getTotalMonthlyMaterialExpense();
			credit.processPayment(cost);
			
			Product product = productionInformation.getTarget();
			int productionAmount = productionInformation.getMonthlyProduction();
			int newInventory = getInventoryOf(product) + productionAmount;
			inventory.put(product, newInventory);
		}
	}
	
	/**
	 * If {@link #inventory} does not contain the specified product, the product will be
	 * added to the map and initialized with a value of 0, and that value will be
	 * returned.
	 * <p>
	 * Otherwise, the corresponding value in {@link #inventory} will be returned.
	 */
	private int getInventoryOf(Product product)
	{
		if (inventory.containsKey(product))
		{
			return inventory.get(product);
		}
		else
		{
			inventory.put(product, 0);
			return 0;
		}
	}
	
	/**
	 * If {@link #publicOpinionOfProducts} does not contain the specified product, the
	 * product will be added to the map and initialized with a value equal to
	 * {@link #publicOpinionOfCompany}, and that value will be returned.
	 * <p>
	 * Otherwise, the corresponding value in {@link #publicOpinionOfProducts} will be
	 * returned.
	 */
	private int getPublicOpinionOf(Product product)
	{
		if (publicOpinionOfProducts.containsKey(product))
		{
			return publicOpinionOfProducts.get(product);
		}
		else
		{
			publicOpinionOfProducts.put(product, publicOpinionOfCompany);
			return publicOpinionOfCompany;
		}
	}
	
	/**
	 * @return The total compensation of all employees in {@link #employees}
	 */
	private float getMonthlyEmployeeExpense()
	{
		float expense = 0;
		
		for (Employee employee : employees)
		{
			expense += employee.getTotalMonthlyCompensation();
		}
		
		return expense;
	}
	
	/**
	 * @return The total of all marketing expenses specified in {@link #activeMarketing}
	 */
	private float getMonthlyMarketingExpense()
	{
		float total = 0;
		
		for (MarketingCampaign campaign : activeMarketing)
		{
			total += campaign.getMonthlyCost();
		}
		
		return total;
	}
	
	/**
	 * Calculates the maximum number of sales a company can process of the given product
	 * at the current point in time. This differs from
	 * {@link #getMonthlySalePotential(Product)} in that this method returns how many
	 * units are ready to be sold, at the current point in time, whereas
	 * {@link #getSaleCapacity(Product)} returns how many units can be handled by this
	 * company's {@link ConsumerConnection}s.
	 * <p>
	 * Generally, a sale cannot be made unless it is below the remainingSalePotential, AND
	 * less than or equal to the saleCapacity of that product.
	 * 
	 * @see #getMonthlySalePotential(Product)
	 * @param product
	 *            Product for which the sale capacity will be calculated
	 * @return The number of units of the specified product that are ready to be sold at
	 *         the current point in time
	 */
	public int getSaleCapacity(Product product)
	{
		if (inventory.containsKey(product))
		{
			return inventory.get(product);
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * Calculates the maximum number of sales a company can process of the given product
	 * in a single month. This differs from {@link #getSaleCapacity(Product)} in that this
	 * method returns how many units can be handled by this company's
	 * {@link ConsumerConnection}s, whereas {@link #getSaleCapacity(Product)} returns how
	 * many units are ready to be sold, at the current point in time.
	 * <p>
	 * Generally, a sale cannot be made unless it is below the remainingSalePotential, AND
	 * less than or equal to the saleCapacity of that product.
	 * 
	 * @see #getSaleCapacity(Product)
	 * @param product
	 *            Product for which the sale potential will be calculated
	 * @return The maximum number of units of the specified product that can be sold by
	 *         this company in a single month
	 */
	public int getMonthlySalePotential(Product product)
	{
		int potential = 0;
		
		for (ConsumerConnection outlet : salesOutlets)
		{
			potential += outlet.getMonthlySalesPotential(product);
		}
		
		return potential;
	}
	
	/**
	 * @param product
	 *            Product for which the remaining sale potential will be calculated
	 * @return How many more units of the specified product can be sold by this company in
	 *         the current month
	 */
	public int getRemainingSalePotential(Product product)
	{
		int potential = getMonthlySalePotential(product);
		int used = salesInCurrentMonth.containsKey(product) ? salesInCurrentMonth
				.get(product) : 0;
		
		return potential - used;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<Employee> getEmployees()
	{
		return employees;
	}
	
	public HashMap<Product, ProductionInformation> getProductLine()
	{
		return productLine;
	}
	
	public HashMap<Product, Integer> getInventory()
	{
		return inventory;
	}
	
	public HashMap<Product, Integer> getSalesInCurrentMonth()
	{
		return salesInCurrentMonth;
	}
	
	public HashMap<Product, Float> getRetailPrice()
	{
		return retailPrice;
	}
	
	public Credit getCredit()
	{
		return credit;
	}
	
	public List<MarketingCampaign> getActiveMarketing()
	{
		return activeMarketing;
	}
	
	public List<ConsumerConnection> getSalesOutlets()
	{
		return salesOutlets;
	}
	
	public HashMap<Product, Integer> getPublicOpinionOfProducts()
	{
		return publicOpinionOfProducts;
	}
	
	public int getPublicOpinionOfCompany()
	{
		return publicOpinionOfCompany;
	}
	
}
