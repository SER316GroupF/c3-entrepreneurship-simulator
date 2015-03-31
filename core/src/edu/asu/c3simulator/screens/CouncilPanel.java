package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * Navigation Panel for council screens
 * 
 * @author Alyahya, Mohammed
 * 
 */
public class CouncilPanel
{
	private Skin skin;

	public CouncilPanel(Game game, Stage stage)
	{
		skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Company", null);
		navigationPanel.addSubButton("Company", "Business",
				AllManagementScreens.COMPANY_PANEL.getInstance());
		//TODO: Link Assets Screen when it is created. 
		navigationPanel.addSubButton("Company", "Assets", null);
		navigationPanel.addButton("Tasks", null);
		navigationPanel.addSubButton("Tasks", "Manage",
				AllManagementScreens.TASK_MANAGEMENT.getInstance());
		navigationPanel.addSubButton("Tasks", "Completed",
				AllManagementScreens.COMPLETED_TASKS.getInstance());
		navigationPanel.showSubButtonsFor("Tasks");

		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight()
				- (0.3f * stage.getHeight()));

		stage.addActor(navigationPanel);

	}
}
