package retired;
import tests.TestTemplate;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.asu.c3simulator.desktop.DesktopLauncher;
import edu.asu.c3simulator.testing.automated.IntegratedTest;
import edu.asu.retired.testing.automated.GLTestDriver;

/**
 * Launcher for {@link GLTestDriver}
 * 
 * @author Moore, Zachary
 * @deprecated Use {@link IntegratedTest} and JUnit instead
 * @see IntegratedTest
 * @see TestTemplate
 *
 */
public class TestingLauncher extends DesktopLauncher
{	
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		capFrameRate(config, DEFAULT_FRAMERATE);
		setResolution(config, RESOLUTION_720p);
		lockedWindowedMode(config);
		
		new LwjglApplication(new GLTestDriver(), config);
	}
}
