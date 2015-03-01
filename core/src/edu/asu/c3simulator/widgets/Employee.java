package edu.asu.c3simulator.widgets;

import java.util.Random;

public class Employee
{
	public String employeeName;
	public String randomEmployeeName;
	public String employeePosition;
	public String randomEmployeePosition;
	public int employeePay;
	public int employeePref;
	public int employeeMorale = 10;
	public int netSalary;
	public float averageAnnualRaise;
	public float averageAnnualBonus;
	public int netBonuses;
	
	public Employee(String name, String position, int pay, int pref, int morale,
			int net_salary, float annualRaise, float annualBonus, int net_bonus)
	{
		employeeName = name;
		employeePosition = position;
		employeePay = pay;
		employeePref = pref;
		employeeMorale = morale;
		netSalary = net_salary;
		averageAnnualRaise = annualRaise;
		averageAnnualBonus = annualBonus;
		netBonuses = net_bonus;
	}
	
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
	
	public String getEmployeeName()
	{
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	public String getRandomEmployeeName()
	{
		String[] firstName = { "Nicholas", "Colton", "Sawyer", "Frankie", "Jennifer",
				"Charles", "Kathy", "James", "Jacob", "Crystal", "Mike", "Cody", "Jorge",
				"Sam", "Lisa", "Margaret", "Marth", "Victoria", "Susan", "Ted" };
		String[] lastName = { "Krogstad", "Tucker", "Hardenbech", "Connelley", "Li",
				"Baker", "Tyke", "Mattingly", "Feng", "Lee", "Smith", "Houston",
				"Franco", "White", "Andrade", "Manning", "Brady", "Boyle", "Terry" };
		
		int randomFirst = (int) (Math.random() * firstName.length);
		int randomLast = (int) (Math.random() * lastName.length);
		
		randomEmployeeName = firstName[randomFirst] + " " + lastName[randomLast];
		
		return randomEmployeeName;
	}
	
	public void setRandomEmployeeName()
	{
		this.randomEmployeeName = randomEmployeeName;
	}
	
	/*
	 * public String getEmployeePosition() { return employeePosition; }
	 * 
	 * public void setEmployeePosition(String employeePosition) { this.employeePosition =
	 * employeePosition; }
	 */
	
	public String getEmployeePosition()
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
	
	public int getEmployeePay()
	{
		return employeePay;
	}
	
	public void setEmployeePay(int employeePay)
	{
		this.employeePay = employeePay;
	}
	
	/*
	 * Employee Preference is determined by the selected employee's position and returns
	 * an integer between the given pay range. The offset between the employee preference
	 * and the employee pay determines the employee's morale.
	 */
	
	public int getEmployeePref()
	{
		if (employeePosition == "Manager")
		{
			Random r = new Random();
			int lowPay = 17;
			int highPay = 24;
			int i = r.nextInt(highPay - lowPay);
			employeePref = i + lowPay;
		}
		
		else if (employeePosition == "Product Design")
		{
			Random r = new Random();
			int lowPay = 14;
			int highPay = 19;
			int i = r.nextInt(highPay - lowPay);
			employeePref = i + lowPay;
		}
		
		else if (employeePosition == "Sales Associate")
		{
			Random r = new Random();
			int lowPay = 11;
			int highPay = 16;
			int i = r.nextInt(highPay - lowPay);
			employeePref = i + lowPay;
		}
		
		else if (employeePosition == "Marketing")
		{
			Random r = new Random();
			int lowPay = 8;
			int highPay = 13;
			int i = r.nextInt(highPay - lowPay);
			employeePref = i + lowPay;
		}
		
		return employeePref;
	}
	
	public void setEmployeePref(int employeePref)
	{
		this.employeePref = employeePref;
	}
	
	/*
	 * Employee Morale is determined by the difference between their pay
	 * preference and their actual pay. If their morale reaches zero, the
	 * employee quits and is removed from the active roster.
	 */
	
	public int getEmployeeMorale()
	{
		int employeeMorale = 10;
		int payDifference = employeePref - employeePay;
		
		if (payDifference == 1)
		{
			employeeMorale -= 2;
		}
		
		else if (payDifference == 2)
		{
			employeeMorale -= 4;
		}
		
		else if (payDifference == 3)
		{
			employeeMorale -= 6;
		}
		
		else if (payDifference == 4)
		{
			employeeMorale -= 8;
		}
		
		else
		{
			employeeMorale = 0;
		}
		
		if (employeePay >= employeePref)
		{
			employeeMorale = 10;
		}
		
		return employeeMorale;
	}
	
	public void setEmployeeMorale(int employeeMorale)
	{
		this.employeeMorale = employeeMorale;
	}
	
	public int getNetSalary()
	{
		return netSalary;
	}
	
	public void setNetSalary(int netSalary)
	{
		this.netSalary = netSalary;
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
