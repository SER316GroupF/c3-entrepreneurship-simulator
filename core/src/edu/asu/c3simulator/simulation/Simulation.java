package edu.asu.c3simulator.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.asu.c3simulator.util.Observable;
import edu.asu.c3simulator.util.ObservationListener;

/**
 * 
 * 
 * @author Moore, Zachary
 *
 */
public class Simulation implements C3Simulation, Observable<Simulation>
{
	private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"dd MMM yy");
	
	/** (simulationTime : realTime) ratio */
	private float timeScale;
	
	/**
	 * A millisecond in realTime will represent this unit in the simulator. The value is
	 * specified by {@link Calendar}, and valid values include {@link Calendar#MONTH},
	 * {@link Calendar#HOUR}, and {@link Calendar#MINUTE}, but any similar unit can be
	 * used.
	 * 
	 * Note: this scale will be applied in addition to {@link #timeScale}
	 */
	private int scaledTimeUnit;
	
	/** Simulation will be run relative to a single player */
	private Player player;
	
	/**
	 * Handles all dateTime storage and processing. {@link #calendar} will be incremented
	 * by {@link Simulation#update(float)}, and should be updated by any other thread.
	 */
	private Calendar calendar;
	
	/** Listeners to be updated upon state change */
	private List<ObservationListener<? super Simulation>> stateListeners;
	
	/** Listeners to be updated upon date change */
	private List<ObservationListener<? super Simulation>> dateListeners;
	
	@Override
	public String getSimulationDateString()
	{
		return getSimulationDateString(DEFAULT_DATE_FORMAT);
	}
	
	@Override
	public String getSimulationDateString(String format)
	{
		DateFormat dateFormat = new SimpleDateFormat(format);
		return getSimulationDateString(dateFormat);
	}
	
	@Override
	public String getSimulationDateString(DateFormat format)
	{
		Date date = calendar.getTime();
		return format.format(date);
	}
	
	@Override
	public void update(float deltaMilliseconds)
	{
		int time = (int) (deltaMilliseconds * timeScale);
		calendar.add(scaledTimeUnit, time);
	}
	
	@Override
	public void registerObservationListener(
			ObservationListener<? super Simulation> listener)
	{
		stateListeners.add(listener);
	}
	
	/**
	 * Registers a listener that is notified only when the date changes. If the listener
	 * should be aware of all state changes, see
	 * {@link #registerObservationListener(ObservationListener)}
	 * 
	 * @param listener
	 *            Will be notified via {@link ObservationListener#stateChanged(Object)}
	 *            whenever the date is updated.
	 */
	public void registerDateListener(ObservationListener<? super Simulation> listener)
	{
		dateListeners.add(listener);
	}
}
