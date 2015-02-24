package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.asu.c3simulator.screens.DifficultySelectionScreen;

public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new DifficultySelectionScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
	
	/**
	 * Clear the screen using a default mask and black clear colour.
	 * 
	 * Serves as a utility function and quick reference.
	 */
	public static void clearScreen()
	{
		int mask = GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT;
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(mask);
	}
}
