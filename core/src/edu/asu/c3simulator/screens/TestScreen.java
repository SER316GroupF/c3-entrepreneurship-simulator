package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.RandomEvents;
import edu.asu.c3simulator.widgets.RenameCompany;

/* 
 * @author Olono, Shantal
 * 
 */
public class TestScreen implements Screen, TextInputListener
{
	String text;
	private static final String ADVISOR_TEXT = "This screen is designed to test the Random Event Generator";
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;

	@SuppressWarnings("unused")
	private Game game;

	private Stage stage;
	private Skin skin;

	public TestScreen(final Game game)
	{
		this.game = game;
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		RandomEvents rand = new RandomEvents();
		//RenameCompany rename = new RenameCompany();
		//stage.addAction(rename);
		
		String newText = "";

		while (newText.equals(""))
		{
			newText = rand.getEvent(10);
		}
		Window ranEventWin = new Window(newText, skin);
		ranEventWin.setPosition(400,500);
		ranEventWin.setSize(300,200);

		HomeButton home = new HomeButton();
		CornerAdvisor advisor = new CornerAdvisor(ADVISOR_TEXT);
		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		home.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to main hub
				// TODO: Instantiate game instance
				game.setScreen(new DifficultySelectionScreen(game));
			}
		});

		RenameCompany renameButton = new RenameCompany(stage, skin);
		renameButton.setPosition(700, 200);
	
		stage.addActor(advisor);

		stage.addActor(home);
		stage.addActor(ranEventWin);
		// this.stage.add(rand);
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
	public void show()
	{
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause()
	{
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void resume()
	{
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}

	@Override
	public void input(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
