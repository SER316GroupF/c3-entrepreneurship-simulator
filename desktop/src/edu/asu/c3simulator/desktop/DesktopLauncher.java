package edu.asu.c3simulator.desktop;

import java.awt.Dimension;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.asu.c3simulator.C3Simulator;

/**
 * Launcher for {@link C3Simulator}
 * 
 * @author Moore, Zachary
 *
 */
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
	
	/**
	 * Sets the framerate limit of the provided config
	 * 
	 * @param config File to configure
	 * @param framerate Max framerate
	 */
	protected static void capFrameRate(LwjglApplicationConfiguration config, int framerate)
	{
		config.foregroundFPS = framerate;
		config.backgroundFPS = framerate;
	}
	
	/**
	 * Configures the provided config by setting
	 * {@link LwjglApplicationConfiguration#resizable},
	 * {@link LwjglApplicationConfiguration#fullscreen}, and
	 * {@link LwjglApplicationConfiguration#vSyncEnabled} to false
	 * 
	 * @param config File to configure
	 */
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
