package edu.asu.c3simulator.testing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.WidgetFactory;

/**
 * Development screen to play with and test new features and graphic elements before using
 * them in production code.
 * 
 * This class should not be deployed.
 * 
 * @author Moore, Zachary
 * 
 */
public class Sandbox implements Screen
{
	/**
	 * Width and Height at which this screen was designed. Can be used in resize
	 * operations.
	 */
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	
	/** Handles all components of this screen (rendering, updating, resizing, etc) */
	private Stage stage;
	
	/** Specifies textures to use for default widgets such as Buttons and Labels */
	@SuppressWarnings("unused")
	private Skin skin;
	
	@SuppressWarnings("unused")
	private SpriteBatch batch = new SpriteBatch();
	
	/**
	 * @param game
	 */
	public Sandbox()
	{
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		sandboxBulletedList();
	}
	
	/**
	 * Contains a bulleted list component on the screen, with multiple bullet items.
	 * Manufactures the list using
	 * {@link WidgetFactory#createBulletedList(Texture, String...)}
	 */
	private void sandboxBulletedList()
	{
		String texturePath = "images/bullet_white.png";
		Texture bulletTexture = new Texture(texturePath);
		Actor list = WidgetFactory.createBulletedList(bulletTexture, "Item 1", "Item 2",
				"Item 3");
		
		list.setPosition(500, 500);
		stage.addActor(list);
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void pause()
	{
		
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
	public void resume()
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
	
}
