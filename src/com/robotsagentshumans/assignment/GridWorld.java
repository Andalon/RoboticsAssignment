package com.robotsagentshumans.assignment;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * GridWorld used for the Robotics Simulation. It is statically designed to be a 10x10 grid, recommended to use a simulation 
 * environment that is an nxn size.
 *  
 * @author David
 */
public class GridWorld {

	private boolean goalStateReached;
	private State currentState;
	private State goalState;
	private int goalStateX;
	private int goalStateY;
	private int currentStateX;
	private int currentStateY;
	private Random random;
	public State[][] gridWorld;
	
	/**
	 * Constructor method for creating the GridWorld and setting up the CurrentState, GoalState and Obstacles from file.
	 * @param obstacleFile
	 */
	public GridWorld(File obstacleFile, int nDim)
	{  
		gridWorld = new State[nDim][nDim];
		//current state is set randomly here, but can be made to initialize at a set point if desired.
		//goal state is set randomly here, but can be made to initialize at a set point if desired.
		random = new Random();

		setStateXYCoords();
		//setStateRandomXYCoords();
		initGrid(gridWorld, 10, 10);
		goalState = gridWorld[goalStateY][goalStateX];		
		currentState = gridWorld[currentStateY][currentStateX];
		
		//Initialize the occupied grid points.
		try {
			Scanner in = new Scanner(obstacleFile);
			String input;
			int inputX;
			int inputY;
			while (in.hasNext())
			{
				input = in.next();
				inputY = Character.getNumericValue(input.charAt(0));
				inputX = Character.getNumericValue(input.charAt(1));
				gridWorld[inputY][inputX].setOccupied(1);				
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the coordinates of the starting state and the goal state, makes sure that the current state is not the goal state.
	 */
	public void setStateRandomXYCoords()
	{
		goalStateX = random.nextInt(10);
		goalStateY = random.nextInt(10);
		currentStateX = random.nextInt(10);
		currentStateY = random.nextInt(10);
		while (currentStateX == goalStateX && currentStateY == goalStateY)
		{
			currentStateX = random.nextInt(10);
			currentStateY = random.nextInt(10);
		}
	}
	
	/**
	 * Manually sets the coordinates of the starting and goal states.
	 */
	public void setStateXYCoords()
	{
		goalStateX = 9;
		goalStateY = 5;
		currentStateX = 0;
		currentStateY = 1;
	}
	
	/**
	 * Method used for debugging purposes. Will print out the grid, and if the currentState is at the Goal State, will
	 * print "Target Reached"
	 * G represents Goal State, C represents Current State, and option set for 0 or " " to represent unoccupied space. 
	 * 1 will represent an occupied state.
	 */
	public void printWorld()
	{
		PrintStream out = System.out;
		out.println("Goal = (" + goalStateX + "," + goalStateY + ")");
		out.println("Current = (" + currentStateX + "," + currentStateY + ")");
		
		if (!goalStateReached())
		{
			for (int i=0; i<10; i++)
			{
				for (int j=0; j<10; j++)
				{
					if (gridWorld[i][j].isGoalState())
					{
						out.print("[G]");
						continue;
					}
					else if (i==currentStateY && j == currentStateX)
					{
						out.print("[C]");
						continue;
					}
					out.print("[" + gridWorld[i][j].getOccupied() + "]");
				}
				out.println();
			}
		}
		else
		{
			out.println("Target Reached!");
		}
	}
	
	/*
	 * Initializes the Grid to have no occupancy across the grid. Sets it all to 0 values until obstacles are created and placed
	 * in the correct spots. Also sets the goal state that was created in the constructor.
	 */
	private State[][] initGrid(State[][] world, int ySize, int xSize)
	{
		for (int i=0; i<ySize; i++)
		{
			for (int j=0; j<xSize; j++)
			{
				world[i][j] = new State(i,j);
				
				//Sets this State as the Goal State and sets the reward value to 100.
				if (i==goalStateY && j==goalStateX)
				{
					world[i][j].setGoalState(true);
					world[i][j].setReward(100);
				}
			}
		}
		return world;
	}
	
	/*
	 * GETTER/SETTERS for the GridWorld.
	 */	
	/*
	 * Returns whether or not the robot has reached the goal state.
	 */
	public boolean goalStateReached()
	{
		if (currentState.getXCoord() == goalState.getXCoord() && currentState.getYCoord() == goalState.getYCoord() && !goalStateReached)
		{
			goalStateReached = true;
		}
		return goalStateReached;
	}
	public int getGoalState()
	{
		return goalState.getStateID();
	}
	
	public int getCurrentState()
	{
		return currentState.getStateID();
	}
	
	/*
	 * Debugging method, may be used for following the CurrentState of the Robot later.
	 * Acceptable Directions to move -> Up, Left, Down, Right. Any other string will result in no move. 
	 */
	public void moveState(String direction)
	{
		int newStateId = getHeuristicState(currentState.getStateID(), direction); 
		if (!(newStateId == (currentState.getStateID())))
		{
			Point p = getXYCoordinatesofState(newStateId); 
			currentState.setNewState(gridWorld[p.y][p.x]);
			currentStateX = currentState.getXCoord();
			currentStateY = currentState.getYCoord();
		}
	}
	
	/*
	 * Gets the XY Coordinates of a State based on its stateID (conveniently, the stateID is the XY Coordinates backwards)
	 */
	public Point getXYCoordinatesofState(int stateID)
	{
		for (State[] arr : gridWorld)
		{
			for (State s : arr)
			{
				if(s.getStateID() == stateID)
				{
					return new Point(s.getXCoord(),s.getYCoord()); 
				}
			}
		}
		return new Point(0,0); 
	}
	/*
	 * This returns an integer ID value of the next state depending on the move direction. 
	 * Protects move decisions if object should not move.
	 */
	public int getHeuristicState(int stateId, String moveDirection)
	{
		moveDirection = moveDirection.toLowerCase();
		Point stateCur = getXYCoordinatesofState(stateId);
		if (moveDirection.equals("up") && stateCur.y != 0)
		{
			stateCur.y -= 1;
		}
		else if (moveDirection.equals("down") && stateCur.y != 9)
		{
			stateCur.y += 1;
		}
		else if (moveDirection.equals("left") && stateCur.x != 0)
		{
			stateCur.x -= 1;
		}
		else if (moveDirection.equals("right") && stateCur.x != 9)
		{
			stateCur.x += 1;
		}
		if(gridWorld[stateCur.y][stateCur.x].isOccupied() == 1)
		{
			return stateId; 
		}
		int nextState = gridWorld[stateCur.y][stateCur.x].getStateID();
		return nextState;
	}
	
	public int getGoalXCoord()
	{
		return goalStateX;
	}
	public int getGoalYCoord()
	{
		return goalStateY;
	}
	public int getCurrentXCoord()
	{
		return currentStateX;
	}
	public int getCurrentYCoord()
	{
		return currentStateY;
	}
}
