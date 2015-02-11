package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.RandomEvent;

/* 
 * @author Olono, Shantal
 * 
 */
public class TestScreen implements Screen
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;

	@SuppressWarnings("unused")
	private Game game;

	private Stage stage;
	private Skin skin;

	public TestScreen(C3Simulator game)
	{
		this.game = game;
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		RandomEvent rand = new RandomEvent();
		Label employee1 = new Label("test", skin);
		String newText = "";

		while (newText.equals(""))
		{
			newText = rand.getEvent(10);
			employee1.setText(newText);
		}

		employee1.setPosition(DESIGN_WIDTH / 2, DESIGN_HEIGHT / 2);
		HomeButton home = new HomeButton();

		stage.addActor(home);
		stage.addActor(employee1);
		// this.stage.add(rand);
	}

	@Override
	public void render(float delta)
	{
		stage.act(delta);
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
}
