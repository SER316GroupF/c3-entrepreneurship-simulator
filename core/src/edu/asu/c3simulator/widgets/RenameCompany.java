package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class RenameCompany extends ApplicationAdapter implements TextInputListener 
{
	public RenameCompany(Skin skin)
	{
		if(Gdx.input.justTouched())
			Gdx.input.getTextInput(this, "Title", "default text");
		Gdx.app.log("Text", text);
		TextButton renameButton = new TextButton("Rename", skin);
		renameButton.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if(Gdx.input.justTouched())
					Gdx.input.getTextInput(this, "Title", "default text");
				Gdx.app.log("Text", text);
			}
		});
	}
	String text;
	@Override 
	public void create()
	{
		
	}
	@Override 
	public void render()
	{
		if(Gdx.input.justTouched())
			Gdx.input.getTextInput(this, "Title", "default text");
		Gdx.app.log("Text", text);
	}

	@Override
	public void input(String text) 
	{
		this.text = text;
	}

	@Override
	public void canceled() 
	{
		text = "cancelled";
	}

}