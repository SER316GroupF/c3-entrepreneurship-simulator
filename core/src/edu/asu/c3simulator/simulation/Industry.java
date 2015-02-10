package edu.asu.c3simulator.simulation;


/**
 * Represents an economic industry, to be used {@link C3Simulation}
 * 
 * @author Moore, Zachary
 *
 */
public interface Industry
{
	/**
	 * @return The unique name of the industry, without any common prefix or suffix
	 */
	String getName();

	/**
	 * @return An array of "bullet points" describing this industry
	 */
	String[] getDescription();
	
}
