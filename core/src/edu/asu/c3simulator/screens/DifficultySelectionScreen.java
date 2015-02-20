package edu.asu.c3simulator.screens;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.screens.*;

/**
 * Allows the user to select the game difficulty. Each available difficulty is
 * displayed along with the details of the difficulty. Clicking an option will
 * transition the player to the main hub
 * 
 * @author Moore, Zachary
 * 
 */

public class DifficultySelectionScreen implements Screen
{
	private static class Button2 extends TextButton
	{
		Layout parent;

		public Button2(String text, Skin skin, Layout parent)
		{
			super(text, skin);
			this.parent = parent;
		}

		public float getPrefWidth()
		{
			if (parent == null)
				return super.getPrefWidth();
			else
				return parent.getPrefWidth();
		}

		// @formatter:off
		/*
		 * private float getMaxWidth(Table table) { float width = 0.0f;
		 * 
		 * for (Cell<?> cell : table.getCells()) { if (cell.getActor()
		 * instanceof Button2) continue; if (cell.getPrefWidth() > width) width
		 * = cell.getPrefWidth(); }
		 * 
		 * return width; }
		 */
		// @formatter:on
	}

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
	private Table choices;
	private Actor difficultyChoiceEasy;
	private Actor difficultyChoiceHard;

	public DifficultySelectionScreen(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		choices = new Table();
		difficultyChoiceEasy = createDifficultyChoiceEasy();
		difficultyChoiceHard = createDifficultyChoiceHard();
		choices.add(difficultyChoiceEasy).top();
		choices.add(difficultyChoiceHard).top().spaceLeft(75);
		advisor = new CornerAdvisor(ADVISOR_TEXT);

		choices.setTransform(true);
		choices.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);
		
		HomeButton home = new HomeButton(game);

		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		stage.addActor(advisor);
		stage.addActor(choices);

	}

	private Actor createDifficultyChoiceEasy()
	{
		// REFACTOR: Combine with #createDifficultyChoiceHard
		VerticalGroup difficultyChoiceEasy = new VerticalGroup();
		Actor descriptionEasy = createDifficultyChoiceDescriptionEasy();
		Actor titleEasy = createDifficultyTitle(difficultyChoiceEasy, "Easy");

		difficultyChoiceEasy.addActor(titleEasy);
		difficultyChoiceEasy.addActor(descriptionEasy);

		difficultyChoiceEasy.setTransform(true);

		difficultyChoiceEasy.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to main hub
				// TODO: Instantiate game instance
				System.out.println("Easy");
				game.setScreen(new MainHub(game));
			}
		});

		return difficultyChoiceEasy;
	}

	private Actor createDifficultyChoiceHard()
	{
		VerticalGroup difficultyChoiceHard = new VerticalGroup();
		Actor descriptionHard = createDifficultyChoiceDescriptionHard();
		Actor titleHard = createDifficultyTitle(difficultyChoiceHard, "Hard");

		difficultyChoiceHard.addActor(titleHard);
		difficultyChoiceHard.addActor(descriptionHard);

		difficultyChoiceHard.setTransform(true);

		difficultyChoiceHard.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to main hub
				// TODO: Instantiate game instance
				System.out.println("Hard");
				game.setScreen(new EmploymentScreen(game));
			}
		});

		return difficultyChoiceHard;
	}

	private Actor createDifficultyChoiceDescriptionEasy()
	{
		// REFACTOR: Combine with #createDifficultyChoiceDescriptionHard
		Table difficultyChoiceDescriptionEasy = new Table();

		List<Label> lines = new LinkedList<>();
		// REFACTOR: Load lines from file
		Label line1 = new Label("$8 000 startup", skin);
		Label line2 = new Label("Tips Display", skin);
		Label line3 = new Label("Modified Competition", skin);
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);
		for (Label line : lines)
		{
			line.setAlignment(Align.center);
			difficultyChoiceDescriptionEasy.add(line);
			difficultyChoiceDescriptionEasy.row();
		}

		return difficultyChoiceDescriptionEasy;
	}

	private Actor createDifficultyChoiceDescriptionHard()
	{
		Table difficultyChoiceDescriptionHard = new Table();

		List<Label> lines = new LinkedList<>();
		Label line1 = new Label("$2 000 startup", skin);
		Label line2 = new Label("Realistic Competition", skin);
		lines.add(line1);
		lines.add(line2);
		

		for (Label line : lines)
		{
			line.setAlignment(Align.center);
			difficultyChoiceDescriptionHard.add(line);
			difficultyChoiceDescriptionHard.row();
		}

		return difficultyChoiceDescriptionHard;
	}

	private Actor createDifficultyTitle(Layout parent, String titleText)
	{
		TextButton title = new Button2(titleText, skin, parent);
		title.align(Align.center);
		title.setDisabled(true);

		return title;
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
