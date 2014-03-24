package com.robotsagentshumans.assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import TBSim.TBSim;

/**
 * Main class that runs the A* and Q-Learning Modules, prints them to .txt files in a direction path format.
 * @author David
 */
public class AgentAssignmentStart {

	private static final String OBSTACLE_FILE = "obstacle.txt";
	private static final String DEMO_DSC_TEMPLATE = "demo.dsc";
	private static final String ROBOT_CLASS = "EDU.gatech.cc.is.abstractrobot.MultiForageN150Sim";
	private static final String ROBOT_CONTROLLER = "com.robotsagentshumans.assignment.DummyController";
	private static final String SQUARE_OBSTACLE = "EDU.cmu.cs.coral.simulation.PolygonObstacleSim";
	private static final String BIN_SIM = "EDU.gatech.cc.is.simulation.BinSim";
	private static final int GRID_DIM = 10;
	private static final double FINAL_PLACEMENT = 0.5;
	private static BufferedReader consoleIn;
	private static String dummyInput;
	
	public static void main(String[] args)
	{
		//Create obstacles and instantiate the GridWorld
		consoleIn = new BufferedReader(new InputStreamReader(System.in));
		writeObstacleFile();
		File obstacleFile = new File(OBSTACLE_FILE);
		GridWorld gridworld = new GridWorld(obstacleFile, GRID_DIM);
		gridworld.printWorld();
		
		//Run the AStar Path Planner on the Gridworld
		Astar aStar = new Astar(gridworld); 
		aStar.plan();
		System.out.println("Press Enter to begin A* Simulated Path");
		try {
			//Run the simulator on the AStar Path Planning route
			dummyInput = consoleIn.readLine();
			manuallyUpdateDSC(gridworld, "aStarUpdate.dsc");
			TBSim simulator = new TBSim("aStarUpdate.dsc");
			simulator.show();
			
			//Run the simulator on the Q-Learning Route Path 1
			
			//Run the simulator on the Q-Learning Route Path 2
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in reading from console.");
		}

	}
	
	//Creating three obstacles for the GridWorld, this is hard-coded
	//NOTE: May leave this be, for simplicity's sake.
	public static void writeObstacleFile()
	{
		try {
			FileWriter out = new FileWriter(OBSTACLE_FILE, false);
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
	
	//This method will take in a pre-determined DSC file for loading, and add any necessary configurations.
	//The DSC file will have preset values, but will not include certain information (like Robot Start, Goal Stop) location.
	private static void manuallyUpdateDSC(GridWorld gworld, String newUpdateName)
	{
		copyOverDSCFile(newUpdateName);
		try
		{
			FileWriter out = new FileWriter(newUpdateName, true);
			Scanner in = new Scanner(new File(OBSTACLE_FILE));
			double startX = gworld.getCurrentXCoord() + FINAL_PLACEMENT;
			double startY = GRID_DIM - (gworld.getCurrentYCoord() + FINAL_PLACEMENT);
			double goalX = gworld.getGoalXCoord() + FINAL_PLACEMENT;
			double goalY = GRID_DIM - (gworld.getGoalYCoord() + FINAL_PLACEMENT);
			//Defaulted to 0, more advanced feature.
			int translationVal = 0;			
			String robotColor = "x000000";
			String robotSensorColor = "xFF0000";
			String goalColor = "x0000BB";
			String goalForeGrndClr = "x000000";
			String obstacleColor = "xC0C0C0";
			String obstacleLocInput;
			double obstacleLocX;
			double obstacleLocY;
			
			//Attach the robot to the current configuration
			out.write("robot " + ROBOT_CLASS + " " + ROBOT_CONTROLLER + " " + startX + " " + startY 
					+ " " + translationVal + " " + robotColor + " " + robotSensorColor + " 2\n\n");
			
			out.write("//Setting up the Obstacles\n");
			while (in.hasNext())
			{
				obstacleLocInput = in.next();
				obstacleLocY = Character.getNumericValue(obstacleLocInput.charAt(0));
				obstacleLocX = Character.getNumericValue(obstacleLocInput.charAt(1));
				obstacleLocY = GRID_DIM - (obstacleLocY + FINAL_PLACEMENT);
				obstacleLocX = obstacleLocX + FINAL_PLACEMENT;
				out.write("object " + SQUARE_OBSTACLE + " " + obstacleLocX + " " + obstacleLocY + " " + translationVal + 
						" 0.5 " + obstacleColor + " " + robotColor + " 2\n");
			}
			in.close();
			out.write("\n");
			
			//Goal state?
			out.write("//Setting the 'goal' location\n");
			out.write("object " + BIN_SIM + " " + goalX + " " + goalY + " " + translationVal + 
					" 0.5 " + robotSensorColor + " " + robotSensorColor + " 4\n\n");
			
			//Setup the Grid manually.
			out.write("//Setting up the grid by using a BinClass that is invisible\n");
			for (int i=0; i<10; i++)
			{
				for (int j=0; j<10; j++)
				{
					if ((i+0.5) == goalY && (j+0.5) == goalX)
						continue;
					out.write("object " + BIN_SIM + " " + (j+0.5) + " " + (i+0.5) + " " + translationVal + 
							" 0.5 " + goalColor + " " + goalForeGrndClr + " -1\n");
				}
			}
			out.write("\n");
			out.close();
		} 
		catch (IOException e) 
		{
			System.out.println("Failed to update DSC file: " + e);
		}	
	}
	
	private static void copyOverDSCFile(String updateName)
	{
		try
		{
			FileReader oldFile = new FileReader(DEMO_DSC_TEMPLATE);
			FileWriter newFile = new FileWriter(updateName);
			
			int charNum;
			while ((charNum = oldFile.read()) != -1)
			{
				newFile.write(charNum);
			}
			oldFile.close();
			newFile.flush();
			newFile.close();
			
		}
		catch (IOException e)
		{
			System.out.println("Failed to copy over DSC file: " + e);
		}
	}
}