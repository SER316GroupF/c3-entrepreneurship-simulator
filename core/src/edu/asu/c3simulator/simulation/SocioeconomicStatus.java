package edu.asu.c3simulator.simulation;

/**
 * Represents the current "level" of the player as s/he stands in the simulated "society"
 * as modeled by {@link C3Simulation}
 * 
 * @author Moore, Zachary
 * 
 */
public enum SocioeconomicStatus
{
	// TODO: replace placeholder value with final content
	TEST_STATUS("test status");
	
	private String string;
	
	private SocioeconomicStatus(String string)
	{
		this.string = string;
	}
	
	public String asString()
	{
		return string;
	}
}
