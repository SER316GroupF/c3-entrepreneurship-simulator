package edu.asu.c3simulator.simulation.business;

/**
 * Structure to represent how, and in what quantities a {@link Product} is being produced.
 * 
 * @author Moore, Zachary
 *
 */
public class ProductionInformation
{
	/** Product for which this information applies */
	private Product target;
	
	/** Number of units of {@link #target} being produced by this company each month */
	private int monthlyProduction;
	
	/** Amount spent on materials per unit produced */
	private float materialBudgetPerUnit;
	
	/**
	 * @param target
	 *            Product for which this information applies. Must be non-null
	 * @param monthlyProduction
	 *            Number of units of {@link #target} to be produced by this company each
	 *            month. A negative monthlyProduction indicates that the company is
	 *            destroying units in their inventory.
	 * @param materialBudgetPerUnit
	 *            Amount spent on materials per unit produced. Must be >= 0
	 */
	public ProductionInformation(Product target, int monthlyProduction,
			float materialBudgetPerUnit)
	{
		super();
		
		validateParameters(target, monthlyProduction, materialBudgetPerUnit);
		
		this.target = target;
		this.monthlyProduction = monthlyProduction;
		this.materialBudgetPerUnit = materialBudgetPerUnit;
	}
	
	private void validateParameters(Product target, int monthlyProduction,
			float materialBudgetPerUnit)
	{
		if (target == null)
		{
			throw new IllegalArgumentException("Product must be non-null");
		}
		else if (materialBudgetPerUnit < 0)
		{
			throw new IllegalArgumentException("Budget cannot be negative");
		}
	}
	
	public int getMonthlyProduction()
	{
		return monthlyProduction;
	}
	
	public void increaseMonthlyProduction(int amount)
	{
		monthlyProduction += amount;
	}
	
	public void setMonthlyProduction(int monthlyProduction)
	{
		this.monthlyProduction = monthlyProduction;
	}
	
	public float getMaterialBudgetPerUnit()
	{
		return materialBudgetPerUnit;
	}
	
	public void increaseMaterialBudgetPerUnit(int amount)
	{
		materialBudgetPerUnit += amount;
	}
	
	public void setMaterialBudgetPerUnit(float materialBudgetPerUnit)
	{
		this.materialBudgetPerUnit = materialBudgetPerUnit;
	}
	
	public Product getTarget()
	{
		return target;
	}
	
	public float getTotalMonthlyMaterialExpense()
	{
		return monthlyProduction * materialBudgetPerUnit;
	}
	
}
