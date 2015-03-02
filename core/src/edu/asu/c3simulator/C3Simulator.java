package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.screens.EmploymentScreen;

/**
 * Main driver for C3 program.
 * 
 * @author Moore, Zachary
 *
 */
public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new EmploymentScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
