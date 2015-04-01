package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.asu.c3simulator.screens.AllManagementScreens;

public class NavigationPanelFactory
{
	public static NavigationPanel getCouncilNavigationPanel(Game game, Stage stage)
	{
		Skin skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

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

		navigationPanel.setPosition(0.01f * stage.getWidth(), 0.7f * stage.getHeight());

		return navigationPanel;
	}
	
	public static NavigationPanel getBusinessCreationGuideNavigationPanel(Game game, Stage stage)
	{
		Skin skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Industry", AllManagementScreens.INDUSTRY.getInstance());
		navigationPanel.addButton("Direction", AllManagementScreens.BUSINESS_DIRECTION.getInstance());
		navigationPanel.addButton("Funding", AllManagementScreens.FUNDING.getInstance());
		navigationPanel.addButton("Tasks", AllManagementScreens.TASKS.getInstance());
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight()
				- (0.3f * stage.getHeight()));

		navigationPanel.setPosition(0.01f * stage.getWidth(), 0.7f * stage.getHeight());

		return navigationPanel;
	}
	
	public static NavigationPanel getManagementNavigationPanel(Game game, Stage stage)
	{
		Skin skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Products", null);
		navigationPanel.addButton("Growth", null);
		navigationPanel.addButton("Employment",
				AllManagementScreens.EMPLOYMENT.getInstance());
		navigationPanel.addButton("Marketing",
				AllManagementScreens.MARKETING.getInstance());
		/** Products Sub Buttons*/
		navigationPanel.addSubButton("Products", "Pre-Market",
				AllManagementScreens.PRE_MARKET.getInstance());
		navigationPanel.addSubButton("Products", "Current Products",
				AllManagementScreens.CURRENT_PRODUCTS.getInstance());
		navigationPanel.addSubButton("Products", "Retired Products",
				AllManagementScreens.RETIRED_PRODUCTS.getInstance());
		navigationPanel.addSubButton("Products", "Product History",
				AllManagementScreens.PRODUCT_HISTORY.getInstance());
		/** Growth Sub Buttons*/
		navigationPanel.addSubButton("Growth", "Supply", null);
		navigationPanel.addSubButton("Growth", "Demand", null);

		navigationPanel.setPosition(0.01f * stage.getWidth(), 0.7f * stage.getHeight());

		return navigationPanel;
	}
}
