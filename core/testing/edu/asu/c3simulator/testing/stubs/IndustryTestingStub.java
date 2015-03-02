package edu.asu.c3simulator.testing.stubs;

import edu.asu.c3simulator.simulation.Industry;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * Returns a description based on the name provided outside of this class.
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
	
	/**
	 * Creates a description term by appending suffix to {@link #name}
	 * 
	 * @param suffix
	 *            String to append to {@link #name}
	 * @return Concatenation of {@link #name} and suffix
	 */
	private String item(String suffix)
	{
		return name + suffix;
	}
	
}
