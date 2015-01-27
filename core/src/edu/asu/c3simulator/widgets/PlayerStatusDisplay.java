package edu.asu.c3simulator.widgets;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.util.Observer;

public class PlayerStatusDisplay extends Table implements Observer<Player>
{
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
	
	private HashMap<Field, String> fieldValues;
	private boolean updatesEnabled;
	
	public PlayerStatusDisplay(Player player)
	{
		super();
		this.fieldValues = new HashMap<>();
		this.setFields(player);
		//debug();
		enableUpdates();
		Tables.allignColumns(this, 0, Align.left);
		// TODO: abstract, use percentages; make less hacky
		float verticalPadding = 3.5f;
		Tables.padColumn(this, 0, new Padding(0, 50, verticalPadding, verticalPadding));
		Tables.padColumn(this, 1, new Padding(0, 0, verticalPadding, verticalPadding));
	}
	
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
	
	private void insertValuePairRow(Field field)
	{
		insertValuePair(field);
		row();
	}
	
	private void updateTableValues()
	{
		if (updatesEnabled)
		{
			this.clearChildren();
			
			insertValuePairRow(Field.STATUS);
			insertValuePairRow(Field.NET_WORTH);
			insertValuePairRow(Field.CAPITAL);
			insertValuePair(Field.DATE);
		}
	}
	
	@Override
	public void observableInstanceUpdated(Player newState)
	{
		setFields(newState);
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
	
	public void setFields(Player player)
	{
		disableUpdates();
		{ // START 	non-updating block
			setField(Field.STATUS, player.getStatus());
			setField(Field.CAPITAL, "$" + player.getCapital());
			setField(Field.NET_WORTH, "$" + player.getNetWorth());
			
			C3Simulation simulation = player.getSimulation();
			String simulationDate = simulation.getSimulationDate();
			setField(Field.DATE, simulationDate);
		} // END 	non-updating block
		enableUpdates();
	}
	
	private void enableUpdates()
	{
		this.updatesEnabled = true;
		updateTableValues();
	}
	
	private void disableUpdates()
	{
		this.updatesEnabled = false;
	}
}
