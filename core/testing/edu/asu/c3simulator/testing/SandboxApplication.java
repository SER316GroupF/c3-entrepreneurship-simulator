package edu.asu.c3simulator.testing;

import com.badlogic.gdx.Game;

public class SandboxApplication extends Game
{
	@Override
	public void create()
	{
		this.setScreen(new Sandbox());
	}
}
