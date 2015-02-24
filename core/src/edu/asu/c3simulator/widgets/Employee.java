package edu.asu.c3simulator.widgets;

public class Employee
{
	public String employeeName;
	public int employeePay;
	
	public Employee(String name, int pay)
	{
		employeeName = name;
		employeePay = pay;
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
}
