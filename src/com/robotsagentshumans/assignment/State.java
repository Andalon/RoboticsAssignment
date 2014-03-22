package com.robotsagentshumans.assignment;

/**
 * Class that represents a State in the GridWorld.
 * Contains functions that tell if its the goal state, its X and Y Coordinates, its State ID, and the value of its reward.
 * 
 * @author David
 *
 */
public class State 
{
	private boolean goalState;
	private int occupiedState;
	private int xCoord;
	private int yCoord;
	private int stateID;
	private int reward;
	
	private static int assignIdCounter = 0; 
	public State(int y, int x)
	{
		//Integer.toString(y) + Integer.toString(x);
		stateID = assignIdCounter;
		occupiedState = 0;
		goalState = false;
		yCoord = y;
		xCoord = x;
		reward = 0;
		
		assignIdCounter++; 
	}
	
	public boolean isGoalState()
	{
		return goalState;
	}
	public void setGoalState(boolean goalState)
	{
		this.goalState = goalState;
	}
	public int isOccupied()
	{
		return occupiedState;
	}
	public int getOccupied()
	{
		return occupiedState;
	}
	public void setOccupied(int occupied)
	{
		this.occupiedState = occupied;
	}
	public int getYCoord()
	{
		return yCoord;
	}
	public int getXCoord()
	{
		return xCoord;
	}
	public int getStateID()
	{
		return stateID;
	}
	//This Setter is mainly used only for the Current State
	public void setNewState(State gridWorld)
	{
		this.stateID = gridWorld.stateID;
		this.yCoord = gridWorld.yCoord; 
		this.xCoord = gridWorld.xCoord; 
	}
	public int getReward()
	{
		return reward;
	}
	public void setReward(int reward)
	{
		this.reward = reward;
	}
}
