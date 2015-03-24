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
