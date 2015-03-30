package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.util.Product;

/**
 * Creates a popup window that display all designs to the user in order to create a new
 * product and return the product create to the table it is going to be displayed on.
 * 
 * @author Alyahya, Mohammed
 */
public class ProductDesigns
{
	/**
	 * This listener checks if any of the icons is clicked and then proceeds to add it to
	 * the products display table.
	 * 
	 * @author Alyahya, Mohammed
	 */
	private class ProductIconListener extends ClickListener
	{
		private Product associatedProduct;
		
		public ProductIconListener(Product associatedProduct)
		{
			this.associatedProduct = associatedProduct;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Table newProduct = new Table();
			Label productLabel = new Label(associatedProduct.getName(), skin);
			Image productIcon = associatedProduct.getProductImage();
			productIcon.clearListeners();
			
			newProduct.add(productIcon).size(100f).row();
			newProduct.add(productLabel);
			
			productsDisplayTable.add(newProduct).space(15);
			
			lastCreatedProduct = associatedProduct;
			newProductPopupWindow.remove();
		}
	}
	
	private Stage stage;
	private Skin skin;
	private Table productsDisplayTable;
	private Window newProductPopupWindow;
	private Product lastCreatedProduct;
	
	public ProductDesigns(Table productsDisplayTable, Stage stage, Skin skin)
	{
		this.stage = stage;
		this.skin = skin;
		this.productsDisplayTable = productsDisplayTable;
		
		newProductPopupWindow = new Window("Choose New Product Design", skin);
		newProductPopupWindow
				.setSize(0.40f * stage.getWidth(), 0.80f * stage.getHeight());
		newProductPopupWindow.setPosition(
				stage.getWidth() / 2 - (0.40f * stage.getWidth()) / 2, stage.getHeight()
						/ 2 - (0.80f * stage.getHeight()) / 2);
		newProductPopupWindow.setMovable(false);
		newProductPopupWindow.setResizable(false);
	}
	
	/**
	 * This method is called in order to display the popup window.
	 */
	public void displayProductDesignOptions()
	{
		Table productGrid = new Table();
		Product[] products = getAvailableProducts();
		
		for (int productIndex = 0; productIndex < products.length; productIndex++)
		{
			Table newProduct = new Table();
			Label productLabel = new Label(products[productIndex].getName(), skin);
			Image productIcon = products[productIndex].getProductImage();
			productIcon.addListener(new ProductIconListener(products[productIndex]));
			
			newProduct.add(productIcon).size(0.20f * stage.getWidth(),
					0.40f * stage.getHeight());
			newProduct.add(productLabel);
			
			productGrid.add(newProduct).space(15);
			
			if (productIndex != products.length - 1)
				productGrid.row();
		}
		
		ScrollPane productGridScroll = new ScrollPane(productGrid, skin);
		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				newProductPopupWindow.remove();
			}
		});
		newProductPopupWindow.clear();
		newProductPopupWindow.add(productGridScroll).expand().fill().row();
		newProductPopupWindow.add(exitButton).fillX();
		stage.addActor(newProductPopupWindow);
	}
	
	/**
	 * Get available designs from the game and return it.
	 * 
	 * @return an array of the available designs of products.
	 */
	private Product[] getAvailableProducts()
	{
		// TODO get products
		Product[] products = { new Product("T-shirt 1"),
				new Product("T-shirt 2"), new Product("Short 1"),
				new Product("Short 2"), new Product("Hoodie 1"),
				new Product("Pants 1"), new Product("Pants 2"),
				new Product("Pants 3"), new Product("Pants 4") };
		return products;
	}
	
	/**
	 * @return the product created by the last user selected design
	 */
	public Product getLastChosenDesign()
	{
		return lastCreatedProduct;
	}
}
