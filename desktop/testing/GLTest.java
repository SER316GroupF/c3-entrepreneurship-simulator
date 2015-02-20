import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.asu.c3simulator.desktop.DesktopLauncher;
import edu.asu.c3simulator.testing.SandboxApplication;
import edu.asu.c3simulator.widgets.SimpleTextField;

public class GLTest extends DesktopLauncher
{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		capFrameRate(config, DEFAULT_FRAMERATE);
		setResolution(config, RESOLUTION_720p);
		lockedWindowedMode(config);
		
		new LwjglApplication(new SandboxApplication(), config);
		
		Support.waitFor(50000000);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		Gdx.app.exit();
	}
	
	@Test
	public void test()
	{
		System.out.println("running");
		new SimpleTextField("test").draw(new SpriteBatch(), 1);;
		
		Support.waitFor(50000000);
	}
	
}
