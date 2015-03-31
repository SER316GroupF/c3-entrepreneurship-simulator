package edu.asu.c3simulator.util;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.SocioeconomicStatus;

/**
 * Implementation of the Player interface Provides detail about various player
 * attributes that are needed for PlayerStatusDisplay
 * 
 * @author Reigel, Justin
 *
 */

public class PlayerInterfaceImplementation implements Player
{
	private SocioeconomicStatus status;
	private int netWorth;
	private int capital;
	private C3Simulation simulation;

	public PlayerInterfaceImplementation(SocioeconomicStatus newStatus, int newNetWorth,
			int newCapital, C3Simulation newSimulation)
	{
		status = newStatus;
		netWorth = newNetWorth;
		capital = newCapital;
		simulation = newSimulation;
	}

	/**
	 * @return The net worth of the player in base units
	 */
	@Override
	public int getNetWorth()
	{
		// TODO Auto-generated method stub
		return netWorth;
	}

	/**
	 * @return The amount of money the player owns as capital
	 */
	@Override
	public int getCapital()
	{
		// TODO Auto-generated method stub
		return capital;
	}

	/**
	 * @return Parent simulation to which this player belongs
	 */
	@Override
	public C3Simulation getSimulation()
	{
		// TODO Auto-generated method stub
		return simulation;
	}

	/**
	 * @return Id of the player
	 */
	@Override
	public String getID()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Player status
	 */
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
