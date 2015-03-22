package edu.asu.c3simulator.widgets;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.Simulation;
import edu.asu.c3simulator.util.ObservationListener;

/**
 * Used to display relevant information regarding a single {@link Player} object.
 * <p>
 * Each field, specified by {@link Field} and evaluated by {@link Player} will be
 * displayed on one row of a {@link Table}.
 * <p>
 * As such, {@link PlayerStatusDisplay} will have one row for each {@link Field}.
 * 
 * @author Moore, Zachary
 *
 */
public class PlayerStatusDisplay extends Table
{
	/**
	 * All fields that are displayed by this widget.
	 * 
	 * @author Moore, Zachary
	 *
	 */
	public static enum Field
	{
		STATUS("Status"),
		NET_WORTH("Net Worth"),
		CAPITAL("Capital"),
		DATE("Date");
		
		private String displayName;
		
		private Field(String displayName)
		{
			this.displayName = displayName;
		}
		
		public String displayName()
		{
			return displayName;
		}
	}
	
	/**
	 * Updates this display when the state of the player is changed.
	 * 
	 * @author Moore, Zachary
	 *
	 */
	private class PlayerListener implements ObservationListener<Player>
	{
		@Override
		public void stateChanged(Player newState)
		{
			setFields(newState);
		}
	}
	
	/**
	 * Updates this display when the state of the player is changed.
	 * 
	 * @author Moore, Zachary
	 *
	 */
	private class DateListener implements ObservationListener<Simulation>
	{
		@Override
		public void stateChanged(Simulation newState)
		{
			setField(Field.DATE, newState.getSimulationDateString());
		}
	}
	
	private HashMap<Field, String> fieldValues;
	private boolean updatesEnabled;
	
	public PlayerStatusDisplay()
	{
		super();
		Player player = C3Simulator.getPlayer();
		this.fieldValues = new HashMap<>();
		this.setFields(player);
		// debug();
		enableUpdates();
		C3Simulation simulation = C3Simulator.getSimulation();
		
		player.registerObservationListener(new PlayerListener());
		simulation.registerDateListener(new DateListener());
	}
	
	/**
	 * Given a field, insert 2 text fields into this display to show the name of the field
	 * followed by the value of the field, as given by {@link #fieldValues}
	 * 
	 * @param field
	 *            Data to insert
	 */
	private void insertValuePair(Field field)
	{
		String labelText = field.displayName();
		String valueText = fieldValues.get(field);
		
		if (valueText == null)
			valueText = "";
		
		SimpleTextField label = new SimpleTextField(labelText);
		SimpleTextField valueLabel = new SimpleTextField(valueText);
		
		add(label);
		add(valueLabel);
	}
	
	/**
	 * Calls {@link #insertValuePair(Field)} followed by {@link #row()}
	 * 
	 * @param field
	 *            Data to insert
	 */
	private void insertValuePairRow(Field field)
	{
		insertValuePair(field);
		row();
	}
	
	/**
	 * Ensures the display shows the same data as found in {@link #fieldValues}
	 */
	private void updateTableValues()
	{
		if (updatesEnabled)
		{
			this.clearChildren();
			
			insertValuePairRow(Field.STATUS);
			insertValuePairRow(Field.NET_WORTH);
			insertValuePairRow(Field.CAPITAL);
			insertValuePair(Field.DATE);
			
			Tables.allignColumns(this, 0, Align.left);
			// TODO: abstract, use percentages; make less hacky
			float verticalPadding = 3.5f;
			Tables.padColumn(this, 0,
					new Padding(0, 50, verticalPadding, verticalPadding));
			Tables.padColumn(this, 1, new Padding(0, 0, verticalPadding, verticalPadding));
			
			pack();
		}
	}
	
	public void setField(Field field, double value)
	{
		String valueString = Double.toString(value);
		setField(field, valueString);
	}
	
	public void setField(Field field, float value)
	{
		String valueString = Float.toString(value);
		setField(field, valueString);
	}
	
	public void setField(Field field, int value)
	{
		String valueString = Integer.toString(value);
		setField(field, valueString);
	}
	
	public void setField(Field field, long value)
	{
		String valueString = Long.toString(value);
		setField(field, valueString);
	}
	
	public void setField(Field field, String value)
	{
		if (!value.equals(fieldValues.get(field)))
		{
			fieldValues.put(field, value);
			updateTableValues();
		}
	}
	
	/**
	 * Updates the data in {@link #fieldValues} to match the values in player, then
	 * updates the display after all data updates have been made.
	 * 
	 * @param player
	 *            Data to conform to.
	 */
	public void setFields(Player player)
	{
		disableUpdates();
		{ // START non-updating block
			setField(Field.STATUS, player.getStatus().asString());
			setField(Field.CAPITAL, "$" + player.getCapital());
			setField(Field.NET_WORTH, "$" + player.getNetWorth());
			
			C3Simulation simulation = C3Simulator.getSimulation();
			String simulationDate = simulation.getSimulationDateString();
			setField(Field.DATE, simulationDate);
		} // END non-updating block
		enableUpdates();
	}
	
	/**
	 * Allow the display to update based on the data in {@link #fieldValues}
	 */
	private void enableUpdates()
	{
		this.updatesEnabled = true;
		updateTableValues();
	}
	
	/**
	 * Disable updating of the display. This will cause {@link #updateTableValues()} to do
	 * nothing, until updates are enabled again.
	 */
	private void disableUpdates()
	{
		this.updatesEnabled = false;
	}
}
