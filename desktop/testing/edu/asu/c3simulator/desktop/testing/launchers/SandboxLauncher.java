package edu.asu.c3simulator.desktop.testing.launchers;
import java.awt.Dimension;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.asu.c3simulator.desktop.DesktopLauncher;
import edu.asu.c3simulator.testing.SandboxApplication;

public class SandboxLauncher extends DesktopLauncher
{
	public static final Dimension RESOLUTION_720p = new Dimension(1280, 720);
	public static final int DEFAULT_FRAMERATE = 60;
	
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		capFrameRate(config, DEFAULT_FRAMERATE);
		setResolution(config, RESOLUTION_720p);
		lockedWindowedMode(config);
		
		new LwjglApplication(new SandboxApplication(), config);
	}
}
