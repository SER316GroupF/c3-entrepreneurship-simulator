package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.screens.*;

public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new DifficultySelectionScreen(this);
		Screen secondScreen = new EmploymentScreen(this);
		Screen thirdScreen = new TestScreen(this);
		this.setScreen(secondScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
