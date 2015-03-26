package edu.asu.c3simulator.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.asu.c3simulator.util.ObservationListener;
import edu.asu.c3simulator.util.Subroutine;

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
	
	/**
	 * @param deltaMilliseconds
	 *            Time since last {@link #update(float)} call, in milliseconds
	 */
	public void update(float deltaMilliseconds);
	
	/**
	 * Registers a listener that is notified only when the date changes. If the listener
	 * should be aware of all state changes, see
	 * {@link #registerObservationListener(ObservationListener)}
	 * 
	 * @param listener
	 *            Will be notified via {@link ObservationListener#stateChanged(Object)}
	 *            whenever the date is updated.
	 */
	public void registerDateListener(ObservationListener<? super Simulation> listener);
	
	/**
	 * @param time
	 *            The in-game time at which to trigger the event
	 * @param event
	 *            The {@link Subroutine} to call at the specified in-game time
	 */
	public void registerTimedEvent(Date time, Subroutine event);


	public int getMinimumWageOfPosition(Employee.Position position);
	
	public int getMedianWageOfPosition(Employee.Position position);
	
	public int getMaximumWageOfPosition(Employee.Position position);
	
	public int getAverageWageOfPosition(Employee.Position position);

}
