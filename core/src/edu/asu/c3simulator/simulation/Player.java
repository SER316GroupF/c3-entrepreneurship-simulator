package edu.asu.c3simulator.simulation;

import edu.asu.c3simulator.util.Observable;
import edu.asu.c3simulator.util.Observer;

public interface Player extends Observable<Player>
{

	String getStatus();

	int getNetWorth();

	int getCapital();

	C3Simulation getSimulation();
	
	@Override
	void registerObserver(Observer<Player> observer);
	
}
