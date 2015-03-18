package edu.asu.c3simulator.util;

/**
 * @deprecated Replaced by {@link ObservationListener}
 *             <p>
 *             An object that can observe {@link Observable} objects.
 *             <p>
 *             In order to be notified when the observable state changes,
 *             {@link Observable#registerObserver(Observer)} must be called. When the
 *             state is updated, {@link #observableInstanceUpdated(Object)} will be
 *             called, using the new state as a parameter.
 *             <p>
 *             If an {@link Observer} is observing more than one object, it's subjects can
 *             be differentiated by using {@link Observable#getID()}
 * 
 * @author Moore, Zachary
 * 
 * @param <T>
 */
public interface Observer<T>
{
	/**
	 * Notify this object that the state of an object that is being watched has changed.
	 * 
	 * @param newState
	 *            The state of the object after the change occurred. Note: the instance
	 *            may be backed by the original data, or it may be a copy that is not
	 *            backed by the original data.
	 * @deprecated Replaced by {@link ObservationListener#stateChanged(Object)}
	 */
	void observableInstanceUpdated(T newState);
}
