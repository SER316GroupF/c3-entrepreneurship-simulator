package edu.asu.c3simulator.simulation.business;

/**
 * Structure to represent how, and in what quantities a {@link Product} is being produced.
 * 
 * @author Moore, Zachary
 *
 */
public class ProductionInformation
{
	private Product target;
	private int monthlyProduction;
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
