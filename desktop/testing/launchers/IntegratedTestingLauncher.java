package launchers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.asu.c3simulator.desktop.DesktopLauncher;
import edu.asu.retired.testing.automated.IntegratedGLTestDriver;

public class IntegratedTestingLauncher extends DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		capFrameRate(config, DEFAULT_FRAMERATE);
		setResolution(config, RESOLUTION_720p);
		lockedWindowedMode(config);
		
		new LwjglApplication(new IntegratedGLTestDriver(), config);
	}
}
