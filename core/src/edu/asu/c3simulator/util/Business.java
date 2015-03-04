package edu.asu.c3simulator.util;

/**
 * This hold a business instance and its information.
 * 
 * @author ShantalOlono
 */
public class Business {

	private String name;
	
	public Business(String businessName)
	{
		name = businessName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
