package edu.asu.c3simulator.widgets;

import java.util.ArrayList;
import java.util.Random;

/* 
* @author Olono, Shantal
* 
*/
public class RandomEvent //extends Actor
{
	float runTime;
	ArrayList<String> events; 
	
	public RandomEvent()
	{
		events = new ArrayList<String>();
		events.add("You went bankrupt");
		events.add("You lost your company");
		events.add("Your store got robbed");
		events.add("You got robbed");
		events.add("You lost your position");
		runTime = 0;
	}
	//@Override
	//public void run(float delta)
	public String getEvent(float delta)
	{
		String label = "";
		runTime += delta;
		if(runTime > 1f)
		{
			Random rand = new Random(); 
			int x = rand.nextInt(10);
			System.out.println(x);
			runTime = 0;
			if(x == 1)
			{
				x = rand.nextInt(events.size());
				label = events.get(x);
			}
		}
		return label;
	
	}
	
	
}

