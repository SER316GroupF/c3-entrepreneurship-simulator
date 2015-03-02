package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.asu.c3simulator.widgets.Location;

/**
 * This screen creates and displays an instance of the Task Management class for the last
 * step of the Business Creation Guide.
 * 
 * @author Alyahya, Mohammed
 */
public class TasksScreen extends BusinessCreationGuideScreens
{
	Skin skin;
	Stage stage;
	
	public TasksScreen(Stage stage)
	{
		this.stage = stage;
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		TaskManagement taskManagementComponents = new TaskManagement(stage, skin);
		float taskManagementX = stage.getWidth() / 2
				- taskManagementComponents.getWidth() / 2;
		float taskManagementY = stage.getHeight() / 2
				- taskManagementComponents.getHeight() / 2;
		addActor(taskManagementComponents, new Location(taskManagementX, taskManagementY));
	}
}
