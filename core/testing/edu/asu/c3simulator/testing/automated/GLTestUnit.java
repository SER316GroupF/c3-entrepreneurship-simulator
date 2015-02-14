package edu.asu.c3simulator.testing.automated;

import junit.framework.Test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * A unit test to be executed by {@link GLTestDriver}. In order to test units with an
 * OpenGL dependency in an environment as close to the deployed execution environment as
 * possible, {@link GLTestUnit} objects are simulated using {@link #tick()}, which
 * simulates a call to {@link Actor#draw(Batch, float)} and/or an update call. When a unit
 * has had enough simulation time/tick calls to perform a realistic simulation, it will
 * return true when {@link #isComplete()} is called.
 * 
 * {@link #validate()} will be called {@link GLTestDriver} after {@link #isComplete()}
 * returns true.
 * 
 * When to use {@link GLTestUnit} as opposed to {@link Test} or other unit testing
 * frameworks: Typically unit testing frameworks do not provide an openGL framework, nor
 * do they simulate the execution of OpenGL or LibGDX Game threads surrounding the unit.
 * GLTestDriver provides these simulation threads, in addition to the functionality
 * provided by {@link GLTestUnit}. Thus, if a unit has a dependency on OpenGL, LibGDX, or
 * can be affected by either during its lifecycle, then it should be tested using
 * {@link GLTestUnit}.
 * 
 * @see GLTestDriver
 * @author Moore, Zachary
 * 
 */
public interface GLTestUnit extends Disposable
{
	/**
	 * Simulates functionality for a single tick, as typically executed by
	 * {@link Game#render()}. Typically {@link GLTestUnit} objects will render themselves
	 * in this function, but they are not required to do so.
	 */
	public void tick();
	
	/**
	 * @return True if {@link #validate()} can be called; False if this test needs more
	 *         ticks to complete it's simulation
	 */
	public boolean isComplete();
	
	/**
	 * Makes assertions regarding the current state of the unit
	 */
	public void validate();
}
