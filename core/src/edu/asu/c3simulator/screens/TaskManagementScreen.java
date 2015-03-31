package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * Creates and displays an instance of the Task Management class for the Council
 * Section.
 * 
 * @author Alyahya, Mohammed
 */
public class TaskManagementScreen implements SimulationScreen
{
	private Game game;
	private Stage stage;
	private Skin skin;

	public TaskManagementScreen(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		TaskManagement taskManagementComponents = new TaskManagement(stage,
				skin);

		// TODO add Corner Advisor
		HomeButton home = new HomeButton(game);

		stage.addActor(home);
		stage.addActor(taskManagementComponents);
	}

	@Override
	public void createNavigationPanel()
	{
		new CouncilPanel(game, stage);
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
		// do nothing
	}

	@Override
	public void resume()
	{
		// do nothing
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
}
