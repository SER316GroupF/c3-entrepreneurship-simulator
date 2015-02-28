package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * This class displays the company - businesses tab in the council section of the game. 
 * 
 * @author Alyahya, Mohammed
 */
public class CompanyPanel implements Screen
{
	private Game game;
	private Stage stage;
	private Skin skin;
	
	private SelectBox<String> businessSelectionBox;
	private Label businessName, businessNetWorth, businessSellingPrice;
	
	public CompanyPanel(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table mainTable = new Table();
		mainTable.setSize(0.70f*stage.getWidth(), 0.70f*stage.getHeight());
		
		Table upperSection = new Table();
		Table lowerSection = new Table();
		Actor businessSelection = createBusinessSelection();
		Actor businessInfo = createBusinessInfo();
		Actor businessGraph = createBusinessGraph();
		Actor businessButtons = createBusinessButtons();
		
		upperSection.add(businessSelection).spaceRight(100).center();
		upperSection.add(businessInfo).expand().fillX().left();
		lowerSection.add(businessGraph).expand().center();
		lowerSection.add(businessButtons).center().width(0.2f*mainTable.getWidth());
		mainTable.add(upperSection).fillX();
		mainTable.row();
		mainTable.add(lowerSection).expand().fill();
		
		mainTable.setPosition(stage.getWidth()/2 - mainTable.getWidth()/2, stage.getHeight()/2 - mainTable.getHeight()/2);
		//upperSection.debug();
		//lowerSection.debug();
		//mainTable.debugTable();
		
		CornerAdvisor advisor = new CornerAdvisor("Test");
		float padding = 0.01f * stage.getHeight();
		float advisorLeft = stage.getWidth() - advisor.getPrefWidth() - padding;
		float advisorBottom = stage.getHeight() - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		
		//TODO add screens
		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		//navigationPanel.addButton("Advising", null);
		navigationPanel.addButton("Company", null);
		navigationPanel.addSubButton("Company", "Business", null);
		navigationPanel.addSubButton("Company", "Assets", null);
		navigationPanel.addButton("Tasks", null);
		navigationPanel.addSubButton("Tasks", "Manage", null);
		navigationPanel.addSubButton("Tasks", "Completed", null);
		navigationPanel.showSubButtonsFor("Company");
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight() - (0.3f * stage.getHeight()));
		
		stage.addActor(navigationPanel);
		stage.addActor(advisor);
		stage.addActor(mainTable);
	}
	
	private Actor createBusinessSelection()
	{
		VerticalGroup businessSelection = new VerticalGroup();
		
		Label businessSelectionLabel = new Label("Select Business:", skin);
		businessSelectionBox = new SelectBox<String>(skin);
		updateBusinessSelection();
		
		businessSelectionBox.setItems("No Business Selected", "Business 1", "Business 2", "Business 3");
		businessSelectionBox.setSelected("No Business Selected");
		
		businessSelection.addActor(businessSelectionLabel);
		businessSelection.addActor(businessSelectionBox);
		businessSelection.align(Align.left);
		businessSelection.space(5);
		
		return businessSelection;
	}
	
	private void updateBusinessSelection()
	{
		//TODO add items to the list
		//TODO test how to manipulate it
		
		//businessSelectionBox.setItems(C3Simulator.getBusinesses());
	}
	
	private Actor createBusinessInfo()
	{
		Table businessInfo = new Table();
		
		VerticalGroup labels = new VerticalGroup();
		VerticalGroup information = new VerticalGroup();
		
		Label businessNameLabel = new Label("Name:", skin);
		Label businessNetWorthLabel = new Label("Net Worth:", skin);
		Label businessSellingPriceLabel = new Label("Selling Price:", skin);
		
		businessName = new Label("Test 1", skin);
		businessNetWorth = new Label("Test 2", skin);
		businessSellingPrice = new Label("Test 3", skin);
		
		updateBusinessInfo();
		
		labels.addActor(businessNameLabel);
		labels.addActor(businessNetWorthLabel);
		labels.addActor(businessSellingPriceLabel);

		information.addActor(businessName);
		information.addActor(businessNetWorth);
		information.addActor(businessSellingPrice);
		
		labels.align(Align.left);
		information.align(Align.left);
		labels.space(5);
		information.space(5);
		
		businessInfo.add(labels).spaceRight(10);
		businessInfo.add(information).expand().fill().left();
		//businessInfo.debug();
		
		return businessInfo;
	}
	
	private void updateBusinessInfo()
	{
		//TODO add items to the list
		//TODO test how to manipulate it
	}
	
	private Actor createBusinessButtons()
	{
		VerticalGroup businessButtons = new VerticalGroup();
		
		//TODO add rename and sell button created by teammates
		
		TextButton renameButton = new TextButton("Rename", skin);
		TextButton sellButton = new TextButton("Sell", skin);
		
		businessButtons.addActor(renameButton);
		businessButtons.addActor(sellButton);
		businessButtons.align(Align.center);
		businessButtons.space(10);
		
		return businessButtons;
	}
	
	private Actor createBusinessGraph()
	{
		VerticalGroup businessGraph = new VerticalGroup();
		
		//TODO all of this after some clarifications
		
		Label graphLabel = new Label("Net Profit vs. Time", skin);
		Label graphImage = new Label("Graph Image", skin);
		
		FileHandle iconLocation = Gdx.files.internal("images/placeholder-advisor-icon.png");
		Texture iconTexture = new Texture(iconLocation);
		Image advisorIcon = new Image(iconTexture);
		
		updateBusinessGraph();
		
		businessGraph.addActor(graphLabel);
		businessGraph.addActor(advisorIcon);
		businessGraph.align(Align.center);
		
		return businessGraph;
	}
	
	private void updateBusinessGraph()
	{
		//TODO add items to the list
		//TODO test how to manipulate it
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
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
		
	}
	
}
