package edu.asu.c3simulator.testing.automated.integrated;

import org.junit.runner.JUnitCore;

import com.badlogic.gdx.ApplicationListener;

public class IntegratedGLTestDriver implements ApplicationListener
{
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
		junit.run(TestActor.class);
		
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
