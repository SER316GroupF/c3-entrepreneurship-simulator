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
	private static final String ADVISOR_IMAGE_PATH = "images/placeholder-advisor-icon.png";
	private static final String DEFAULT_IMAGE_PATH = "images/placeholder-t_shirt-icon.png";
	
	/**
	 * @param productName The name of the product
	 * @param imageName The name of the image that is going to be displayed, if a wrong name is passed, it will display a default image.
	 */
	public Product(String productName, String imageName)
	{
		this.name = productName;
		this.productImage = getImage(imageName);
	}
	
	/**
	 * Create an Image object based on the image name passed.
	 * 
	 * @param imageName name of the image 
	 * @return Image object
	 */
	private Image getImage(String imageName)
	{
		String imagePath = "";
		
		switch (imageName) {
            case "default":  imagePath = DEFAULT_IMAGE_PATH;
                     break;
            case "advisor":  imagePath = ADVISOR_IMAGE_PATH;
                     break;
            default: imagePath = DEFAULT_IMAGE_PATH;
                     break;
        }
		
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
