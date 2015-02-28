/**
 * 
 */
package edu.asu.c3simulator.util;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;

/**
 * @author Justin
 *
 */

public class PlayerImp implements Player
{

	private String status;
	private int netWorth;
	private int capital;
	private C3Simulation simulation;
	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Player#getStatus()
	 */
	public PlayerImp(String newStatus, int newNetWorth, int newCapital, C3Simulation newSimulation){
		status = newStatus;
		netWorth = newNetWorth;
		newCapital= newCapital;
		simulation = newSimulation;
	}
	@Override
	public String getStatus()
	{
		// TODO Auto-generated method stub
		return status;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Player#getNetWorth()
	 */
	@Override
	public int getNetWorth()
	{
		// TODO Auto-generated method stub
		return netWorth;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Player#getCapital()
	 */
	@Override
	public int getCapital()
	{
		// TODO Auto-generated method stub
		return capital;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Player#getSimulation()
	 */
	@Override
	public C3Simulation getSimulation()
	{
		// TODO Auto-generated method stub
		return simulation;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Player#registerObserver(edu.asu.c3simulator.util.Observer)
	 */
	@Override
	public void registerObserver(Observer<Player> observer)
	{
		// TODO Auto-generated method stub

	}

}
