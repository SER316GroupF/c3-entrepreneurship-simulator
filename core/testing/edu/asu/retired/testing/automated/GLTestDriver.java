package edu.asu.retired.testing.automated;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.asu.c3simulator.widgets.SimpleTextField;

/**
 * Executes unit tests while simulating an environment using OpenGL and LibGDX.
 * 
 * Contained TestUnits will be given the capacity to simulate {@link Actor}s and other
 * LibGDX components, as well as components with OpenGL dependencies. {@link GLTestDriver}
 * will call {@link GLTestUnit#tick()} every frame, and continue to do so until the unit
 * identifies itself as complete via {@link GLTestUnit#isComplete()}, at which point
 * {@link GLTestDriver} will validate the unit's state via {@link GLTestUnit#validate()},
 * and subsequently remove it from the simulation.
 * 
 * @author Moore, Zachary
 * 
 */
public class GLTestDriver implements ApplicationListener
{
	private Queue<GLTestUnit> currentTests;
	private Queue<GLTestUnit> futureTests;
	private List<GLTestUnit> completedTests;
	
	@Override
	public void create()
	{
		this.currentTests = new LinkedList<>();
		this.futureTests = new LinkedList<>();
		this.completedTests = new LinkedList<>();
		
		GLTest test = new TestWidget<Actor>() {
			
			@Override
			protected Actor createTargetInstance()
			{
				return new SimpleTextField("Test");
			}
		};
		
		registerTest(test);
	}
	
	private void registerTest(GLTest test)
	{
		for (GLTestUnit unit : test.getTestUnits())
		{
			unit.performTestFunctionality();
			this.currentTests.add(unit);
		}
	}
	
	@Override
	public void resize(int width, int height)
	{
		// TODO Add optional screen simulation functionality (outsource #resize to Screen)
	}
	
	@Override
	public void render()
	{
		while (!currentTests.isEmpty())
		{
			GLTestUnit unit = currentTests.poll();
			unit.tick();
			if (unit.isComplete())
			{
				completeUnitTest(unit);
			}
			else
			{
				futureTests.offer(unit);
			}
		}
		
		Queue<GLTestUnit> temporaryQueueReference = currentTests;
		currentTests = futureTests;
		futureTests = temporaryQueueReference;
		
		if (currentTests.isEmpty())
		{
			end();
		}
	}
	
	private void completeUnitTest(GLTestUnit unit)
	{
		try
		{
			System.out.println("Completed: " + unit.getName());
			unit.validate();
		}
		catch (Exception e)
		{
			Failure failure = new Failure(e);
			GLAssertionResult result = new GLAssertionResult(e.getMessage(), failure);
			
			unit.registerGLAssertionResult(result);
		}
		unit.dispose();
		completedTests.add(unit);
	}
	
	@Override
	public void pause()
	{
		// TODO Add optional screen simulation functionality (outsource #pause to Screen)
	}
	
	@Override
	public void resume()
	{
		// TODO Add optional screen simulation functionality (outsource #resume to Screen)
	}
	
	@Override
	public void dispose()
	{
		for (GLTestUnit unit : currentTests)
		{
			unit.dispose();
		}
		
		for (GLTestUnit unit : futureTests)
		{
			unit.dispose();
		}
		
		currentTests = null;
		futureTests = null;
	}
	
	/**
	 * Closes the application and frees system resources
	 */
	private void end()
	{
		Gdx.app.exit();
		System.exit(0);
	}
	
}
