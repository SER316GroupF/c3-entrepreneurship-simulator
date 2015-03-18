package edu.asu.c3simulator.simulation;

import edu.asu.c3simulator.util.Observable;
import edu.asu.c3simulator.util.ObservationListener;

/**
 * Container for data concerning the simulated statistics (money, capital, social
 * standing, etc) of the User
 * 
 * @author Moore, Zachary
 * 
 */
public interface Player extends Observable<Player>
{
	/**
	 * @return Represents the current "level" of the player as s/he stands in the
	 *         simulated "society" as specified by {@link SocioeconomicStatus}
	 */
	SocioeconomicStatus getStatus();
	
	/**
	 * @return The net worth of the player in base units
	 */
	int getNetWorth();
	
	/**
	 * @return The amount of money the player owns as capital
	 */
	int getCapital();
	
	/**
	 * @return Parent simulation to which this player belongs
	 */
	C3Simulation getSimulation();
	
	@Override
	void registerObservationListener(ObservationListener<? super Player> listener);
}
