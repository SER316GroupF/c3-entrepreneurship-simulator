//package edu.asu.c3simulator.widgets;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//
//import edu.asu.c3simulator.screens.MainHub;
//
//public class NewProductButton
//{
//	private Image homeIcon;
//	
//	public NewProductButton(final Game game)
//	{
//		super();
//		
//		FileHandle iconLocation = Gdx.files.internal("images/home-button.png");
//		Texture iconTexture = new Texture(iconLocation);
//		this.homeIcon = new Image(iconTexture);
//		add(homeIcon).size(150);
//		homeLeft = this.getPrefWidth()/2;
//		homeBottom = this.getPrefHeight()/2;
//		this.setPosition(homeLeft, homeBottom);
//		this.addListener(new ClickListener(){
//			@Override
//			public void clicked(InputEvent event, float x, float y)
//			{
//				game.setScreen(new MainHub(game));
//			}
//		});
//	}
//}
