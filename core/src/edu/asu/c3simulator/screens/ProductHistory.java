package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanelFactory;
import edu.asu.c3simulator.widgets.ProductInfoPanel;

/**
 * Management screen that provides the player with information about the history of past
 * products.
 * 
 * @author Reigel, Justin
 * 
 */
public class ProductHistory implements SimulationScreen
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private Stage stage;
	private Skin skin;
	private Game game;
	private Table table;
	
	public ProductHistory(final Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		// TODO: implement advisor when advisor is implemented in master_testing
		// or higher.
		
		table = new Table();
		table.setSize(900, 500);
		table.add(ProductPane()).fill().expand();
		table.setPosition(300, 50);
		table.add(ProductInfo()).size(300, 500);
		
		HomeButton homeButton = new HomeButton(game);
		
		stage.addActor(homeButton);
		stage.addActor(table);
	}
	
	/**
	 * Initializes management panel. Called during initialization to avoid infinite loops
	 * with AllManagementScreens.
	 */
	@Override
	public void createNavigationPanel()
	{
		stage.addActor(NavigationPanelFactory.getManagementNavigationPanel(game, stage));
	}
	
	/**
	 * Creates a table of the product history icons in a panel that the can be scrolled
	 * through.
	 * 
	 * @return Table
	 */
	private Actor ProductPane()
	{
		TextButton text = new TextButton("Design History", skin);
		text.setDisabled(true);
		text.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		Table productTable = new Table();
		// TODO: replace with real products and product icons.
		productTable.add(new HomeButton(game));
		productTable.add(new HomeButton(game));
		productTable.add(new HomeButton(game));
		productTable.row();
		
		ScrollPane chosenTasksScroll = new ScrollPane(productTable, skin);
		
		Table leftSection = new Table();
		leftSection.debug();
		leftSection.add(text).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		return leftSection;
	}
	
	/**
	 * Creates a table with detailed information about the product history in a panel that
	 * the can be scrolled through.
	 * 
	 * @return Table
	 */
	private Actor ProductInfo()
	{
		
		// TODO: backend. remove product interface, implement actual product
		// class,
		// replace selected employees in dropdown with real employees,
		// refresh product info
		ProductInfoPanel productInfoPanel = new ProductInfoPanel(skin);
		productInfoPanel.addHistoryPanel();
		Table productInfo = productInfoPanel.getProductInfo();
		// TODO: future implementation, perhaps add buttons.
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
		throw new UnsupportedOperationException("The method is not implemented yet.");
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
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
}
