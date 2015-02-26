package edu.asu.retired.testing.automated;

import org.junit.runner.JUnitCore;

import com.badlogic.gdx.ApplicationListener;

import edu.asu.c3simulator.testing.automated.integrated.IntegratedTest;
import edu.asu.retired.testing.automated.baseline.TestActor;

/**
 * Driver to run JUnit tests with an OpenGL Context
 * 
 * @author Moore, Zachary
 * @deprecated Consider using {@link IntegratedTest} in conjunction with JUnit instead
 *
 */
public class IntegratedGLTestDriver implements ApplicationListener
{
	/**
	 * Classes to be tested. Much like a JUnit TestSuite
	 */
	private static Class<?>[] tests = new Class<?>[] { TestActor.class };
	
	@Override
	public void create()
	{
		
	}
	
	@Override
	public void resize(int width, int height)
	{
		
	}
	
	@Override
	public void render()
	{
		JUnitCore junit = new JUnitCore();
		junit.run(tests);
		
		System.exit(0);
	}
	
	@Override
	public void pause()
	{
		
	}
	
	@Override
	public void resume()
	{
		
	}
	
	@Override
	public void dispose()
	{
		
	}
	
}
