package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanel;
import edu.asu.c3simulator.widgets.PlayerStatusDisplay;

public class RetiredProducts implements Screen
{
	private static final String ADVISOR_TEXT = "This is a test of TextAreaX. This is intended to cover multiple lines at a width of 200px. This is the second extention";
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;

	@SuppressWarnings("unused")
	private Game game;

	private Stage stage;
	private Skin skin;
	private CornerAdvisor advisor;
	private Table table;
	private NavigationPanel navigation;
	private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
	
	public RetiredProducts(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		//difficultyChoiceEasy = createDifficultyChoiceEasy();
		//difficultyChoiceHard = createDifficultyChoiceHard();
		
		//choices.add(difficultyChoiceHard).top().spaceLeft(75);
		advisor = new CornerAdvisor(ADVISOR_TEXT);
		navigation = new NavigationPanel(game, skin);

		navigation.addButton("Products", game.getScreen() );
		navigation.addButton("Growth", game.getScreen());
		navigation.addButton("Employment", game.getScreen());
		navigation.addButton("Marketing", game.getScreen());
		navigation.addSubButton("Products", "Pre-Market", game.getScreen() );
		navigation.addSubButton("Products", "Current Products", game.getScreen() );
		navigation.addSubButton("Products", "Retired Products", game.getScreen() );
		navigation.addSubButton("Growth", "Current Products", game.getScreen() );
		navigation.addSubButton("Growth", "Demand", game.getScreen() );
		
		table = new Table();
		table.debug();
		table.add(ProductPane()).fill().expand();
		table.setPosition(300, 0);

		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		navigation.setPosition(navigation.getWidth()/2 - padding, 500);
		
		HomeButton home = new HomeButton(game);
		
		stage.addActor(home);
		stage.addActor(ProductPane().debug());
		stage.addActor(advisor);
		stage.addActor(navigation);
		//stage.addActor(table);
		

	}
	private ScrollPane ProductPane()
	{
		Table productTable = new Table();
		final Label text = new Label("Designs", skin);
		text.setAlignment(Align.left);
		text.setWrap(true);
		productTable.add(text);
		productTable.row();
		final ScrollPane scroller = new ScrollPane(productTable);
		scroller.setPosition(100,100);
		return scroller;
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
