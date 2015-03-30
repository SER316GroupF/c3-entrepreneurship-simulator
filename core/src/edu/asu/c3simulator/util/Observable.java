package edu.asu.c3simulator.util;

/**
 * An object which allows observation of its state by other objects.
 * <p>
 * When the state of an Observable object is changed, it is required to notify all
 * listeners that have been registered, by calling
 * {@link ObservationListener#stateChanged(Object)} on each listener, and passing this
 * objects state as the parameter.
 * <p>
 * {@link Observable} objects are obligated to keep track of their current listeners.
 * 
 * @author Moore, Zachary
 * @version 2
 * 
 * @param <T>
 *            The type of data that can be observed as this objects state. In many cases,
 *            this will be the type of the observable object. However, in some cases such
 *            as charts and graphs, the state may instead be a data object.
 */
public interface Observable<T>
{
	/**
	 * @param observer
	 *            Object to be notified when the state of this Observable is changed
	 * @deprecated Replaced by {@link #registerObservationListener(ObservationListener)}
	 */
	default void registerObserver(Observer<T> observer)
	{
		throw new UnsupportedOperationException("Use registerObservationListener instead");
	}
	
	/**
	 * Registers an {@link ObservationListener} such that it will be notified every time
	 * the state of this object changes.
	 * 
	 * @param listener
	 */
	void registerObservationListener(ObservationListener<? super T> listener);
	
	/**
	 * @return An ID unique to this object. Note: two instances may share the same ID,
	 *         however this implies that both instances represent the same object (but one
	 *         may be a copy of the other - depending on the specification of <T>)
	 * @deprecated Instances of observable can now be differentiated by registering
	 *             separate listeners. If an ID is still required, use
	 *             {@link Object#hashCode()}
	 */
	default String getID()
	{
		return Integer.toString(this.hashCode());
	}
}
