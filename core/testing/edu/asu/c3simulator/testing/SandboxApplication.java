package edu.asu.c3simulator.testing;

import com.badlogic.gdx.Game;

/**
 * Application wrapper for {@link Sandbox}
 * 
 * Future versions may allow for a GUI mechanic to switch between sandbox instances
 * 
 * @author Moore, Zachary
 *
 */
public class SandboxApplication extends Game
{
	@Override
	public void create()
	{
		this.setScreen(new Sandbox());
	}
}
