package edu.asu.c3simulator.testing.stubs;

import java.util.ArrayList;
import java.util.List;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.SocioeconomicStatus;
import edu.asu.c3simulator.util.ObservationListener;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * @author Moore, Zachary
 *
 */
public class PlayerTestingStub implements Player
{
	private List<ObservationListener<? super Player>> listeners;
	
	public PlayerTestingStub()
	{
		listeners = new ArrayList<>();
	}
	
	@Override
	public SocioeconomicStatus getStatus()
	{
		return SocioeconomicStatus.TEST_STATUS;
	}
	
	@Override
	public C3Simulation getSimulation()
	{
		return C3Simulator.getSimulation();
	}
	
	@Override
	public int getNetWorth()
	{
		return 1000000;
	}
	
	@Override
	public int getCapital()
	{
		return 100000;
	}
	
	@Override
	public String getID()
	{
		return "test";
	}
	
	@Override
	public void registerObservationListener(ObservationListener<? super Player> listener)
	{
		listeners.add(listener);
	}
}
