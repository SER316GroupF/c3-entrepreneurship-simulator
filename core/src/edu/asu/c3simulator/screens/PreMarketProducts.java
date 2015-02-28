package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanel;
import edu.asu.c3simulator.widgets.SellCompany;

/**
 * @author Reigel, Justin
 * 
 */
public class PreMarketProducts implements Screen
{
	private static final String ADVISOR_TEXT = "This is a PreMarketProducts screen";
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

	public PreMarketProducts(final Game game)
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
		SellCompany sell = new SellCompany(game, skin);

		stage.addActor(sell);

		stage.addActor(home);
		stage.addActor(advisor);
		stage.addActor(table);
	}
	public void initialize(){
		manage = new ManagePanel(game);
		stage.addActor(manage);
	}

	private Actor ProductPane()
	{
		TextButton text = new TextButton("Designs", skin);
		text.setDisabled(true);
		text.setColor(0.5f, 0.5f, 0.5f, 1.0f);

		// text color can be changed if screen color is changed (default is
		// white)
		// text.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		Table productTable = new Table();
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

		ScrollPane chosenTasksScroll = new ScrollPane(productTable, skin);

		Table leftSection = new Table();
		leftSection.debug();
		leftSection.add(text).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		return leftSection;
	}

	private Actor ProductInfo()
	{

		Table productInfo = new Table(skin);
		Label text = new Label("Production cost: ", skin);
		productInfo.add(text).left().row();
		Label text2 = new Label("Labor: ", skin);
		productInfo.add(text2).left().row();
		Label text3 = new Label("Design employee", skin);
		productInfo.add(text3).left().row();
		// TODO: dropdown box
		Label text4 = new Label("Materials", skin);
		productInfo.add(text4).left().row();
		Label text5 = new Label("Quality", skin);
		// TODO: sideScrolling Bar
		productInfo.add(text5).left().row();
		Label text6 = new Label("Efficiency", skin);
		productInfo.add(text6).left().row();
		Label text7 = new Label("Selling Price", skin);
		productInfo.add(text7).left().row();
		// TODO: edit button
		// TODO: relaunch button
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
