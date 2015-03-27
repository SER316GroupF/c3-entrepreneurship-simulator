package edu.asu.c3simulator.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class creates a new product.
 * 
 * @author Alyahya, Mohammed
 */
public class Product
{
	private Image productImage;
	private String name;
	private static final String DEFAULT_IMAGE_PATH = "images/placeholder-t_shirt-icon.png";
	
	public Product(String productName, String imagePath)
	{
		this.name = productName;
		this.productImage = getImage(imagePath);
	}
	
	public Product(String productName)
	{
		this.name = productName;
		this.productImage = getImage(DEFAULT_IMAGE_PATH);
	}
	
	/**
	 * Create an Image object using the image path passed.
	 * 
	 * @param imagePath
	 *            name of the image
	 * @return Image object
	 */
	private Image getImage(String imagePath)
	{
		FileHandle iconLocation = Gdx.files.internal(imagePath);
		Texture iconTexture = new Texture(iconLocation);
		return new Image(iconTexture);
	}
	
	public Image getProductImage()
	{
		return productImage;
	}
	
	public void setProductImage(Image productImage)
	{
		this.productImage = productImage;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
