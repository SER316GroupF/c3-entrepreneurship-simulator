package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.testing.SandboxApplication;
import edu.asu.c3simulator.widgets.SimpleTextField;

public class TestTemplate
{
	private Object synch;
	private boolean run;
	private Widget testTarget;
	private static LwjglApplication app;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = 1280;
		cfg.height = 720;
		app = new LwjglApplication(new SandboxApplication(), cfg);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		Gdx.app.exit();
		app.exit();
	}
	
	@Before
	public void setUp() throws Exception
	{
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	@Test
	public void test()
	{
		run = false;
		synch = new Object();
		
		Gdx.app.postRunnable(new Runnable() {
			
			@Override
			public void run()
			{
				testTarget = new SimpleTextField("Test");
				testTarget.setSize(0, 0);
				testTarget.validate();
				System.out.println("run");
				run = true;
				
				synchronized (synch)
				{
					synch.notify();
				}
			}
		});
		
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
		
		System.out.println("end loop");
		assertTrue(testTarget.getWidth() == 0);
		assertTrue(testTarget.getHeight() == 0);
		
	}
	
}
