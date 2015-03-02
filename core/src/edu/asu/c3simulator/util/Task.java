package edu.asu.c3simulator.util;

public class Task
{
	private String name;
	private String type;
	
	public Task(String name, String type)
	{
		this.name = name;
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
