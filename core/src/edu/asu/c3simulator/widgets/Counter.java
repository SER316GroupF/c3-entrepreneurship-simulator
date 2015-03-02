package edu.asu.c3simulator.widgets;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
	private static final int DEFAULT_MINIMUM_COUNT = 0;
	private static final int DEFAULT_MAXIMUM_COUNT = 99;
	private static final int DEFAULT_INCREMENT = 1;
	
	/**
	 * Generate the default {@link Texture} to be used for {@link #minusButton}
	 * 
	 * @return The default {@link Texture} to be used for {@link #minusButton}, unsized
	 */
	private static Texture defaultMinusTexture()
	{
		return new Texture(Gdx.files.internal("images/red-square.png"));
	}
	
	/**
	 * Generate the default {@link Texture} to be used for {@link #plusButton}
	 * 
	 * @return The default {@link Texture} to be used for {@link #plusButton}, unsized
	 */
	private static Texture defaultPlusTexture()
	{
		return new Texture(Gdx.files.internal("images/blue-square.png"));
	}
	
	/** Current count to be displayed */
	private int count;
	
	/** The number of digits to be displayed, not including signage (if applicable) */
	private int numericWidth;
	
	/** {@link #count} will always be >= to this number */
	private int minimumCount;
	
	/** {@link #count} will always be <= to this number */
	private int maximumCount;
	
	/**
	 * Amount by which to increase or decrease {@link #count} when {@link #plusButton} or
	 * {@link #minusButton} is pressed, respectively
	 */
	private int incrementAmount;
	
	/**
	 * Displays the current value of {@link #count} and is updated whenever {@link #count}
	 * is updated
	 */
	private SimpleTextField countDisplay;
	
	/** Holds the {@link Texture} and image bounds (absolute) of the plus button */
	private Association<Texture, Rectangle> plusButton;
	
	/** Holds the {@link Texture} and image bounds (absolute) of the minus button */
	private Association<Texture, Rectangle> minusButton;
	
	/**
	 * LibGDX alters this object's x and y values using package-access to reflect
	 * different values for draw and event calls. (Event calls are relative, and draw
	 * calls have absolute positioning). {@link #drawnX} and {@link #drawnY} reflect the
	 * absolute positioning on the screen.
	 */
	private float drawnX;
	
	/** @see #drawnX */
	private float drawnY;
	
	/** Listeners to notify in the case of a count change event */
	private List<CountListener> countListeners;
	
	public Counter()
	{
		this.count = 0;
		this.numericWidth = 2;
		this.minimumCount = DEFAULT_MINIMUM_COUNT;
		this.maximumCount = DEFAULT_MAXIMUM_COUNT;
		this.incrementAmount = DEFAULT_INCREMENT;
		this.countDisplay = new SimpleTextField(getFormatedCount());
		this.countListeners = new LinkedList<>();
		
		this.plusButton = new Association<>(defaultPlusTexture(), null);
		this.minusButton = new Association<>(defaultMinusTexture(), null);
		recalculateButtonBounds();
		addButtonListeners();
		pack();
	}
	
	/**
	 * Create listeners to handle incrementing and decrementing the count via the
	 * {@link #plusButton} and {@link #minusButton}, and adds the listener to this
	 * counter.
	 */
	private void addButtonListeners()
	{
		ClickListener buttonsListener = new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Rectangle plusBounds = plusButton.getValue();
				Rectangle minusBounds = minusButton.getValue();
				float screenX = drawnX + x;
				float screenY = drawnY + y;
				
				if (plusBounds.contains(screenX, screenY))
					changeCount(incrementAmount);
				else if (minusBounds.contains(screenX, screenY))
					changeCount(-incrementAmount);
			}
		};
		
		this.addListener(buttonsListener);
	}
	
	/**
	 * Add the specified amount to the current {@link #count}, and
	 * {@link #validateCount()}
	 * 
	 * @param amount
	 */
	private void changeCount(int amount)
	{
		count += amount;
		validateCount();
		countChanged(amount, count);
	}
	
	/**
	 * Determines the bounds of {@link #plusButton} and {@link #minusButton} such that
	 * their combined height is equal to the height of the display. The width of the
	 * buttons is determined by {@link #BUTTON_WIDTH_HEIGHT_RATIO}
	 * 
	 * This method also sets the bounds of {@link #plusButton} and {@link #minusButton}
	 * using new {@link Rectangle} objects (the new data will not be backed by the old
	 * objects, but will still be backed by the {@link #plusButton} and
	 * {@link #minusButton} Associations)
	 */
	private void recalculateButtonBounds()
	{
		float buttonHeight = (countDisplay.getHeight() / 2);
		float buttonWidth = (buttonHeight * BUTTON_WIDTH_HEIGHT_RATIO);
		float buttonX = (getX() + countDisplay.getWidth());
		float minusY = getY();
		float plusY = minusY + buttonHeight;
		
		Rectangle plusBounds = new Rectangle(buttonX, plusY, buttonWidth, buttonHeight);
		Rectangle minusBounds = new Rectangle(buttonX, minusY, buttonWidth, buttonHeight);
		
		this.plusButton.set(plusButton.getKey(), plusBounds);
		this.minusButton.set(minusButton.getKey(), minusBounds);
	}
	
	/**
	 * If the count is outside of a valid range, then set count equal to the closest
	 * boundary and update the display.
	 * 
	 * Also ensures that the display matches the current count using
	 * {@link #validateCountDisplay()}
	 * 
	 * Valid ranges: (count >= {@link #minimumCount} && count <= {@link #maximumCount})
	 */
	private void validateCount()
	{
		if (count < minimumCount)
		{
			count = minimumCount;
		}
		else if (count > maximumCount)
		{
			count = maximumCount;
		}
		
		validateCountDisplay();
	}
	
	/**
	 * Ensures the display matches the current count
	 */
	private void validateCountDisplay()
	{
		String counterText = getFormatedCount();
		
		if (!countDisplay.getText().equals(counterText))
		{
			countDisplay.setText(counterText);
		}
	}
	
	/**
	 * Notifies all {@link CountListener}s currently registered to this object.
	 * 
	 * @see CountListener#countChanged(Counter, int, int)
	 */
	protected void countChanged(int delta, int newCount)
	{
		for (CountListener listener : countListeners)
		{
			listener.countChanged(this, delta, newCount);
		}
	}
	
	/**
	 * @param listener
	 *            Will be fired when {@link #count} is changed
	 * @return True if the listener was registered successfully, false otherwise
	 */
	public boolean addListener(CountListener listener)
	{
		return this.countListeners.add(listener);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		this.drawnX = getX();
		this.drawnY = getY();
		
		countDisplay.setPosition(getX(), getY());
		countDisplay.draw(batch, parentAlpha);
		
		recalculateButtonBounds();
		
		// TODO: honour parentAlpha
		Rectangle bounds = plusButton.getValue();
		Texture texture = plusButton.getKey();
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
		
		bounds = minusButton.getValue();
		texture = minusButton.getKey();
		batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	/**
	 * @return The current value of this counter, as an int
	 */
	public int getCount()
	{
		return this.count;
	}
	
	/**
	 * @return The current value of this counter, as it is displayed (leading zeros,
	 *         signage, etc. is included)
	 */
	public String getFormatedCount()
	{
		int stringWidth = (count < 0) ? numericWidth + 1 : numericWidth;
		return String.format("%0" + stringWidth + "d", count);
	}
	
	@Override
	public float getHeight()
	{
		return countDisplay.getHeight();
	}
	
	@Override
	public float getMinHeight()
	{
		return countDisplay.getMinHeight();
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
	public float getPrefHeight()
	{
		float preferedHeight = getHeight();
		
		if (getParent() != null)
		{
			float parentHeight = getParent().getHeight();
			float prefHeight = getHeight();
			
			preferedHeight = Math.min(prefHeight, parentHeight);
		}
		
		return preferedHeight;
	}
	
	@Override
	public float getPrefWidth()
	{
		float preferedWidth = getWidth();
		
		if (getParent() != null)
		{
			float parentWidth = getParent().getWidth();
			float currentWidth = getWidth();
			
			preferedWidth = Math.min(currentWidth, parentWidth);
		}
		
		return preferedWidth;
	}
	
	@Override
	public float getWidth()
	{
		return countDisplay.getWidth() + plusButton.getValue().width;
	}
}
