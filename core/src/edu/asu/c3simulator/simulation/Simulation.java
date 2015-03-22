package edu.asu.c3simulator.simulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import edu.asu.c3simulator.util.Observable;
import edu.asu.c3simulator.util.ObservationListener;
import edu.asu.c3simulator.util.Subroutine;

/**
 * Simulation that tracks time using an adjustable {@link #timeScale} and
 * {@link #scaledTimeUnit}. Each millisecond of real-time represents {@link #timeScale}
 * amount of time in-game, and the in-game time is expressed in {@link #scaledTimeUnit}s
 * <p>
 * {@link #update(float)} should be called regularly, as it is what will update the
 * in-game time, and trigger all applicable simulation events.
 * <p>
 * DateEvents will be triggered whenever the in-game date is updated, and can be
 * registered with {@link #registerDateListener(ObservationListener)}.
 * <p>
 * StateChangeEvents will be triggered whenever any change in the simulation data occurs,
 * including date changes, changes in business value, new businesses starting, etc.
 * <p>
 * Some cases will require events to be triggered at a certain in-game time. For these
 * cases, use {@link #registerTimedEvent(Date, Subroutine)}
 * 
 * @author Moore, Zachary
 * @version 0.1
 *
 */
public class Simulation implements C3Simulation, Observable<Simulation>
{
	private static class EventNode implements Comparable<EventNode>
	{
		private Date date;
		private Subroutine event;
		
		@Override
		public int compareTo(EventNode other)
		{
			return date.compareTo(other.date);
		}
	}
	
	private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"dd MMM yy");
	
	private static final int DEFAULT_SCALED_TIME_UNIT = Calendar.MINUTE;
	
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
	
	/**
	 * Simulation will be run relative to a single player. As of V0.1, {@link #player} is
	 * unused except by {@link #getPlayer()}
	 */
	private Player player;
	
	/**
	 * Handles all dateTime storage and processing. {@link #calendar} will be incremented
	 * by {@link Simulation#update(float)}, and should not be updated by any other thread.
	 */
	private Calendar calendar;
	
	// TODO: replace with lambda implementation
	/** Listeners to be updated upon state change */
	private List<ObservationListener<? super Simulation>> stateListeners;
	
	/** Listeners to be updated upon date change */
	private List<ObservationListener<? super Simulation>> dateListeners;
	
	/** Listeners to be updated at a certain in-game time */
	private PriorityQueue<EventNode> timedEvents;
	
	public Simulation(Player player, float timeScale)
	{
		this(player, timeScale, DEFAULT_SCALED_TIME_UNIT);
	}
	
	public Simulation(Player player, float timeScale, int scaledTimeUnit)
	{
		this.timeScale = timeScale;
		this.scaledTimeUnit = scaledTimeUnit;
		this.player = player;
		this.calendar = createInitializedGregorianCalendar();
		this.stateListeners = new LinkedList<>();
		this.dateListeners = new LinkedList<>();
		this.timedEvents = new PriorityQueue<>();
	}
	
	private GregorianCalendar createInitializedGregorianCalendar()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setGregorianChange(new Date(0L));
		calendar.setTime(new Date(0L));
		return calendar;
	}
	
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
		checkTimedEvents();
		triggerListeners(dateListeners);
		triggerListeners(stateListeners);
	}
	
	@Override
	public void registerObservationListener(
			ObservationListener<? super Simulation> listener)
	{
		stateListeners.add(listener);
	}
	
	@Override
	public void registerDateListener(ObservationListener<? super Simulation> listener)
	{
		dateListeners.add(listener);
	}
	
	@Override
	public void registerTimedEvent(Date time, Subroutine event)
	{
		EventNode node = new EventNode();
		node.date = time;
		node.event = event;
		this.timedEvents.add(node);
	}
	
	/**
	 * Evaluates events in {@link #timedEvents} and if the current date is >= to the
	 * specified event trigger date, the event will be triggered and removed from the
	 * queue.
	 */
	private void checkTimedEvents()
	{
		Date now = calendar.getTime();
		EventNode event = timedEvents.peek();
		while (event != null && !now.before(event.date))
		{
			timedEvents.poll().event.run();
		}
	}
	
	private void triggerListeners(List<ObservationListener<? super Simulation>> listeners)
	{
		for (ObservationListener<? super Simulation> listener : listeners)
		{
			listener.stateChanged(this);
		}
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public float getTimeScale()
	{
		return timeScale;
	}
	
	public void setTimeScale(float timeScale)
	{
		this.timeScale = timeScale;
	}
	
	public int getScaledTimeUnit()
	{
		return scaledTimeUnit;
	}
	
	public void setScaledTimeUnit(int scaledTimeUnit)
	{
		this.scaledTimeUnit = scaledTimeUnit;
	}
}
