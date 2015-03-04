package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.util.Player;

/**
 * This allows the user to create a new game and enter in their player name. 
 * 
 * @author ShantalOlono
 */
public class NewGame extends ApplicationAdapter implements TextInputListener 
{
	private TextButton NewGameButton;
	private Player selectedPlayer;
	
	public NewGame(Stage stage, Skin skin)
	{
		NewGameButton = new TextButton("New Game", skin);
		final NewGame thisclass = this;
		selectedPlayer = new Player("Player Test");
		
		NewGameButton.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.input.getTextInput(thisclass, "Please enter name: ", "default text");
			}
		});
		
		stage.addActor(NewGameButton);
	}
	
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}

	public void setSelectedPlayer(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}

	/**
	 * This allows the user to choose the position of the button.
	 * 
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public void setPosition(float x, float y)
	{
		NewGameButton.setPosition(x,y);
	}
	
	@Override 
	public void create()
	{
		
	}
	@Override 
	public void render()
	{
		
	}

	@Override
	public void input(String text) 
	{
		selectedPlayer.setName(text);
		System.out.println("Player Name: "+selectedPlayer.getName());
	}

	@Override
	public void canceled() 
	{
		System.out.println("The operation was canceled");
	}

}