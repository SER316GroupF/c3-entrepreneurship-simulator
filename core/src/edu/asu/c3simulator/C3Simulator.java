package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.screens.BusinessCreationGuide;
import edu.asu.c3simulator.screens.CompanyPanel;
import edu.asu.c3simulator.screens.DifficultySelectionScreen;
import edu.asu.c3simulator.screens.MarketingScreen;
import edu.asu.c3simulator.screens.TaskManagement;
import edu.asu.c3simulator.screens.TaskManagementScreen;

public class C3Simulator extends Game
{
	@Override
	public void create()
	{
		Screen firstScreen = new MarketingScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		super.render();
	}
}
