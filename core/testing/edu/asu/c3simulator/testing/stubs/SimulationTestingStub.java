package edu.asu.c3simulator.testing.stubs;

import java.text.DateFormat;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Employee.Position;

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
}
