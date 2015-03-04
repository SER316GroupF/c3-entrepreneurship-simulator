package edu.asu.c3simulator.util;

/**
 * This holds a player instance and its information.
 * 
 * @author ShantalOlono
 */
public class Player {

	private String name;
	
	public Player(String playerName)
	{
		name = playerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
