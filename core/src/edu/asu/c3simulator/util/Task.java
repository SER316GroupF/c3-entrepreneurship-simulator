package edu.asu.c3simulator.util;

/**
 * This is a class that holds an instance of a task. This is but a shell for now that
 * needs to be implemented further.
 * 
 * @author Alyahya, Mohammed
 */
public class Task
{
	/** The name of the Task */
	private String name;
	/** The task's type: Action Based or Non-Action Based */
	private Type type;
	public static enum Type
	{
		ACTION_BASED,
		NON_ACTION_BASED;
	}
	
	public Task(String name, Type type)
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
	
	public Type getType()
	{
		return type;
	}
	
	public void setType(Type type)
	{
		this.type = type;
	}
}
