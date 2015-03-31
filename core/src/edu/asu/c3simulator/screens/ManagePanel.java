package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * Creates a panel that allows for navigation through the management screens.
 * 
 * @author Reigel, Justin
 * 
 */
public class ManagePanel
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	float padding = 0.01f * DESIGN_HEIGHT;
	private static NavigationPanel navigation;
	private Skin skin;

	public ManagePanel(Game game, Stage stage)
	{
		skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		navigation = new NavigationPanel(game, skin);
		// Main buttons for manage panel
		navigation.addButton("Products", null);
		navigation.addButton("Growth", null);
		navigation.addButton("Employment",
				AllManagementScreens.EMPLOYMENT.getInstance());
		navigation.addButton("Marketing",
				AllManagementScreens.MARKETING.getInstance());
		// Products Sub Buttons
		navigation.addSubButton("Products", "Pre-Market",
				AllManagementScreens.PRE_MARKET.getInstance());
		navigation.addSubButton("Products", "Current Products",
				AllManagementScreens.CURRENT_PRODUCTS.getInstance());
		navigation.addSubButton("Products", "Retired Products",
				AllManagementScreens.RETIRED_PRODUCTS.getInstance());
		navigation.addSubButton("Products", "Product History",
				AllManagementScreens.PRODUCT_HISTORY.getInstance());
		// Growth Sub Buttons
		navigation.addSubButton("Growth", "Supply", null);
		navigation.addSubButton("Growth", "Demand", null);

		navigation.setPosition(0.01f * stage.getWidth(),
				stage.getHeight() * .7f);

		stage.addActor(navigation);

	}
}
