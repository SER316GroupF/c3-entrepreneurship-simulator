package edu.asu.c3simulator.util;

/**
 * An object that can be observed by other objects.
 * 
 * When the state of an Observable object is changed, it is required to update all
 * observers that have been registered, by calling
 * {@link Observer#observableInstanceUpdated(Object)}, and passing itself.
 * 
 * Each Observable object is required to have an ID unique to its represented object. That
 * is, two instances may share the same ID, however this implies that both instances
 * represent the same object (but one may be a copy of the other - depending on the
 * specification of <T>)
 * 
 * It is suggested that the hashcode of an instance be used as its ID, except in the case
 * of temporary data copies.
 * 
 * @author Moore, Zachary
 * 
 * @param <T>
 */
public interface Observable<T>
{
	/**
	 * @param observer
	 *            Object to be notified when the state of this Observable is changed
	 */
	void registerObserver(Observer<T> observer);
	
	/**
	 * The default implementation returns the objects hash code.
	 * 
	 * @return An ID unique to this object. Note: two instances may share the same ID,
	 *         however this implies that both instances represent the same object (but one
	 *         may be a copy of the other - depending on the specification of <T>)
	 */
	default String getID()
	{
		return Integer.toString(hashCode());
	}
}
