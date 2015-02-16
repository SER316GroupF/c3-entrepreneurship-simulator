package edu.asu.c3simulator.widgets;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class TextAreaX extends Widget
{
	private BitmapFont font;
	private List<String> lines;
	private float lineHeight;
	private float lineWidth;
	private float linePadding;
	private Texture background;
	private Padding borderPadding;
	private Color color;
	
	public TextAreaX(String text, float width, float fontScale, Color color)
	{
		this.color = color;
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		font.setScale(fontScale);
		linePadding = 1.2f;
		borderPadding = new Padding();
		setText(text, width);
	}
	
	public TextAreaX(String text, float width, float fontScale)
	{
		color = Color.WHITE;
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		font.setScale(fontScale);
		linePadding = 1.2f;
		borderPadding = new Padding();
		setText(text, width);
	}
	
	public TextAreaX(String text, float width)
	{
		this(text, width, 1.0f);
	}
	
	private void resetLineMetrics()
	{
		float maxHeight = 0.0f;
		float maxWidth = 0.0f;
		
		for (String line : lines)
		{
			TextBounds lineBounds = font.getBounds(line);
			
			if (lineBounds.height > maxHeight)
				maxHeight = lineBounds.height;
			if (lineBounds.width > maxWidth)
				maxWidth = lineBounds.width;
		}
		
		this.lineHeight = maxHeight * linePadding;
		this.lineWidth = maxWidth;
	}
	
	private void setText(String text, float width)
	{
		this.lines = new LinkedList<>();
		
		// TODO: support new line characters
		Queue<String> futureLines = new LinkedList<>();
		futureLines.add(text);
		
		while (!futureLines.isEmpty())
		{
			String line = futureLines.poll();
			float lineWidth = this.font.getBounds(line).width;
			
			if (lineWidth <= width)
			{
				// TODO: account for lines that can be combined with the next futureLine
				this.lines.add(line);
			}
			else
			{
				String[] halves = splitIntoFullWordHalves(line);
				if (halves == null)
				{
					this.lines.add(line);
				}
				else
				{
					futureLines.add(halves[1]);
					futureLines.add(halves[0]);
				}
			}
		}
		
		resetLineMetrics();
	}
	
	private String[] splitIntoFullWordHalves(String string)
	{
		String[] halves = new String[2];
		int splitIndex = string.length() / 2;
		
		while (string.charAt(splitIndex) != ' ')
		{
			++splitIndex;
			
			if (splitIndex == string.length())
				return null;
		}
		
		if (splitIndex == string.length() - 1)
			return null;
		else
		{
			halves[0] = string.substring(0, splitIndex);
			halves[1] = string.substring(splitIndex + 1);
		}
		
		return halves;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
		if (this.background != null)
		{
			Gdx.gl20.glClearColor(0, 0, 0, 1.0f);
			batch.draw(this.background, getX(), getY(), getPrefWidth(), getPrefHeight());
		}
		

		font.setColor(color.r, color.g, color.b, parentAlpha);
		
		for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++)
		{
			String line = lines.get(lineIndex);
			float lineY = ((lineIndex + 1) * lineHeight) + getY()
					+ borderPadding.get(Padding.BOTTOM);
			float textWidth = font.getBounds(line).width;
			float widthDelta = lineWidth - textWidth;
			float lineX = getX() + (widthDelta / 2) + borderPadding.get(Padding.LEFT);
			
			font.draw(batch, line, lineX, lineY);
		}
	}
	
	@Override
	public float getPrefHeight()
	{
		float heightPadding = borderPadding.get(Padding.TOP)
				+ borderPadding.get(Padding.BOTTOM);
		
		return (lines.size() * lineHeight) + heightPadding;
	}
	
	@Override
	public float getPrefWidth()
	{
		float widthPadding = borderPadding.get(Padding.RIGHT)
				+ borderPadding.get(Padding.LEFT);
		
		return lineWidth + widthPadding;
	}
	
	public void setBackground(Texture background)
	{
		this.background = background;
	}
	
	public void setBackground(FileHandle backgroundFile)
	{
		Texture background = new Texture(backgroundFile);
		setBackground(background);
	}
	
	public void setBackground(String internalPath)
	{
		FileHandle backgroundFile = Gdx.files.internal(internalPath);
		Texture background = new Texture(backgroundFile);
		
		setBackground(background);
	}
	
	public void setBorderPadding(Padding borderPadding)
	{
		this.borderPadding = borderPadding;
		// TODO: recalculate lines
	}

	public void setText(String text)
	{
		// TODO Auto-generated method stub 
		throw new UnsupportedOperationException("The method is not implemented yet.");
		
		// TODO: recalculate lines and set text
	}
	
}
