package edu.asu.c3simulator.screens;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.util.Product;
import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * Display a list of all products created by the business. Give the user the ability to
 * either market one of them or the company as a whole. The user is also able to choose
 * from different types of marketing campaigns. This is the first step of a two-step wizard.
 * 
 * @author Alyahya, Mohammed
 */
public class MarketingScreen implements SimulationScreen
{
	
	/**
	 * The ProductIconListener takes in the label associated with the product icon and the
	 * product object itself and then manipulate them when the icon is clicked.
	 */
	private class ProductIconListener extends ClickListener
	{
		private Label associatedLabel;
		private Product associatedProduct;
		
		/**
		 * @param associatedLabel
		 *            A label which will change colors when the icon is clicked.
		 * @param associatedProduct
		 *            The product that is represented by the object that is being listened
		 *            to.
		 */
		public ProductIconListener(Label associatedLabel, Product associatedProduct)
		{
			this.associatedLabel = associatedLabel;
			this.associatedProduct = associatedProduct;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			companyPromotionButton.setColor(Color.WHITE);
			associatedLabel.setColor(Color.GREEN);
			if (selectedProductLabel != null && selectedProductLabel != associatedLabel)
				selectedProductLabel.setColor(Color.WHITE);
			
			selectedProduct = associatedProduct;
			selectedProductLabel = associatedLabel;
			productChosen = true;
		}
	}
	
	/**
	 * The marketingIconListener takes in the label associated with the campaign icon and
	 * the campaign object itself and then manipulate them when the icon is clicked.
	 */
	private class MarketingIconListener extends ClickListener
	{
		private Label associatedLabel;
		private String marketingType;
		
		/**
		 * @param associatedLabel
		 *            A label which will change colors when the icon is clicked
		 * @param marketingType
		 *            The marketing type that the icon is representing.
		 */
		public MarketingIconListener(Label associatedLabel, String marketingType)
		{
			this.associatedLabel = associatedLabel;
			this.marketingType = marketingType;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			associatedLabel.setColor(Color.BLUE);
			if (selectedMarketingLabel != null
					&& selectedMarketingLabel != associatedLabel)
				selectedMarketingLabel.setColor(Color.WHITE);
			
			selectedMarketingMethod = marketingType;
			selectedMarketingLabel = associatedLabel;
			marketingMethodChosen = true;
		}
	}
	
	private Game game;
	private Stage stage;
	private Skin skin;
	private Product selectedProduct;
	private Label selectedProductLabel, selectedMarketingLabel;
	private TextButton companyPromotionButton;
	private String selectedMarketingMethod = "";
	private boolean promoteCompany;
	private boolean productChosen;
	private boolean marketingMethodChosen;
	
	public MarketingScreen(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		NavigationPanel navigationPanel = createNavigationPanel();
		
		Table mainTable = createMainTable();
		mainTable.setPosition(
				stage.getWidth() - 0.01f * stage.getWidth() - mainTable.getWidth(),
				0.01f * stage.getHeight());
		
		stage.addActor(navigationPanel);
		stage.addActor(mainTable);
	}
	
	/**
	 * The method creates the navigation panel.
	 * 
	 * @return the NavigationPanel actor that was created.
	 */
	@Override
	public NavigationPanel createNavigationPanel()
	{
		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		
		// TODO add screens
		navigationPanel.addButton("Products", null);
		navigationPanel.addSubButton("Products", "Pre-Market", null);
		navigationPanel.addSubButton("Products", "Current", null);
		navigationPanel.addSubButton("Products", "Retired", null);
		navigationPanel.addButton("Product Growth", null);
		navigationPanel.addSubButton("Product Growth", "Supply", null);
		navigationPanel.addSubButton("Product Growth", "Demand", null);
		navigationPanel.addButton("Employment", null);
		navigationPanel.addButton("Marketing", null);
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight()
				- (0.3f * stage.getHeight()));
		
		return navigationPanel;
	}
	
	/**
	 * This method creates the main section of the GUI
	 * 
	 * @return The table that was created.
	 */
	private Table createMainTable()
	{
		Table mainTable = new Table();
		mainTable.setSize(0.80f * stage.getWidth(), 0.80f * stage.getHeight());
		
		Table leftSection = new Table();
		Table rightSection = new Table();
		
		ScrollPane productGridScroll = new ScrollPane(getProductGrid(), skin);
		companyPromotionButton = new TextButton("Promote Company", skin);
		companyPromotionButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if (promoteCompany)
					companyPromotionButton.setColor(Color.WHITE);
				else
					companyPromotionButton.setColor(Color.GREEN);
				promoteCompany = !promoteCompany;
				productChosen = false;
				if (selectedProduct != null)
					selectedProduct = null;
				if (selectedProductLabel != null)
				{
					selectedProductLabel.setColor(Color.WHITE);
					selectedProductLabel = null;
				}
			}
		});
		
		leftSection.add(productGridScroll).expand().fill().row();
		leftSection.add(companyPromotionButton).center().space(10.0f);
		
		ScrollPane marketingGridScroll = new ScrollPane(getMarketingGrid(), skin);
		TextButton marketinLabel = new TextButton("Select Marketing", skin);
		marketinLabel.setDisabled(true);
		marketinLabel.setColor(Color.GRAY);
		TextButton nextButton = new TextButton("Next", skin);
		nextButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				if (marketingMethodChosen && (productChosen || promoteCompany))
				{
					game.setScreen(new MarketingWizard(game, MarketingScreen.this));
				}
			}
		});
		
		rightSection.add(marketinLabel).expand().fill().row();
		rightSection.add(marketingGridScroll).expand().fill().row();
		rightSection.add(nextButton).right().space(10.0f);
		
		mainTable.add(leftSection).expand().fill();
		mainTable.add(rightSection).expand().fill();
		
		return mainTable;
	}
	
	/**
	 * This method creates the the grid of products that the user can choose from and adds
	 * them to a table.
	 * 
	 * @return The table that was created.
	 */
	private Table getProductGrid()
	{
		Table productGrid = new Table();
		Product[] products = getProducts();
		
		for (int index = 0; index < products.length; index++)
		{
			Table newProduct = new Table();
			Label productLabel = new Label(products[index].getName(), skin);
			Image productIcon = products[index].getProductImage();
			productIcon
					.addListener(new ProductIconListener(productLabel, products[index]));
			
			newProduct.add(productIcon).size(100f).row(); // TODO change to dynamic size
			newProduct.add(productLabel);
			
			productGrid.add(newProduct).space(15);
			
			if ((index + 1) % 2 == 0 && index != products.length - 1)
				productGrid.row();
		}
		
		return productGrid;
	}
	
	/**
	 * Creates the the grid of marketing types icons that the user can choose from and
	 * adds them to a table.
	 * 
	 * @return The table that was created.
	 */
	private Table getMarketingGrid()
	{
		Table marketingGrid = new Table();
		
		HashMap<String, String> marketingIcons = new HashMap<String, String>();
		marketingIcons.put("Internet Campaign", "images/placeholder-Computer-Icon.png");
		marketingIcons.put("Social Media", "images/placeholder-SocialMedia-icon.png");
		marketingIcons.put("Television and Radio", "images/placeholder--TV-icon.png");
		marketingIcons.put("Traditional Advertising",
				"images/placeholder-Billboard-icon.png");
		
		Set<String> marketingTypes = marketingIcons.keySet();
		int iconCounter = 0;
		int iconsPerLine = 2;
		for (String marketingType : marketingTypes)
		{
			Table newMarketingMethod = new Table();
			Label marketingMethodLabel = new Label(marketingType, skin);
			FileHandle iconLocation = Gdx.files.internal(marketingIcons
					.get(marketingType));
			Texture iconTexture = new Texture(iconLocation);
			Image marketingtIcon = new Image(iconTexture);
			marketingtIcon.addListener(new MarketingIconListener(marketingMethodLabel,
					marketingType));
			
			newMarketingMethod.add(marketingtIcon).size(200f).row(); // TODO change to
																		// dynamic size
			newMarketingMethod.add(marketingMethodLabel);
			
			marketingGrid.add(newMarketingMethod).space(15);
			
			iconCounter++;
			if (iconCounter >= iconsPerLine)
			{
				marketingGrid.row();
				iconCounter = 0;
			}
		}
		
		return marketingGrid;
	}
	
	/**
	 * This method is a testing shell that creates different products and return them to
	 * be displayed.
	 * 
	 * @return an array of the products created.
	 */
	private Product[] getProducts()
	{
		// TODO get current product products from the data layer of the company.
		Product[] products = { new Product("T-shirt 1", "default"),
				new Product("T-shirt 2", "default"), new Product("Short 1", "default"),
				new Product("Short 2", "default"), new Product("Hoodie 1", "default"),
				new Product("Pants 1", "default"), new Product("Pants 2", "default"),
				new Product("Pants 3", "default"), new Product("Pants 4", "default") };
		return products;
	}
	
	/**
	 * @return The product selected by the user.
	 */
	public Product getSelectedProduct()
	{
		return selectedProduct;
	}
	
	/**
	 * @return The marketing campaign selected by the user.
	 */
	public String getSelectedMarketingMethod()
	{
		return selectedMarketingMethod;
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
		
	}
	
	@Override
	public void pause()
	{
		// do nothing
	}
	
	@Override
	public void resume()
	{
		// do nothing
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
}
