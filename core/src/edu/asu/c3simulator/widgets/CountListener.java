package edu.asu.c3simulator.widgets;

/**
 * Listener to be registered on a {@link Counter} object. The counter will call
 * {@link #countChanged(int, int)} whenever its count is changed by the user or system.
 * 
 * @author Moore, Zachary
 *
 */
public interface CountListener
{
	/**
	 * @param source
	 *            The counter firing the event
	 * @param delta
	 *            The amount by which the count was altered
	 * @param newCount
	 *            The new value of count, as returned by {@link Counter#getCount()}
	 */
	void countChanged(Counter source, int delta, int newCount);
}
