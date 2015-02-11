package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.screens.DifficultySelectionScreen;
import edu.asu.c3simulator.screens.EmploymentScreen;

public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new DifficultySelectionScreen(this);
		Screen secondScreen = new EmploymentScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
