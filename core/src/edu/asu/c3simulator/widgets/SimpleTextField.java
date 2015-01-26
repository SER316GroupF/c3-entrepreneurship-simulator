package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class SimpleTextField extends Widget
{
	private BitmapFont font;
	private String text;
	private float textWidth;
	
	public SimpleTextField(String text)
	{
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		setText(text);
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
		// TODO: honour parentAlpha
		font.setColor(Color.WHITE);
		font.draw(batch, text, getX() - textWidth / 2, getY());
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		this.textWidth = font.getBounds(text).width;
	}

	public BitmapFont getFont()
	{
		return font;
	}

	public float getTextWidth()
	{
		return textWidth;
	}
	
	
}
