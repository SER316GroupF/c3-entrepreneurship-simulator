package edu.asu.c3simulator.util;

public interface Observer<T>
{
	void observableInstanceUpdated(T newState);
}
