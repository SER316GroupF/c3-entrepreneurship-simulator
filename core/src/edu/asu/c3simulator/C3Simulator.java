package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.screens.EmployeeHistoryScreen;

public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new EmployeeHistoryScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
