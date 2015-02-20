package edu.asu.c3simulator.screens;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.NavigationPanel;
/**
 * This class displays the main hub, allowing the player to navigate to their Businesses. After: DifficultySelection, HomeButton. 
 * 
 * @author Reigel, Justin
 */
public class MainHub implements Screen
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
	private Actor company;
	private Actor difficultyChoiceHard;
	private NavigationPanel navigation;
	private BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));

	public MainHub(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		choices = new Table();
		company = Company();
		//difficultyChoiceEasy = createDifficultyChoiceEasy();
		//difficultyChoiceHard = createDifficultyChoiceHard();
		
		//choices.add(difficultyChoiceHard).top().spaceLeft(75);
		advisor = new CornerAdvisor(ADVISOR_TEXT, Color.BLACK);
		navigation = new NavigationPanel(game, skin);

		navigation.addButton("Businesses", game.getScreen() );
		navigation.addButton("Manage", game.getScreen());
		navigation.addButton("Council", game.getScreen());
		navigation.addSubButton("Businesses", "1.Create New", game.getScreen() );
		navigation.addSubButton("Businesses", "2.Create New", game.getScreen() );
		navigation.addSubButton("Businesses", "3.Create New", game.getScreen() );
		
		Label playerLabel = new Label( ("Player"), new Label.LabelStyle(font, new Color(Color.DARK_GRAY)) );
		playerLabel.setPosition(50, 680);
		
		choices.setTransform(true);
		choices.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);

		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		navigation.setPosition(navigation.getWidth()/2 - padding, 500);
		
		stage.addActor(playerLabel);
		stage.addActor(advisor);
		stage.addActor(company);
		stage.addActor(navigation);
		stage.addActor(Council());
		stage.addActor(playerInfo());
		

	}
	//Creates the business image in the middle of the screen. (Note: not meant to be clickable)
	private Actor Company()
	{
		Table company = new Table();
		Image companyIcon;
		float companyLeft;
		float companyBottom;
		FileHandle iconLocation = Gdx.files.internal("images/CompanyIcon.png");
		Texture iconTexture = new Texture(iconLocation);
		companyIcon = new Image(iconTexture);
		company.add(companyIcon).size(800, 520);
		//sets the height and width. Estimated that the image has 1/5 of white space on bottom, so actual image is upper 4/5. 
		companyLeft = DESIGN_WIDTH * 8/10 - companyIcon.getPrefWidth()/2;
		companyBottom = companyIcon.getHeight() * 4/5;
		company.setPosition(companyLeft, companyBottom);

		return company;
	}
	//Creates the council image in the bottom left of the screen. (Note: not meant to be clickable)
	private Actor Council()
	{
		Table council = new Table();
		Image companyIcon;
		float councilLeft;
		float councilBottom;
		FileHandle iconLocation = Gdx.files.internal("images/Council.png");
		Texture iconTexture = new Texture(iconLocation);
		companyIcon = new Image(iconTexture);
		council.add(companyIcon).size(200);
		//sets the image to the bottom left then shifts 50 pixels to the right
		councilLeft = companyIcon.getPrefWidth()/2 + 50;
		councilBottom = companyIcon.getHeight();
		council.setPosition(councilLeft, councilBottom);

		return council;
	}
	private Actor playerInfo()
	{
		Table playerInfo = new Table();
		List<Label> lines = new LinkedList<>();
		// REFACTOR: Load lines from file
		//Every second label is a description related to the first
		
		
		Label line1 = new Label(String.format("%-20s %s", "Status: ",  "Plebian"), new Label.LabelStyle(font, new Color(Color.BLACK)));
		Label line2 = new Label(String.format("%-21s %s", "Net Worth: ",  "8000"), new Label.LabelStyle(font, new Color(Color.BLACK)));
		//TODO: Fix formatting. As the year increases, the label will push the text to the left of the screen and ruin the formatting.
		Label line3 = new Label(String.format("%-9s %s", "Date: ",  "January, Year 1"),new Label.LabelStyle(font, new Color(Color.BLACK)));
		lines.add(line1);
		lines.add(line2);
		lines.add(line3);
		//iterate through the labels and place every second label on the right
		for (int i = 0; i < lines.size(); i++)
		{
			lines.get(i).setAlignment(Align.left);
			playerInfo.add(lines.get(i));
			playerInfo.row();
		}
		playerInfo.setPosition(200, 600);
		return playerInfo;
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
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
