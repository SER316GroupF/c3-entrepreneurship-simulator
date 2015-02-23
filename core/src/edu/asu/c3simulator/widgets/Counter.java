package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.util.Association;

/**
 * Displays a count (int) to the left of two buttons (+ and -) which increment and
 * decrement the counter respectively.
 * 
 * @author Moore, Zachary
 *
 */
public class Counter extends Widget
{
	private static final float BUTTON_WIDTH_HEIGHT_RATIO = 1.5f;
	
	private int count;
	private int numericWidth;
	private SimpleTextField countDisplay;
	private Association<Texture, Rectangle> plusButton;
	private Association<Texture, Rectangle> minusButton;
	
	private Counter()
	{
		this.count = 0;
		this.numericWidth = 2;
		this.countDisplay = new SimpleTextField(getFormatedCount());
		
		this.plusButton = new Association<>(defaultPlusTexture(), null);
		this.minusButton = new Association<>(defaultMinusTexture(), null);
		recalculateButtonBounds();
	}
	
	private static Texture defaultPlusTexture()
	{
		return new Texture(Gdx.files.internal("images/blue-square.png"));
	}
	
	private static Texture defaultMinusTexture()
	{
		return new Texture(Gdx.files.internal("images/red-square.png"));
	}
	
	public String getFormatedCount()
	{
		return String.format("%0" + numericWidth + "d", count);
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		countDisplay.draw(batch, parentAlpha);
		
		// TODO: honour parentAlpha
		Rectangle bounds = plusButton.getValue();
		Texture texture = plusButton.getKey();
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
		
		bounds = minusButton.getValue();
		texture = minusButton.getKey();
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	@Override
	public float getPrefWidth()
	{
		float parentWidth = getParent().getWidth();
		float currentWidth = countDisplay.getWidth() + plusButton.getValue().width;
		
		return Math.min(currentWidth, parentWidth);
	}
	
	@Override
	public float getPrefHeight()
	{
		float prefHeight = countDisplay.getHeight();
		return Math.min(prefHeight, getParent().getHeight());
	}
	
	@Override
	public float getMinWidth()
	{
		float minWidth = countDisplay.getMinWidth();
		float minHeight = countDisplay.getMinHeight();
		
		minWidth += BUTTON_WIDTH_HEIGHT_RATIO * minHeight;
		
		return minWidth;
	}
	
	@Override
	public float getMinHeight()
	{
		return countDisplay.getMinHeight();
	}
	
	protected void sizeChanged()
	{
		super.sizeChanged();
		countDisplay.setSize(getWidth(), getHeight());
		countDisplay.validate();
		recalculateButtonBounds();
	}
	
	@Override
	protected void positionChanged()
	{
		this.countDisplay.setPosition(getX(), getY());
		recalculateButtonBounds();
	}
	
	private void recalculateButtonBounds()
	{
		float buttonHeight = (countDisplay.getHeight() / 2);
		float buttonWidth = (buttonHeight * BUTTON_WIDTH_HEIGHT_RATIO);
		float buttonX = (getX() + countDisplay.getWidth());
		float plusY = getX();
		float minusY = plusY + buttonHeight;
		
		Rectangle plusBounds = new Rectangle(buttonX, plusY, buttonWidth, buttonHeight);
		Rectangle minusBounds = new Rectangle(buttonX, minusY, buttonWidth, buttonHeight);
		
		this.plusButton.set(plusButton.getKey(), plusBounds);
		this.minusButton.set(minusButton.getKey(), minusBounds);
	}
	
}
