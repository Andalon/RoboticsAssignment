package com.robotsagentshumans.assignment;

import java.io.File;

import TBSim.TBSim;

public class HelloAssignment {

	public static void main(String[] args)
	{
		//File dscFile = new File("walls.dsc");
		//TBSim simulator = new TBSim("walls.dsc");
		//simulator.show();
		GridWorld gridworld = new GridWorld();
		gridworld.printWorld();
		
		while (!gridworld.goalStateReached())
		{
			gridworld.moveState("Up");
			gridworld.printWorld();
			gridworld.moveState("Left");
			gridworld.printWorld();
		}
		
	}
}
