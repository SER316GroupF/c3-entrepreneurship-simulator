package edu.asu.c3simulator.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * A {@link C3Simulation} handles all information related to the simulated world,
 * including the simulation clock, the current state of the economy, random events, etc.
 * 
 * @author Moore, Zachary
 * 
 */
public interface C3Simulation
{
	/**
	 * @return The current simulation date, in any, unspecified format. For formatted date
	 *         strings, see {@link #getSimulationDateString(String)} and
	 *         {@link #getSimulationDateString(DateFormat)}
	 */
	public String getSimulationDateString();
	
	/**
	 * @param format
	 *            A string representing the format of the date, as specified by
	 *            {@link SimpleDateFormat}
	 * @return The current simulation date, in the format specified by format
	 */
	public String getSimulationDateString(String format);
	
	/**
	 * @param format
	 *            Will format the current simulation date using
	 *            {@link DateFormat#format(java.util.Date)}
	 * @return
	 */
	public String getSimulationDateString(DateFormat format);
}
