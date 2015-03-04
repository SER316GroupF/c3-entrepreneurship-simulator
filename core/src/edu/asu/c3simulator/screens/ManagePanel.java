/**
 * 
 */
package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * @author Reigel, Justin
 * 
 */
public class ManagePanel extends Table
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	float padding = 0.01f * DESIGN_HEIGHT;
	private static NavigationPanel navigation;
	private Skin skin;

	public ManagePanel(Game game)
	{
		skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		navigation = new NavigationPanel(game, skin);
		// Main buttons for manage panel
		navigation.addButton("Products", null);
		navigation.addButton("Growth", null);
		navigation.addButton("Employment", null);
		navigation.addButton("Marketing", null);
		// Products Sub Buttons
		navigation.addSubButton("Products", "Pre-Market", AllManagementScreens.PRE_MARKET.getInstance());
		navigation.addSubButton("Products", "Current Products", AllManagementScreens.CURRENT_PRODUCTS.getInstance());
		navigation.addSubButton("Products", "Retired Products", AllManagementScreens.RETIRED_PRODUCTS.getInstance());
		// Growth Sub Buttons
		navigation.addSubButton("Growth", "Current Products Growth", null);
		navigation.addSubButton("Growth", "Demand", null);

		this.add(navigation);
		this.setPosition(navigation.getWidth() / 2 + 70, 450);
	}
}
