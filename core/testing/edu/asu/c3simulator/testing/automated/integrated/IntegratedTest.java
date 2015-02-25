package edu.asu.c3simulator.testing.automated.integrated;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * {@link IntegratedTest} is run as soon as it is instantiated. It is intended to be
 * created (and therefore run) inside of a JUnit test.
 * <p>
 * {@link IntegratedTest} provides access to an OpenGL context and LibGDX context from
 * within a JUnit test.
 * <p>
 * Also note that an {@link LwjglApplication} should be instantiated in the @BeforeClass
 * method of the JUnit test, and {@link LwjglApplication#exit()} should be called @AfterClass
 * 
 * @author Moore, Zachary
 *
 */
public abstract class IntegratedTest
{
	/**
	 * Code to be run by the OpenGL thread. It calls {@link IntegratedTest#runGLCode()}
	 * and notifies {@link IntegratedTest#synch} when it is complete.
	 * 
	 * @author Moore, Zachary
	 *
	 */
	private class RunTarget implements Runnable
	{
		@Override
		public void run()
		{
			runGLCode();
			
			synchronized (synch)
			{
				synch.notify();
			}
		}
	}
	
	/**
	 * Used to synchronize this test with the OpenGL thread
	 */
	private Object synch;
	
	/**
	 * Create and run a new {@link IntegratedTest}
	 */
	public IntegratedTest()
	{
		run();
	}
	
	/**
	 * Runs this test and the assertions defined by {@link #runGLCode()} and
	 * {@link #runAssertions()}
	 */
	private void run()
	{
		synch = new Object();
		Gdx.app.postRunnable(new RunTarget());
		
		waitForGLToFinish();
		
		runAssertions();
	}
	
	/**
	 * Puts this thread into a wait state until the OpenGL thread notifies us of its
	 * completion
	 */
	private void waitForGLToFinish()
	{
		synchronized (synch)
		{
			try
			{
				synch.wait();
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}
	
	/**
	 * Setup the test and run all code to be tested. Code in this method will be run with
	 * an OpenGL context.
	 * 
	 * Instantiation of {@link Widget}, {@link Actor}, and all other components that use
	 * or depend on OpenGL should be run here.
	 */
	abstract protected void runGLCode();
	
	/**
	 * Assert conditions using JUnit, as would normally be done in a test case.
	 */
	abstract protected void runAssertions();
}
