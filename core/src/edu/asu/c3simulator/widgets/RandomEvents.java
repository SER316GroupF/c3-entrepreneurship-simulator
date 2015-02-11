package edu.asu.c3simulator.widgets;

import java.util.ArrayList;
import java.util.Random;


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
	public void getEvent(float delta)
	{
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
				System.out.println(events.get(x));
			}
		}
	
	}
	
	
}
