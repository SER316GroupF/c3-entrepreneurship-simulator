package edu.asu.c3simulator.simulation;

import edu.asu.c3simulator.testing.stubs.SimulationTestingStub;

/**
 * The Employee class is used to store all of the attributes that an employee should have.
 * It is also used to randomly generate these attributes to apply to a list of employees
 * available for hire.
 * 
 * Assumes employee can only be hired by one company at a time.
 * 
 * @author nickkrogstad
 * 
 */
public class Employee
{
	public static enum Position
	{
		MANAGER,
		PRODUCT_DESIGNER,
		SALES_ASSOCIATE,
		MARKETING,
		UNEMPLOYED;
	}
	
	private String name;
	private Position position;
	private int actualHourlyWage;
	private int netEarnings;
	private float averageAnnualRaise;
	private float averageAnnualBonus;
	private int netBonuses;
	
	/** Largest pay difference an employee will take before quitting, as a percentage. */
	private float wageTolerance;
	
	/** An employee's ambition to be paid higher based on their position. */
	private float ambition;
	
	public Employee(String name, Position position, float wageTolerance, float ambition)
	{
		this.name = name;
		this.position = position;
		this.wageTolerance = wageTolerance;
		this.ambition = ambition;
	}
	
	/**
	 * @return A string used to display only the name and pay of an employee for the
	 *         Employee Panel
	 */
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append(name);
		string.append(" ($");
		string.append(actualHourlyWage);
		string.append(" / hr)");
		
		return string.toString();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Position getPosition()
	{
		return position;
	}
	
	public void setPosition(Position position)
	{
		this.position = position;
	}
	
	public int getActualHourlyWage()
	{
		return actualHourlyWage;
	}
	
	public void setActualHourlyWage(int actualHourlyWage)
	{
		this.actualHourlyWage = actualHourlyWage;
	}
	
	/**
	 * Employee Morale is determined by the difference between the employees preferred
	 * hourly wage and their actual hourly wage, and affected by their wage tolerance. A
	 * higher wage tolerance means they will generally maintain a higher moral, and will
	 * not be affected as intensely by their actual wage being less than their preferred
	 * wage. If the employees morale reaches 0 then they will quit and be removed from the
	 * company's active roster of employees.
	 * 
	 * @return Morale expressed as a percentage between 0 and 1
	 */
	public float getMorale()
	{
		float maxPayDelta = (actualHourlyWage * wageTolerance);
		float payDelta = getPreferredHourlyWage() - actualHourlyWage;
		float moraleDock = payDelta / maxPayDelta;
		
		if (moraleDock > 1)
		{
			moraleDock = 1;
		}
		else if (moraleDock < 0)
		{
			moraleDock = 0;
		}
		
		return (1 - moraleDock);
	}
	
	/**
	 * Preferred Hourly Wage is determined by the selected employee's ambition which, in
	 * turn, is dependent on the employee's position.
	 */
	public float getPreferredHourlyWage()
	{
		C3Simulation simulation = getSimulation();
		int minPay = simulation.getMinimumWageOfPosition(position);
		int maxPay = simulation.getMaximumWageOfPosition(position);
		float payRange = maxPay - minPay;
		float preferredHourlyWage = ambition * payRange + minPay;
		
		return preferredHourlyWage;
	}
	
	public boolean isQuitting()
	{
		return getMorale() <= 0;
	}
	
	private C3Simulation getSimulation()
	{
		// TODO: replace with actual simulation
		return new SimulationTestingStub();
	}
	
	public int getNetEarnings()
	{
		return netEarnings;
	}
	
	public void setNetEarnings(int netEarnings)
	{
		this.netEarnings = netEarnings;
	}
	
	public float getAverageAnnualRaise()
	{
		return averageAnnualRaise;
	}
	
	public void setAverageAnnualRaise(float averageAnnualRaise)
	{
		this.averageAnnualRaise = averageAnnualRaise;
	}
	
	public float getAverageAnnualBonus()
	{
		return averageAnnualBonus;
	}
	
	public void setAverageAnnualBonus(float averageAnnualBonus)
	{
		this.averageAnnualBonus = averageAnnualBonus;
	}
	
	public int getNetBonuses()
	{
		return netBonuses;
	}
	
	public void setNetBonuses(int netBonuses)
	{
		this.netBonuses = netBonuses;
	}
	
}
