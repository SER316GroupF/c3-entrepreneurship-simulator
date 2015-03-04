package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.util.Business;

/**
 * This allows the user to change the name of the business that they have created. 
 * 
 * @author ShantalOlono
 */
public class RenameCompany extends ApplicationAdapter implements TextInputListener 
{
	private TextButton renameButton;
	private Business selectedBusiness;
	
	public RenameCompany(Stage stage, Skin skin)
	{
		renameButton = new TextButton("Rename", skin);
		final RenameCompany thisclass = this;
		selectedBusiness = new Business("Business Test");
		
		renameButton.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.input.getTextInput(thisclass, "Title", "default text");
			}
		});
		
		stage.addActor(renameButton);
	}
	
	public Business getSelectedBusiness() {
		return selectedBusiness;
	}

	public void setSelectedBusiness(Business selectedBusiness) {
		this.selectedBusiness = selectedBusiness;
	}

	/**
	 * This allows the user to choose the position of the button.
	 * 
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public void setPosition(float x, float y)
	{
		renameButton.setPosition(x,y);
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
		selectedBusiness.setName(text);
		System.out.println("The new business name is "+selectedBusiness.getName());
	}

	@Override
	public void canceled() 
	{
		System.out.println("The operation was canceled");
	}

}