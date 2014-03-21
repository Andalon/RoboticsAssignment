package com.robotsagentshumans.assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import TBSim.TBSim;

public class AgentAssignmentStart {

	public static void main(String[] args)
	{
		//File dscFile = new File("walls.dsc");
		//TBSim simulator = new TBSim("walls.dsc");
		//simulator.show();
		writeObstacleFile();
		File obstacleFile = new File("obstacle.txt");
		GridWorld gridworld = new GridWorld(obstacleFile);
		gridworld.printWorld();
		Astar aStar = new Astar(gridworld); 
		aStar.plan(); 
//		
//		while (!gridworld.goalStateReached())
//		{
//			gridworld.moveState("Up");
//			gridworld.printWorld();
//			gridworld.moveState("Left");
//			gridworld.printWorld();
//		}
		
	}
	//Creating three obstacles for the GridWorld, this is hardcoded, will talk about this.
	public static void writeObstacleFile()
	{
		try {
			FileWriter out = new FileWriter("obstacle.txt", false);
			//Circle Obstacle
			out.write("02 03 04 12 13 14 22 23 24\n");
			//Square Obstacle
			out.write("72 73 74 82 83 84 92 93 94\n");
			//Rectangle Obstacle
			out.write("37 47 57 67");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " + e);
		}
	}
}
