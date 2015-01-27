package edu.asu.c3simulator.util;

public interface Observable<T>
{
	void registerObserver(Observer<T> observer);
}
