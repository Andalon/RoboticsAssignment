package com.robotsagentshumans.assignment;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import TBSim.TBSim;

public class AgentSimulationStart {

	public static final String ROBOT_CLASS = "EDU.gatech.cc.is.abstractrobot.MultiForageN150Sim";
	public static final String ROBOT_CONTROLLER = "com.robotsagentshumans.assignment.DummyController";
	public static final String SQUARE_OBSTACLE = "EDU.cmu.cs.coral.simulation.PolygonObstacleSim";
	public static final String BIN_SIM = "EDU.gatech.cc.is.simulation.BinSim";
	public static final int GRID_DIM = 10;
	public static final double FINAL_PLACEMENT = 0.5;
	
	public static void main(String[] args)
	{
		manuallyUpdateDSC();
		TBSim simulator = new TBSim("demoUpdate.dsc");
		simulator.show();
	}
	
	//This method will take in a pre-determined DSC file for loading, and add any necessary configurations.
	//The DSC file will have preset values, but will not include certain information (like Robot Start, Goal Stop) location.
	private static void manuallyUpdateDSC()
	{
		copyOverDSCFile();
		try
		{
			FileWriter out = new FileWriter("demoUpdate.dsc", true);
			Scanner in = new Scanner(new File("obstacle.txt"));
			double startX = 0 + FINAL_PLACEMENT;
			double startY = GRID_DIM - (1 + FINAL_PLACEMENT);
			double goalX = 9 + FINAL_PLACEMENT;
			double goalY = GRID_DIM - (5 + FINAL_PLACEMENT);
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
	
	//private static String returnCorrectXY()
	//{
		
	//}
	
	private static void copyOverDSCFile()
	{
		
		InputStream input = null;
		OutputStream output = null;
		try
		{
			FileReader oldFile = new FileReader("demo.dsc");
			FileWriter newFile = new FileWriter("demoUpdate.dsc");
			
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
