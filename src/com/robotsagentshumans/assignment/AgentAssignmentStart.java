package com.robotsagentshumans.assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Main class that runs the A* and Q-Learning Modules, prints them to .txt files in a direction path format.
 * @author David
 */
public class AgentAssignmentStart {

	public static void main(String[] args)
	{
		writeObstacleFile();
		File obstacleFile = new File("obstacle.txt");
		GridWorld gridworld = new GridWorld(obstacleFile);
		gridworld.printWorld();
		Astar aStar = new Astar(gridworld); 
		aStar.plan(); 
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
