package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.asu.c3simulator.screens.*;

public class C3Simulator extends Game
{
	DifficultySelectionScreen firstScreen;
	EmploymentScreen secondScreen;
	TestScreen thirdScreen;
	@Override
	public void create()
	{
		firstScreen = new DifficultySelectionScreen(this);
		secondScreen = new EmploymentScreen(this);
		thirdScreen = new TestScreen(this);
		this.setScreen(firstScreen);
	}
}
