package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanelFactory;
import edu.asu.c3simulator.widgets.ProductDesigns;
import edu.asu.c3simulator.widgets.ProductInfoPanel;

/**
 * Management screen that provides the player with information and allows for editing with
 * pre market products.
 * 
 * @author Reigel, Justin
 * 
 */
public class PreMarketProducts implements SimulationScreen
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private Stage stage;
	private Skin skin;
	private Game game;
	private Table table;
	
	public PreMarketProducts(final Game game)
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
	 * Creates a table of the pre market product icons in a panel that the can be scrolled
	 * through.
	 * 
	 * @return Table
	 */
	private Actor ProductPane()
	{
		TextButton text = new TextButton("Designs", skin);
		text.setDisabled(true);
		text.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		Table productTable = new Table();
		Table testTable = new Table();
		ProductDesigns newProductPopupWindow = new ProductDesigns(testTable, stage, skin);
		// button for creating new products
		Table createNewTable = new Table();
		
		FileHandle img = Gdx.files.internal("images/Create-new.png");
		Image newProductButton = new Image(new Texture(img));
		newProductButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				newProductPopupWindow.displayProductDesignOptions();
			}
		});
		productTable.add(testTable);
		createNewTable.add(newProductButton);
		createNewTable.row();
		createNewTable.add(new Label("Create new", skin));
		productTable.add(createNewTable);
		
		ScrollPane chosenTasksScroll = new ScrollPane(productTable, skin);
		
		Table leftSection = new Table();
		leftSection.debug();
		leftSection.add(text).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		return leftSection;
	}
	
	/**
	 * Creates a table with detailed information about the pre market products in a panel
	 * that the can be scrolled through.
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
		Table productInfo = productInfoPanel.getProductInfo();
		// TODO: add functionality so buttons will open appropriate windows when
		// clicked.
		// TODO: add functionality so buttons will open appropriate windows when
		// clicked.
		Table productButtons = new Table();
		FileHandle edit = Gdx.files.internal("images/edit.png");
		productButtons.add(new Image(new Texture(edit))).left().size(100);
		FileHandle launch = Gdx.files.internal("images/launch.png");
		productButtons.add(new Image(new Texture(launch))).right().size(100)
				.space(Value.percentWidth(1.4f));
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
