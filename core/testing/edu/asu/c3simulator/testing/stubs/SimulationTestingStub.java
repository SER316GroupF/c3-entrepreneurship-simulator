package edu.asu.c3simulator.testing.stubs;

import java.text.DateFormat;
import java.util.Date;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Employee.Position;
import edu.asu.c3simulator.simulation.Simulation;
import edu.asu.c3simulator.util.ObservationListener;
import edu.asu.c3simulator.util.Subroutine;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * @author Krogstad, Nick
 * @author Moore, Zachary
 *
 */
public class SimulationTestingStub implements C3Simulation
{
	@Override
	public String getSimulationDateString(DateFormat format)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getSimulationDateString(String format)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getSimulationDateString()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getMinimumWageOfPosition(Position position)
	{
		return 11;
	}
	
	@Override
	public int getMedianWageOfPosition(Position position)
	{
		return 15;
	}
	
	@Override
	public int getMaximumWageOfPosition(Position position)
	{
		return 28;
	}
	
	@Override
	public int getAverageWageOfPosition(Position position)
	{
		return 17;
	}

	@Override
	public void update(float deltaMilliseconds)
	{
		// TODO Auto-generated method stub 
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void registerDateListener(ObservationListener<? super Simulation> listener)
	{
		// TODO Auto-generated method stub 
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void registerTimedEvent(Date time, Subroutine event)
	{
		// TODO Auto-generated method stub 
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
}
