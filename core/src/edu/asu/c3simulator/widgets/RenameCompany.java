package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class RenameCompany extends ApplicationAdapter implements TextInputListener 
{
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