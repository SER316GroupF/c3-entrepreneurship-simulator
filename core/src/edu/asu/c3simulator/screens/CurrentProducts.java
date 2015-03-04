/**
 * 
 */
package edu.asu.c3simulator.screens;

import java.text.DecimalFormat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
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
import edu.asu.c3simulator.simulation.Product;
import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.SellCompany;

/**
 * Management screen that provides the player with information and allows for
 * editing with current products.
 * 
 * @author Reigel, Justin
 * 
 */
public class CurrentProducts implements Screen
{
	private static final String ADVISOR_TEXT = "This is the Current Products screen";
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private Stage stage;
	private Skin skin;
	private Game game;
	private CornerAdvisor advisor;
	private Table table;
	private BitmapFont font = new BitmapFont(
			Gdx.files.internal("fonts/arial32_superSample.fnt"));
	private ManagePanel manage;

	public CurrentProducts(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		advisor = new CornerAdvisor(ADVISOR_TEXT);
		// creates and adds manage Panel to the current screen
		// manage = new ManagePanel(game);

		table = new Table();
		table.setSize(900, 500);
		table.add(ProductPane()).fill().expand();
		table.setPosition(300, 50);
		table.add(ProductInfo()).size(300, 500);

		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);

		HomeButton home = new HomeButton(game);

		stage.addActor(home);
		stage.addActor(advisor);
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
	 * Creates a table of the current product icons in a panel that the can be
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
		productTable.add(new HomeButton(game));
		productTable.add(new HomeButton(game));
		productTable.row();
		productTable.add(new HomeButton(game));
		productTable.add(new HomeButton(game));
		productTable.row();
		productTable.add(new HomeButton(game));
		productTable.row();
		productTable.add(new HomeButton(game));
		// button for creating new products
		Table createNewTable = new Table();
		FileHandle img = Gdx.files.internal("images/Create-new.png");
		createNewTable.add(new Image(new Texture(img)));
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
	 * Creates a table with detailed information about the current products in a
	 * panel that the can be scrolled through.
	 * 
	 * @return Table
	 */
	private Actor ProductInfo()
	{
		// TODO: remove product interface, implement actual product class,
		// refresh product info
		// This is an implementation of the interface, ends at line 228
		Product testProduct = new Product() {
			public float getProductionCost()
			{
				DecimalFormat df = new DecimalFormat("#.##");
				return Float.valueOf(df.format(11.00));
			}

			public float getMaterialCost()
			{
				DecimalFormat df = new DecimalFormat("#.##");
				return Float.valueOf(df.format(8.50));
			}

			public int getEfficiency()
			{
				return 32;
			}

			public float getSellingPrice()
			{
				DecimalFormat df = new DecimalFormat("#.##");
				return Float.valueOf(df.format(16.50));
			}

			@Override
			public Product getProduct()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getProductName()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public float getDistributionCost()
			{
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getLabor()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String setProductName()
			{
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setProductionCost()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setMaterialCost()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setDistributionCost()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setSellingPrice()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setEfficiency()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void setLabor()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void retireProduct()
			{
				// TODO Auto-generated method stub

			}

			@Override
			public C3Simulation getSimulation()
			{
				// TODO Auto-generated method stub
				return null;
			}
		};
		Table productInfo = new Table(skin);
		Label text = new Label("Production cost: $"
				+ testProduct.getProductionCost(), skin);
		productInfo.add(text).left().row();
		Label text2 = new Label("Labor: ", skin);
		productInfo.add(text2).left().row();
		Label text3 = new Label("Design employee", skin);
		productInfo.add(text3).left().row();
		// TODO: place real employees in dropdown box, add functionality when
		// selected
		SelectBox<String> businessSelectionBox = new SelectBox<String>(skin);

		businessSelectionBox.setItems("Select..", "Employee 1", "Employee 2",
				"Employee 3");
		businessSelectionBox.setSelected("Select..");
		productInfo.add(businessSelectionBox).row();
		productInfo.add("").row();

		Label text4 = new Label("Materials: $" + testProduct.getMaterialCost(),
				skin);
		productInfo.add(text4).left().row();
		productInfo.add("").row();
		Label text6 = new Label("Efficiency: " + testProduct.getEfficiency()
				+ "/hr", skin);
		productInfo.add(text6).left().row();
		productInfo.add("").row();
		Label text7 = new Label("Selling Price: $"
				+ testProduct.getSellingPrice(), skin);
		productInfo.add(text7).left().row();
		productInfo.add("").expand().row();
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
