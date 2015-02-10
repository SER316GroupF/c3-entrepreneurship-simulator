package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class PaddedTexture extends Texture
{
	private Padding padding;

	public PaddedTexture(FileHandle file)
	{
		super(file);
	}

	public PaddedTexture(FileHandle file, boolean useMipMaps)
	{
		super(file, useMipMaps);
	}

	public PaddedTexture(FileHandle file, boolean useMipMaps, Padding padding)
	{
		super(file, useMipMaps);
		this.padding = padding;
	}

	public PaddedTexture(FileHandle file, Format format, boolean useMipMaps)
	{
		super(file, format, useMipMaps);
	}

	public PaddedTexture(FileHandle file, Format format, boolean useMipMaps, Padding padding)
	{
		super(file, format, useMipMaps);
		this.padding = padding;
	}

	public PaddedTexture(FileHandle file, Padding padding)
	{
		super(file);
		this.padding = padding;
	}

	public PaddedTexture(int width, int height, Format format)
	{
		super(width, height, format);
	}

	public PaddedTexture(Pixmap pixmap)
	{
		super(pixmap);
	}

	public PaddedTexture(Pixmap pixmap, boolean useMipMaps)
	{
		super(pixmap, useMipMaps);
	}

	public PaddedTexture(Pixmap pixmap, boolean useMipMaps, Padding padding)
	{
		super(pixmap, useMipMaps);
		this.padding = padding;
	}

	public PaddedTexture(Pixmap pixmap, Format format, boolean useMipMaps)
	{
		super(pixmap, format, useMipMaps);
	}

	public PaddedTexture(Pixmap pixmap, Format format, boolean useMipMaps, Padding padding)
	{
		super(pixmap, format, useMipMaps);
		this.padding = padding;
	}

	public PaddedTexture(Pixmap pixmap, Padding padding)
	{
		super(pixmap);
		this.padding = padding;
	}

	public PaddedTexture(String internalPath)
	{
		super(internalPath);
	}

	public PaddedTexture(String internalPath, Padding padding)
	{
		super(internalPath);
		this.padding = padding;
	}

	public PaddedTexture(TextureData data)
	{
		super(data);
	}

	public PaddedTexture(TextureData data, Padding padding)
	{
		super(data);
		this.padding = padding;
	}
	
	public Padding getPaddingAtResolution(float width, float height)
	{
		float xScale = width / getWidth();
		float yScale = height / getHeight();
		
		float left = padding.get(Padding.LEFT) * xScale;
		float right = padding.get(Padding.RIGHT) * xScale;
		float top = padding.get(Padding.TOP) * yScale;
		float bottom = padding.get(Padding.BOTTOM) * yScale;
		
		Padding scaledPadding = new Padding(left, right, top, bottom);
		return scaledPadding;
	}
}
