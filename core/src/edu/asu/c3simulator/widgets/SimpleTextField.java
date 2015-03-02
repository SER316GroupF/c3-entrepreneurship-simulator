package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/**
 * Alternative to {@link Label} that draws text based on a {@link BitmapFont} instead of a
 * {@link Skin} object.
 * 
 * {@link SimpleTextField#setSize(float, float)} will prioritize the given size over it's
 * font size (as opposed to {@link Label} which will do the opposite).
 * {@link SimpleTextField} will resize it's font to meet the smallest bound of width and
 * height
 * 
 * @author Moore, Zachary
 *
 */
public class SimpleTextField extends Widget
{
	/**
	 * The total (top and bottom, combined) vertical padding will be calculated by
	 * multiplying {@link #textHeight} by this factor
	 */
	private static final float VARIABLE_HEIGHT_PADDING = 0.3f;
	
	private static final float MINIMUM_FONT_SCALE = 0.3f;
	
	/**
	 * Font with which to draw the text. This will determine, in part, the width and
	 * height of this unit
	 */
	private BitmapFont font;
	
	/** Text to be displayed */
	private String text;
	
	/**
	 * Current width of the text being drawn, as calculated by
	 * {@link BitmapFont#getBounds(CharSequence)}
	 */
	private float textWidth;
	
	/**
	 * Current height of the text being drawn, as calculated by
	 * {@link BitmapFont#getBounds(CharSequence)}
	 */
	private float textHeight;
	
	/** The width of {@link #text} if it were drawn by {@link #font} at a scale of 1.0f */
	private float normalTextWidth;
	
	/** The height of {@link #text} if it were drawn by {@link #font} at a scale of 1.0f */
	private float normalTextHeight;
	
	/**
	 * The width of {@link #text} if it were drawn by {@link #font} at the
	 * {@link #MINIMUM_FONT_SCALE}
	 */
	private float minWidth;
	
	/**
	 * The height of {@link #text} if it were drawn by {@link #font} at the
	 * {@link #MINIMUM_FONT_SCALE}
	 */
	private float minHeight;
	
	public SimpleTextField(String text)
	{
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		setText(text);
	}
	
	/**
	 * Ensure the current values of {@link #textWidth} and {@link #textHeight} are equal
	 * to the current display sizes
	 */
	private void validateTextBounds()
	{
		TextBounds textBounds = font.getBounds(text);
		this.textWidth = textBounds.width;
		this.textHeight = textBounds.height;
	}
	
	/**
	 * Ensure the value of {@link #normalTextHeight} and {@link #normalTextWidth} is equal
	 * to the size of {@link #text} drawn at a scale of 1.0
	 */
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
	
	/**
	 * Ensure the value of {@link #minHeight} and {@link #minWidth} is equal to the size
	 * of {@link #text} drawn with the {@link #MINIMUM_FONT_SCALE}
	 */
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
		return Math.min(getWidth(), parentWidth);
	}
	
	@Override
	public float getPrefHeight()
	{
		return getHeight();
		// return Math.min(getHeight(), getParent().getHeight());
	}
	
	@Override
	public float getWidth()
	{
		return textWidth;
	}
	
	@Override
	public float getHeight()
	{
		return textHeight + heightPadding();
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
	
	@Override
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
	
	/**
	 * @return The vertical padding based on {@link #textHeight} and
	 *         {@link #VARIABLE_HEIGHT_PADDING}
	 */
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
