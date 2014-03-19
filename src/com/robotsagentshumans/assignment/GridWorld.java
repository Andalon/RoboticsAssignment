package com.robotsagentshumans.assignment;

import java.awt.Point;

/**
 * GridWorld used for the Robotics Simulation. It is statically designed to be a 10x10 grid, recommended to use a simulation 
 * environment that is an nxn size.
 *  
 * @author David
 */
public class GridWorld {

	private boolean goalStateReached;
	private int currentState;
	private int goalState;
	
	public GridWorld()
	{  
		
	}
	
	
	/*
	 * GETTER/SETTERS for the GridWorld.
	 */
	
	/*
	 * Returns whether or not the robot has reached the goal state.
	 */
	public boolean goalStateReached()
	{
		return goalStateReached;
	}
	public int getGoalState()
	{
		return goalState;
	}
	public int getCurrentState()
	{
		//return current state.
		return currentState;
	}
	public Point getXYCoordinatesofState(int state)
	{
		Point temp = new Point();
		//temp.X = state.X;
		//temp.Y = state.Y;
		return temp; 
	}
	public int getHeuristicState(int state, String moveDirection)
	{
		int nextState=0;
		return nextState;
	}
	
	
	
}
