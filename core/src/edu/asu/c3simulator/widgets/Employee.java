package edu.asu.c3simulator.widgets;


public class Employee
{
	public String employeeName;
	public int employeePay;
	public int employeePref;
	public int employeeMorale;
	public int netSalary;
	public float averageAnnualRaise;
	public float averageAnnualBonus;
	public int netBonuses;
	
	public Employee(String name, int pay, int pref, int morale, int net_salary, float annualRaise, float annualBonus, int net_bonus)
	{
		employeeName = name;
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
	
	public String getEmployeeName()
	{
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName)
	{
		this.employeeName = employeeName;
	}
	
	public int getEmployeePay()
	{
		return employeePay;
	}
	
	public void setEmployeePay(int employeePay)
	{
		this.employeePay = employeePay;
	}
	
	public int getEmployeePref()
	{
		return employeePref;
	}
	
	public void setEmployeePref(int employeePref)
	{
		this.employeePref = employeePref;
	}
	
	public int getEmployeeMorale()
	{
		return employeeMorale;
	}
	
	public void setEmployeeMorale(int employeeMorale)
	{
		this.employeeMorale = employeeMorale;
	}
}
