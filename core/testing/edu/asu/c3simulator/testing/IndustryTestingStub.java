package edu.asu.c3simulator.testing;

import edu.asu.c3simulator.simulation.Industry;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * @author Moore, Zachary
 *
 */
public class IndustryTestingStub implements Industry
{
	String name;
	
	public IndustryTestingStub(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String[] getDescription()
	{
		return new String[] { item(" 1"), item(" 2"), item(" 3") };
	}
	
	private String item(String suffix)
	{
		return name + suffix;
	}
	
}
