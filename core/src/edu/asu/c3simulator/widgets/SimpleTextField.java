package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class SimpleTextField extends Widget
{
	private static final float VARIABLE_HEIGHT_PADDING = 0.3f;
	
	private BitmapFont font;
	private String text;
	private float textWidth;
	private float textHeight;
	
	public SimpleTextField(String text)
	{
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		setText(text);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
		// TODO: honour parentAlpha
		font.setColor(Color.WHITE);
		float yOffset = (heightPadding() / 2) + this.textHeight;
		font.draw(batch, text, getX(), getY() + yOffset);
	}
	
	@Override
	public float getPrefWidth()
	{
		return this.textWidth;
	}
	
	@Override
	public float getPrefHeight()
	{
		return this.textHeight + heightPadding();
	}
	
	private float heightPadding()
	{
		return this.textHeight * VARIABLE_HEIGHT_PADDING;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
		TextBounds textBounds = font.getBounds(text);
		this.textWidth = textBounds.width;
		this.textHeight = textBounds.height;
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
