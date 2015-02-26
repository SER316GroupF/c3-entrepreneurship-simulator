package edu.asu.retired.testing.automated;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Convenience class that provides default functionality to setup and test instances of
 * {@link Actor}
 * 
 * @author Moore, Zachary
 * 
 * @param <T>
 */
public abstract class BasicActorUnitTest<T extends Actor> implements GLTestUnit
{
	private int remainingTicks;
	
	protected Batch batch;
	protected T testTarget;
	protected GLTestResult testResults;
	
	/**
	 * @param testTarget
	 *            Instance of target to test
	 */
	public BasicActorUnitTest(String name, T testTarget)
	{
		this(name, testTarget, 1);
	}
	
	/**
	 * @param testTarget
	 *            Instance of target to test
	 * @param totalTicks
	 *            Number of frames to render the object before calling {@link #validate()}
	 */
	public BasicActorUnitTest(String name, T testTarget, int totalTicks)
	{
		this.testTarget = testTarget;
		this.remainingTicks = totalTicks;
		this.batch = new SpriteBatch();
		this.testResults = new GLTestResult(name);
	}
	
	/**
	 * Casts object to {@link Disposable} and calls {@link Disposable#dispose()}
	 * 
	 * @param object
	 *            Disposal target
	 */
	private void disposeOf(Object object)
	{
		Disposable disposable = (Disposable) object;
		disposable.dispose();
	}
	
	public void registerGLAssertionResult(GLAssertionResult result)
	{
		testResults.reportAssertion(result);
	}
	
	/**
	 * Disposes of {@link #testTarget} by calling {@link Disposable#dispose()}, if
	 * applicable, and setting the target to null
	 * 
	 * @see com.badlogic.gdx.utils.Disposable#dispose()
	 */
	@Override
	public void dispose()
	{
		if (testTarget instanceof Disposable)
		{
			disposeOf(testTarget);
		}
		
		testTarget = null;
	}
	
	@Override
	public GLTestResult getTestResult()
	{
		if (!isComplete())
		{
			throw new IllegalStateException(
					"Results cannot be collected until this test is complete.");
		}
		else
		{
			return this.testResults;
		}
	}
	
	@Override
	public boolean isComplete()
	{
		// should be < 0, NOT <= 0
		// This allows the surrounding OpenGL and LibGDX architecture to run for the
		// number of ticks specified
		return remainingTicks < 0;
	}
	
	/**
	 * Renders the {@link #testTarget} and decrements {@link #remainingTicks}. If
	 * {@link #remainingTicks} < 0, this test will be marked as complete.
	 * 
	 * @see edu.asu.retired.testing.automated.GLTestUnit#tick()
	 */
	@Override
	public void tick()
	{
		batch.begin();
		testTarget.draw(batch, 1.0f);
		remainingTicks--;
		batch.end();
	}
	
	@Override
	public String getName()
	{
		return this.testResults.getTestName();
	}
	
}
