package edu.asu.c3simulator.util;

/**
 * Used to listen for changes to the state of an {@link Observable} object.
 * <p>
 * The listener must be registered to all objects that it wishes to observe the state of,
 * by calling {@link Observable#registerObservationListener(ObservationListener)}. This
 * call is typically made outside of the {@link ObservationListener} class.
 * <p>
 * When a change of state occurs, this Listener's {@link #stateChanged(Object)} method
 * will be called.
 * 
 * @author Moore, Zachary
 *
 * @see Observable
 * @param <T>
 *            The type of data this listener wishes to receive upon a state change. If the
 *            data doesn't matter, and the listener is only concerned that a state change
 *            occurred, then {@link Object} can be used as this argument. Alternatively,
 *            {@link GPObservationListener} can be used instead.
 */
public interface ObservationListener<T>
{
	/**
	 * Called when the state of an object is changed.
	 * 
	 * @param newState
	 *            An object representing the state of the object after the change (i.e.
	 *            its current state). This state object may or may not be backed by the
	 *            actual data of the object. That is, it may be a copy.
	 */
	void stateChanged(T newState);
}
