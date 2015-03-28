package edu.asu.c3simulator.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.Product;
import edu.asu.c3simulator.testing.stubs.ProductTestingStub;
import edu.asu.c3simulator.util.PlayerImp;
//import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanel;
import edu.asu.c3simulator.widgets.ProductInfoPanel;
//import edu.asu.c3simulator.widgets.PlayerStatusDisplay;
import edu.asu.c3simulator.widgets.SellCompany;

/**
 * Management screen that provides the player with information and allows for
 * editing with retired products.
 * 
 * @author Reigel, Justin
 * 
 */
public class RetiredProducts implements Screen
{
	private static final String ADVISOR_TEXT = "This is a test of the Retired Products Screen";
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;

	@SuppressWarnings("unused")
	private Game game;

	private Stage stage;
	private Skin skin;
	// TODO: uncomment cornerAdvisor and playerStatusDisplay when they get to
	// master testing.
	// private CornerAdvisor advisor;
	private Table table;
	private BitmapFont font = new BitmapFont(
			Gdx.files.internal("fonts/arial32_superSample.fnt"));
	private ManagePanel manage;

	public RetiredProducts(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		// advisor = new CornerAdvisor(ADVISOR_TEXT);

		table = new Table();
		table.setSize(900, 500);
		table.add(ProductPane()).fill().expand();
		table.setPosition(300, 50);
		table.add(ProductInfo()).size(300, 500);

		float padding = 0.01f * DESIGN_HEIGHT;
		// float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		// float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() -
		// padding;
		// advisor.setPosition(advisorLeft, advisorBottom);

		HomeButton home = new HomeButton(game);

		stage.addActor(home);
		// stage.addActor(advisor);
		stage.addActor(table);
	}

	/**
	 * Initializes management panel. Called during initialization to avoid
	 * infinite loops with AllManagementScreens.
	 */
	public void initialize()
	{
		manage = new ManagePanel(game);
		stage.addActor(manage);
	}

	/**
	 * Creates a table of the retired product icons in a panel that the can be
	 * scrolled through.
	 * 
	 * @return Table
	 */
	private Actor ProductPane()
	{
		TextButton text = new TextButton("Designs", skin);
		text.setDisabled(true);
		text.setColor(0.5f, 0.5f, 0.5f, 1.0f);

		Table productTable = new Table();
		// TODO: replace with real products and product icons.
		productTable.add(new HomeButton(game));

		ScrollPane chosenTasksScroll = new ScrollPane(productTable, skin);

		Table leftSection = new Table();
		leftSection.debug();
		leftSection.add(text).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		return leftSection;
	}

	/**
	 * Creates a table with detailed information about the retired products in a
	 * panel that the can be scrolled through.
	 * 
	 * @return Table
	 */
	private Actor ProductInfo()
	{
		// TODO: backend. remove product interface, implement actual product
		// class, replace selected employees in dropdown with real employees,
		// refresh product info
		ProductInfoPanel productInfoPanel = new ProductInfoPanel(skin);
		Table productInfo = productInfoPanel.getProductInfo();
		// TODO: add functionality so buttons will open appropriate windows when
		// clicked.
		// TODO: add functionality so buttons will open appropriate windows when
		// clicked.
		Table productButtons = new Table();
		FileHandle edit = Gdx.files.internal("images/edit.png");
		productButtons.add(new Image(new Texture(edit))).left().size(100);
		FileHandle relaunch = Gdx.files.internal("images/re-launch.png");
		productButtons.add(new Image(new Texture(relaunch))).right().size(100)
				.space(Value.percentWidth(1f));
		productInfo.add(productButtons).left();
		ScrollPane productPane = new ScrollPane(productInfo.top(), skin);
		return productPane;
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}

	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException(
				"The method is not implemented yet.");
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
}
