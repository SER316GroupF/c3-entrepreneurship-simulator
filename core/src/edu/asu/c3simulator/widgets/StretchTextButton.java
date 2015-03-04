package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/**
 * A TextButton that stretches to the width of a specified target Layout. In many cases,
 * the target layout will be the button's parent, but this is not strictly enforced.
 * 
 * This functionality is useful in tables, in which the button may not be the widest
 * element, and therefore appears smaller than other entries.
 * 
 * @author Moore, Zachary
 * 
 */
public class StretchTextButton extends TextButton
{
	/**
	 * The target's preferred width will be used to determine this object's preferred
	 * width
	 */
	private Layout target;
	
	/**
	 * @param text
	 *            Text to be displayed on this button
	 * @param skin
	 *            Determines the background image and specifications thereof
	 * @param layout
	 *            Used to determine this object's preferred width
	 */
	public StretchTextButton(String text, Skin skin, Layout layout)
	{
		super(text, skin);
		this.target = layout;
	}
	
	/**
	 * @return The preferred width of {@link #target}, or the width of this button's text,
	 *         if {@link #target} is null
	 * @see com.badlogic.gdx.scenes.scene2d.ui.Button#getPrefWidth()
	 */
	public float getPrefWidth()
	{
		if (target == null)
			return super.getPrefWidth();
		else
			return target.getPrefWidth();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		layout();
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public void layout()
	{
		super.layout();
		TextButtonStyle style = this.getStyle();
		BitmapFont font = style.font;
		
		float originalScaleX = font.getScaleX();
		float originalScaleY = font.getScaleY();
		
		font.setScale(1.0f);
		float textWidth = font.getBounds(getText()).width;
		float targetWidth = getPrefWidth();
		font.setScale(originalScaleX, originalScaleY);
		
		if (textWidth > targetWidth)
		{
			BitmapFont newFont = new BitmapFont();
			textWidth = newFont.getBounds(getText()).width;
			
			float newScale = targetWidth / textWidth;
			newFont.setScale(newScale);
			style.font = newFont;
		}
		
		setStyle(style);
	}
}
