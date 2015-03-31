package edu.asu.c3simulator.testing.stubs;

import java.text.DateFormat;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Employee.Position;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.SocioeconomicStatus;
import edu.asu.c3simulator.util.Observer;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * @author Moore, Zachary
 *
 */
public class PlayerTestingStub implements Player
{
	@Override
	public void registerObserver(Observer<Player> observer)
	{
		
	}
	
	@Override
	public SocioeconomicStatus getStatus()
	{
		return SocioeconomicStatus.TEST_STATUS;
	}
	
	@Override
	public C3Simulation getSimulation()
	{
		return new C3Simulation() {
			@Override
			public String getSimulationDateString()
			{
				return "5 Jan, Year TEST";
			}
			
			@Override
			public String getSimulationDateString(String format)
			{
				// TODO Auto-generated method stub return null;
				throw new UnsupportedOperationException(
						"The method is not implemented yet.");
			}
			
			@Override
			public String getSimulationDateString(DateFormat format)
			{
				// TODO Auto-generated method stub return null;
				throw new UnsupportedOperationException(
						"The method is not implemented yet.");
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
		};
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
}
