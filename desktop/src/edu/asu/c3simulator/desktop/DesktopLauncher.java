package edu.asu.c3simulator.desktop;

import java.awt.Dimension;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.asu.c3simulator.C3Simulator;

public class DesktopLauncher
{
	public static final Dimension RESOLUTION_720p = new Dimension(1280, 720);
	public static final int DEFAULT_FRAMERATE = 60;
	
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		capFrameRate(config, DEFAULT_FRAMERATE);
		setResolution(config, RESOLUTION_720p);
		lockedWindowedMode(config);
		
		new LwjglApplication(new C3Simulator(), config);
	}
	
	protected static void capFrameRate(LwjglApplicationConfiguration config, int framerate)
	{
		config.foregroundFPS = framerate;
		config.backgroundFPS = framerate;
	}
	
	protected static void lockedWindowedMode(LwjglApplicationConfiguration config)
	{
		config.resizable = false;
		config.fullscreen = false;
		config.vSyncEnabled = false;
	}
	
	protected static void setResolution(LwjglApplicationConfiguration config,
			Dimension resolution)
	{
		setResolution(config, resolution.width, resolution.height);
	}
	
	protected static void setResolution(LwjglApplicationConfiguration config, int width,
			int height)
	{
		config.width = width;
		config.height = height;
	}
}
