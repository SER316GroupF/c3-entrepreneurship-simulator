package edu.asu.c3simulator.simulation;

import java.util.Random;

/**
 * The Employee class is used to store all of the attributes that an employee should have.
 * It is also used to randomly generate these attributes to apply to a list of employees
 * available for hire.
 * 
 * @author nickkrogstad
 * 
 */
public class Employee
{
	public String employeeName;
	public static String employeePosition;
	public int employeePay;
	public static int employeePreferredHourlyWage;
	public int employeeMorale;
	public int netEarnings;
	public float averageAnnualRaise;
	public float averageAnnualBonus;
	public int netBonuses;
	public String randomEmployeePosition;
	
	public Employee(String name, String position, int pay, int preferredHourlyWage,
			int morale, int net_earnings, float annualRaise, float annualBonus,
			int net_bonus)
	{
		employeeName = name;
		employeePosition = position;
		employeePay = pay;
		employeePreferredHourlyWage = preferredHourlyWage;
		employeeMorale = morale;
		netEarnings = net_earnings;
		averageAnnualRaise = annualRaise;
		averageAnnualBonus = annualBonus;
		netBonuses = net_bonus;
	}
	
	private Employee()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return A string used display only the name and pay of an employee for the Employee
	 *         Panel
	 */
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		string.append(employeeName);
		string.append(" ($");
		string.append(employeePay);
		string.append(" / hr)");
		
		return string.toString();
	}
	
	/**
	 * @return An object of type Employee that has three randomly generated attributes:
	 *         Name, Preferred Hourly Wage, and Position.
	 */
	public static Employee getRandomEmployee()
	{
		// TODO: Populate random employee
		Employee employee = new Employee();
		employee.employeeName = getRandomEmployeeName();
		employee.employeePreferredHourlyWage = getPreferredHourlyWage();
		employee.employeePosition = getEmployeePosition();
		
		return employee;
	}
	
	public String getEmployeeName()
	{
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	public static String getRandomEmployeeName()
	{
		String[] firstName = { "Nicholas", "Colton", "Sawyer", "Frankie", "Jennifer",
				"Charles", "Kathy", "James", "Jacob", "Crystal", "Mike", "Cody", "Jorge",
				"Sam", "Lisa", "Margaret", "Marth", "Roy", "Victoria", "Susan", "Ted" };
		String[] lastName = { "Krogstad", "Tucker", "Hardenbech", "Connelley", "Li",
				"Baker", "Tyke", "Mattingly", "Feng", "Lee", "Smith", "Houston",
				"Franco", "White", "Andrade", "Manning", "Brady", "Boyle", "Terry" };
		
		int randomFirst = (int) (Math.random() * firstName.length);
		int randomLast = (int) (Math.random() * lastName.length);
		
		String randomEmployeeName = firstName[randomFirst] + " " + lastName[randomLast];
		
		return randomEmployeeName;
	}
	
	public void setRandomEmployeeName()
	{
		this.employeeName = getRandomEmployeeName();
	}
	
	public static String getEmployeePosition()
	{
		String[] positions = { "Manager", "Product Design", "Sales Associate",
				"Marketing" };
		int randomPositions = (int) (Math.random() * positions.length);
		employeePosition = positions[randomPositions];
		return employeePosition;
	}
	
	public void setEmployeePosition(String employeePosition)
	{
		this.employeePosition = employeePosition;
	}
	
	public int getHourlyWage()
	{
		return employeePay;
	}
	
	public void setHourlyWage(int employeePay)
	{
		this.employeePay = employeePay;
	}
	
	/**
	 * Preferred Hourly Wage is determined by the selected employee's position and returns
	 * an integer between the given pay range
	 */
	public static int getPreferredHourlyWage()
	{
		if (employeePosition == "Manager")
		{
			Random r = new Random();
			int lowPay = 17;
			int highPay = 24;
			int i = r.nextInt(highPay - lowPay);
			Employee.employeePreferredHourlyWage = i + lowPay;
		}
		
		else if (employeePosition == "Product Design")
		{
			Random r = new Random();
			int lowPay = 14;
			int highPay = 19;
			int i = r.nextInt(highPay - lowPay);
			employeePreferredHourlyWage = i + lowPay;
		}
		
		else if (employeePosition == "Sales Associate")
		{
			Random r = new Random();
			int lowPay = 11;
			int highPay = 16;
			int i = r.nextInt(highPay - lowPay);
			employeePreferredHourlyWage = i + lowPay;
		}
		
		else if (employeePosition == "Marketing")
		{
			Random r = new Random();
			int lowPay = 8;
			int highPay = 13;
			int i = r.nextInt(highPay - lowPay);
			employeePreferredHourlyWage = i + lowPay;
		}
		
		return employeePreferredHourlyWage;
	}
	
	public void setPreferredHourlyWage(int emoloyeePreferredHourlyWage)
	{
		this.employeePreferredHourlyWage = emoloyeePreferredHourlyWage;
	}
	
	/**
	 * Employee Morale is determined by the difference between the employees pay
	 * preference and their actual pay. If their morale reaches zero, the employee quits
	 * and is removed from the active list of hired employees.
	 */
	public int getMorale()
	{
		int employeeMorale = 10;
		final int MAX_DIFFERENCE = 4; // Largest difference an employee will take before
										// quitting
		
		int payDifference = employeePreferredHourlyWage - employeePay;
		
		if (employeePay <= employeePreferredHourlyWage)
		{
			employeeMorale -= (payDifference * 2);
		}
		
		if (employeePay >= employeePreferredHourlyWage)
		{
			employeeMorale = 10;
		}
		
		if (payDifference > MAX_DIFFERENCE)
		{
			employeeMorale = 0;
		}
		
		return employeeMorale;
	}
	
	public void setEmployeeMorale(int employeeMorale)
	{
		this.employeeMorale = employeeMorale;
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
