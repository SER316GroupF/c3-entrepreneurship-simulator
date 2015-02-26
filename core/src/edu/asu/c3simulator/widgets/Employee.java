package edu.asu.c3simulator.widgets;


public class Employee
{
	public String employeeName;
	public int employeePay;
	public int employeePref;
	public int employeeMorale;
	
	public Employee(String name, int pay, int pref, int morale)
	{
		employeeName = name;
		employeePay = pay;
		employeePref = pref;
		employeeMorale = morale;
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
		//TODO: Implement
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public float getAverageAnnualRaise()
	{
		//TODO: Implement
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public float getAverageAnnualBonus()
	{
		//TODO: Implement
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public int getNetBonuses()
	{
		//TODO: Implement
		throw new UnsupportedOperationException("Not yet implemented");
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
