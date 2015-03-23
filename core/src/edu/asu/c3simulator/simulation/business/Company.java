package edu.asu.c3simulator.simulation.business;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Company
{
	private List<Employee> employees;
	private HashMap<Product, ProductionInformation> productLine;
	private HashMap<Product, Integer> inventory;
	private HashMap<Product, Integer> salesInCurrentMonth;
	private HashMap<Product, Float> retailPrice;
	private Credit credit;
	private List<MarketingCampaign> activeMarketing;
	private List<ConsumerConnection> salesOutlets;
	
	private HashMap<Product, Integer> publicOpinionOfProducts;
	private int publicOpinionOfCompany;
	
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
	
	private float getMonthlyEmployeeExpense()
	{
		float expense = 0;
		
		for (Employee employee : employees)
		{
			expense += employee.getTotalMonthlyCompensation();
		}
		
		return expense;
	}
	
	private float getMonthlyMarketingExpense()
	{
		float total = 0;
		
		for (MarketingCampaign campaign : activeMarketing)
		{
			total += campaign.getMonthlyCost();
		}
		
		return total;
	}
	
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
	
	public int getMonthlySalePotential(Product product)
	{
		int potential = 0;
		
		for (ConsumerConnection outlet : salesOutlets)
		{
			potential += outlet.getMonthlySalesPotential(product);
		}
		
		return potential;
	}
	
	public int getRemainingSalePotential(Product product)
	{
		int potential = getMonthlySalePotential(product);
		int used = salesInCurrentMonth.containsKey(product) ? salesInCurrentMonth
				.get(product) : 0;
		
		return potential - used;
	}
	
}
