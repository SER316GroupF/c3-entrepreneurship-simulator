package edu.asu.c3simulator.util;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.SocioeconomicStatus;

/**
 * Implementation of the Player interface Provides detail about various player
 * attributes that are needed for PlayerStatusDisplay. This class will be refactored and replaced in the future. 
 * 
 * @author Reigel, Justin
 *
 *@version V0.1
 */
//TODO Rename and replace with a refactored version in the future.
public class PlayerInterfaceImplementation implements Player
{
	private SocioeconomicStatus status;
	private int netWorth;
	private int capital;
	private C3Simulation simulation;

	//TODO Decide how the live data layer will be handled
	public PlayerInterfaceImplementation(SocioeconomicStatus newStatus, int newNetWorth,
			int newCapital, C3Simulation newSimulation)
	{
		status = newStatus;
		netWorth = newNetWorth;
		capital = newCapital;
		simulation = newSimulation;
	}

	@Override
	public int getNetWorth()
	{
		// TODO Auto-generated method stub
		return netWorth;
	}

	@Override
	public int getCapital()
	{
		// TODO Auto-generated method stub
		return capital;
	}

	@Override
	public C3Simulation getSimulation()
	{
		// TODO Auto-generated method stub
		return simulation;
	}

	@Override
	public String getID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocioeconomicStatus getStatus()
	{
		// TODO Auto-generated method stub
		return status;
	}

	@Override
	public void registerObservationListener(
			ObservationListener<? super Player> listener)
	{
		// TODO Auto-generated method stub

	}

}
