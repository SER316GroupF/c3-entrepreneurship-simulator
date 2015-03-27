package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.testing.SandboxApplication;
import edu.asu.c3simulator.testing.automated.IntegratedTest;
import edu.asu.c3simulator.widgets.SimpleTextField;

/**
 * Template to test units that require an OpenGL or LibGDX context. All calls that use an
 * OpenGL context should be made using {@link IntegratedTest}
 * 
 * @author Moore, Zachary
 *
 */
public class TestTemplate
{
	private static Object applicationSynch;
	private static LwjglApplication application;
	
	/**
	 * Subject of these tests
	 */
	private Widget testTarget;
	
	private static class Monitor implements LifecycleListener
	{
		@Override
		public void pause()
		{
			// DO NOTHING
		}
		
		@Override
		public void resume()
		{
			// DO NOTHING
		}
		
		@Override
		public void dispose()
		{
			synchronized (applicationSynch)
			{
				applicationSynch.notify();
			}
		}
	}
	
	private static void waitForApplicationToExit()
	{
		synchronized (applicationSynch)
		{
			try
			{
				applicationSynch.wait();
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}
	
	/**
	 * Create an Application that will give access to an OpenGL context as well as a
	 * LibGDX context
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		if (application == null)
		{
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.width = 1280;
			config.height = 720;
			application = new LwjglApplication(new SandboxApplication(), config);
		}
	}
	
	/**
	 * Cleanup the application object
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		
	}
	
	@Before
	public void setUp() throws Exception
	{
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	/**
	 * Basic test case to test {@link Actor#setSize(float, float)}
	 * 
	 * Example to show how to use {@link IntegratedTest}
	 */
	@Test
	public void testSample()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				testTarget = new SimpleTextField("Test");
				testTarget.setSize(0, 0);
				testTarget.validate();
			}
			
			@Override
			protected void runAssertions()
			{
				assertTrue(testTarget.getWidth() == 0);
				assertTrue(testTarget.getHeight() == 0);
			}
		};
		
	}
	
}
