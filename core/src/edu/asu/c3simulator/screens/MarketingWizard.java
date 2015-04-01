package edu.asu.c3simulator.screens;

import java.util.LinkedHashMap;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.util.Product;

/**
 * This is the second page of the marketing campaign wizard. It displays product/company
 * information and marketing campaign options to enable the user to customize their
 * campaign.
 * 
 * @author Alyahya, Mohammed
 */
public class MarketingWizard implements Screen
{
	private Game game;
	private Stage stage;
	private Skin skin;
	private Product selectedProduct = null;
	private String campaignMethod = "", marketingOption = "";
	private MarketingScreen firstWizardPage;
	private Table campaignOptions;
	
	public MarketingWizard(Game game, MarketingScreen previousWizardPage)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		firstWizardPage = previousWizardPage;
		
		selectedProduct = firstWizardPage.getSelectedProduct();
		campaignMethod = firstWizardPage.getSelectedMarketingMethod();
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		Table mainTable = createMainTable();
		mainTable.setPosition(
				stage.getWidth() - 0.01f * stage.getWidth() - mainTable.getWidth(),
				0.01f * stage.getHeight());
		
		stage.addActor(mainTable);
	}
	
	/**
	 * This method creates the main section of the GUI
	 * 
	 * @return The table that was created.
	 */
	private Table createMainTable()
	{
		Table mainTable = new Table();
		mainTable.setSize(0.98f * stage.getWidth(), 0.80f * stage.getHeight());
		
		Table upperSection = new Table();
		Table lowerSection = new Table();
		
		Label campaignMethodLabel = new Label("Marketing Campaign: " + campaignMethod,
				skin);
		
		TextButton campaignTargetLabel;
		if (selectedProduct == null) // No product was selected. Thus, the company as a
										// whole is the campaign target.
			campaignTargetLabel = new TextButton("Promoting Company", skin);
		else
			campaignTargetLabel = new TextButton("Product: " + selectedProduct.getName(),
					skin);
		
		campaignTargetLabel.setDisabled(true);
		campaignTargetLabel.setColor(Color.GRAY);
		
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				game.setScreen(firstWizardPage);
			}
		});
		
		upperSection.add(backButton).center();
		upperSection.add(campaignTargetLabel).expand().fill().center();
		
		Table campaignTypes = new Table();
		Table campaignOptionTable = new Table();
		Table productAndConsumerInformation = new Table();
		
		Label campaignTypesLabel = new Label("Campaign Types", skin);
		
		// this is not java.util.List but Libgdx's own list which is a displayable actor
		// (com.badlogic.gdx.scenes.scene2d.ui.List)
		List<String> campaignTypesList = new List<String>(skin);
		campaignTypesList.setItems(getCampaignTypes());
		campaignTypesList.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				marketingOption = campaignTypesList.getSelected();
				updateCampaignOptions();
			}
			
		});
		ScrollPane campaignTypesScroll = new ScrollPane(campaignTypesList, skin);
		
		campaignTypes.add(campaignTypesLabel).center().row();
		campaignTypes.add(campaignTypesScroll).expand().fill();
		
		campaignOptions = new Table();
		marketingOption = campaignTypesList.getSelected();
		Label campaignOptionsLabel = new Label("Campaign Options", skin);
		ScrollPane campaignOptionsScroll = new ScrollPane(campaignOptions, skin);
		updateCampaignOptions();
		
		campaignOptionTable.add(campaignOptionsLabel).center().row();
		campaignOptionTable.add(campaignOptionsScroll).expand().fill();
		
		Label productAndConsumerInformationLabel1 = new Label("Product and", skin);
		Label productAndConsumerInformationLabel2 = new Label("Consumer Information",
				skin);
		ScrollPane productAndConsumerInformationScroll = new ScrollPane(
				getProductAndConsumerInformation(), skin);
		TextButton finishButton = new TextButton("Finish", skin);
		finishButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO add wizard results to data layer
				game.setScreen(AllManagementScreens.MARKETING.getInstance());
			}
		});
		
		productAndConsumerInformation.add(productAndConsumerInformationLabel1).center()
				.row();
		productAndConsumerInformation.add(productAndConsumerInformationLabel2).center()
				.row();
		productAndConsumerInformation.add(productAndConsumerInformationScroll).expand()
				.fill().row();
		productAndConsumerInformation.add(finishButton).expandX().fillX().padTop(5.0f)
				.padLeft(5.0f);
		
		lowerSection.add(campaignTypes).expand().fill();
		lowerSection.add(campaignOptionTable).expand().fill();
		lowerSection.add(productAndConsumerInformation).expand().fill();
		
		mainTable.add(campaignMethodLabel).left().row();
		mainTable.add(upperSection).expandX().fillX().padBottom(10.0f).row();
		mainTable.add(lowerSection).expand().fill();
		
		return mainTable;
	}
	
	/**
	 * This methods gets the campaign types available for the chosen marketing campaign
	 * from the data layer.
	 * 
	 * @return An array of the campaign types in String format.
	 */
	private String[] getCampaignTypes()
	{
		// TODO get campaign types based on the campaignMethod chosen
		String[] campaignTypes = { "Create Website", "Email Campaign",
				"Promote On Web Forums", "Banner Ads", "Video Ads" };
		
		return campaignTypes;
	}
	
	/**
	 * This method updates the campaign options section based on the selected campaign
	 * type.
	 */
	private void updateCampaignOptions()
	{
		// TODO get campaign options based on the campaignMethod chosen and the campaign
		// type chosen
		campaignOptions.clear();
		
		// Label campaignOptionsLabel = new Label("Placeholder Label: ", skin);
		// CheckBox campaignOptionsCheckBox = new CheckBox("Test CheckBox", skin);
		
		// This is used only for testing and demo purposes
		campaignOptions.add(new Label(marketingOption + " Label:", skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new Label(marketingOption + " Label:", skin)).left()
				.padTop(10.0f).row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new Label(marketingOption + " Label:", skin)).left()
				.padTop(10.0f).row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new Label(marketingOption + " Label:", skin)).left()
				.padTop(10.0f).row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		campaignOptions.add(new CheckBox(" " + marketingOption, skin)).left().row();
		
		campaignOptions.top().left();
	}
	
	/**
	 * @return A table containing the selected product or the company's information.
	 */
	private Table getProductAndConsumerInformation()
	{
		Table productAndConsumerInformation = new Table();
		LinkedHashMap<String, String> campaignTargetInfo;
		
		if (selectedProduct == null) // No product was selected. Thus, the company as a
										// whole is the campaign target.
			campaignTargetInfo = getCompanyInfo();
		else
			campaignTargetInfo = getProductInfo();
		
		Set<String> targetInfoLabels = campaignTargetInfo.keySet();
		for (String infoLabel : targetInfoLabels)
		{
			Label campaignTargetLabel = new Label(infoLabel, skin);
			Label campaignTargetInformation = new Label(
					campaignTargetInfo.get(infoLabel), skin);
			campaignTargetLabel.setColor(Color.BLUE);
			
			productAndConsumerInformation.add(campaignTargetLabel).top().left().row();
			productAndConsumerInformation.add(campaignTargetInformation).top().left()
					.padBottom(10.0f).row();
		}
		
		productAndConsumerInformation.top().left();
		return productAndConsumerInformation;
	}
	
	/**
	 * @return A LinkedHashMap<infoLabel, info> that relates an info to its label for the
	 *         selected product.
	 */
	private LinkedHashMap<String, String> getProductInfo()
	{
		// TODO get product info from the data layer and update this
		LinkedHashMap<String, String> productInfo = new LinkedHashMap<String, String>();
		
		productInfo.put("Product Name: ", selectedProduct.getName());
		productInfo.put("Type: ", "Some Type");
		productInfo.put("Audiance: ", "Some Audiance");
		productInfo.put("Market Condition: ", "Some Market Condition");
		
		return productInfo;
	}
	
	/**
	 * @return A LinkedHashMap<infoLabel, info> that relates an info to its label for the
	 *         company.
	 */
	private LinkedHashMap<String, String> getCompanyInfo()
	{
		// TODO get business info from the data layer and update this
		LinkedHashMap<String, String> companyInfo = new LinkedHashMap<String, String>();
		
		companyInfo.put("Company Name: ", "Some Name");
		companyInfo.put("Info 1 Label: ", "Info 1");
		companyInfo.put("Info 2 Label: ", "Info 2");
		companyInfo.put("Info 3 Label: ", "Info 3");
		companyInfo.put("Info 4 Label: ", "Info 4");
		companyInfo.put("Info 5 Label: ", "Info 5");
		companyInfo.put("Info 6 Label: ", "Info 6");
		
		return companyInfo;
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
