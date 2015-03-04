package edu.asu.c3simulator.screens;

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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.util.PlayerImp;
import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.NavigationPanel;
import edu.asu.c3simulator.widgets.PlayerStatusDisplay;

/**
 * This class displays the main hub, allowing the player to navigate to their
 * Businesses. Located after: DifficultySelection, and or HomeButton.
 * 
 * @author Reigel, Justin
 */
public class MainHub implements Screen
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
	private Table choices;
	private Actor company;
	private NavigationPanel navigation;
	private BitmapFont font = new BitmapFont(
			Gdx.files.internal("fonts/arial32_superSample.fnt"));
	public PlayerStatusDisplay player;

	public MainHub(Game game)
	{
		this.game = game;

		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH,
				DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

		choices = new Table();
		company = Company();

		AllManagementScreens.initialize(game);

		advisor = new CornerAdvisor(ADVISOR_TEXT, Color.BLACK);
		navigation = new NavigationPanel(game, skin);

		navigation.addButton("Businesses", game.getScreen());
		navigation.addButton("Manage",
				AllManagementScreens.PRE_MARKET.getInstance());
		navigation.addButton("Council", game.getScreen());
		navigation.addSubButton("Businesses", "1.Create New", game.getScreen());
		navigation.addSubButton("Businesses", "2.Create New", game.getScreen());
		navigation.addSubButton("Businesses", "3.Create New", game.getScreen());

		Label playerLabel = new Label("Player", skin);
		playerLabel.setPosition(40, 680);

		choices.setTransform(true);
		choices.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);

		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		navigation.setPosition(navigation.getWidth() / 2 - padding, 500);
		System.out.println("Create new home screen");

		stage.addActor(playerLabel);
		stage.addActor(advisor);
		stage.addActor(company);
		stage.addActor(navigation);
		stage.addActor(Council());
		stage.addActor(playerInfo());

	}

	/**
	 * Creates the business image in the middle of the screen. (Note: image is
	 * not meant to be clickable)
	 * 
	 * @return Table
	 */
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
		// sets the height and width. Estimated that the image has 1/5 of white
		// space on bottom, so actual image is upper 4/5.
		companyLeft = DESIGN_WIDTH * 8 / 10 - companyIcon.getPrefWidth() / 2;
		companyBottom = companyIcon.getHeight() * 4 / 5;
		company.setPosition(companyLeft, companyBottom);

		return company;
	}

	/**
	 * Creates the council image in the bottom left of the screen. (Note: not
	 * meant to be clickable)
	 * 
	 * @return Table
	 */
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
		// sets the image to the bottom left then shifts 50 pixels to the right
		councilLeft = companyIcon.getPrefWidth() / 2 + 50;
		councilBottom = companyIcon.getHeight();
		council.setPosition(councilLeft, councilBottom);

		return council;
	}

	/**
	 * Creates an instance of player status display in the upper left corner of
	 * the screen
	 * 
	 * @return Table
	 */
	private Actor playerInfo()
	{
		// TODO: Replace c3Simulation interface with actual backend

		C3Simulation sim = new C3Simulation() {
			public String getSimulationDate()
			{
				return ("Jan, Year: 1");
			}
		};
		PlayerStatusDisplay playerStatus = new PlayerStatusDisplay(
				new PlayerImp("Plebian", 8000, 2000, sim));

		playerStatus.setPosition(200, 600);
		return playerStatus;
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
