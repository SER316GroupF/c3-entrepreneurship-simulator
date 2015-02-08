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
	private static final float MINIMUM_FONT_SCALE = 0.3f;
	
	private BitmapFont font;
	private String text;
	private float textWidth;
	private float textHeight;
	private float normalTextWidth;
	private float normalTextHeight;
	private float minWidth;
	private float minHeight;
	
	public SimpleTextField(String text)
	{
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		setText(text);
	}
	
	private void validateTextBounds()
	{
		TextBounds textBounds = font.getBounds(text);
		this.textWidth = textBounds.width;
		this.textHeight = textBounds.height;
	}
	
	private void validateNormalTextBounds()
	{
		float scaleX = font.getScaleX();
		float scaleY = font.getScaleY();
		
		font.setScale(1.0f);
		TextBounds textBounds = font.getBounds(text);
		
		normalTextHeight = textBounds.height;
		normalTextWidth = textBounds.width;
		
		font.setScale(scaleX, scaleY);
	}
	
	private void validateSizeSpecification()
	{
		float minHeight = 0.0f;
		float minWidth = 0.0f;
		
		if (MINIMUM_FONT_SCALE > 0)
		{
			float scaleX = font.getScaleX();
			float scaleY = font.getScaleY();
			
			font.setScale(MINIMUM_FONT_SCALE);
			TextBounds textBounds = font.getBounds(text);
			
			minHeight = textBounds.height;
			minWidth = textBounds.width;
			
			font.setScale(scaleX, scaleY);
		}
		
		this.minHeight = minHeight;
		this.minWidth = minWidth;
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
		float parentWidth = getParent().getWidth();
		return Math.min(textWidth, parentWidth);
	}
	
	@Override
	public float getPrefHeight()
	{
		float prefHeight = textHeight + heightPadding();
		return Math.min(prefHeight, getParent().getHeight());
	}
	
	@Override
	public float getMinWidth()
	{
		return minWidth;
	}
	
	@Override
	public float getMinHeight()
	{
		return minHeight;
	}
	
	protected void sizeChanged()
	{
		super.sizeChanged();
		float desiredWidth = this.getWidth();
		float desiredHeight = this.getHeight();
		
		float estimatedScaleX = desiredWidth / normalTextWidth;
		float estimatedScaleY = desiredHeight / normalTextHeight;
		
		float newScale = Math.min(estimatedScaleX, estimatedScaleY);
		newScale = Math.max(MINIMUM_FONT_SCALE, newScale);
		
		font.setScale(newScale);
		validateTextBounds();
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
		
		validateTextBounds();
		validateSizeSpecification();
		validateNormalTextBounds();
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
