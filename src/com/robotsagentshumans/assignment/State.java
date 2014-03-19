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
	private String stateID;
	private int reward;
	
	public State(int y, int x)
	{
		stateID = Integer.toString(y) + Integer.toString(x);
		occupiedState = 0;
		goalState = false;
		yCoord = y;
		xCoord = x;
		reward = 0;
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
	public String getStateID()
	{
		return stateID;
	}
	//This Setter is mainly used only for the Current State
	public void setStateID(String newID)
	{
		this.stateID = newID;
		this.yCoord = Character.getNumericValue(newID.charAt(0));
		this.xCoord = Character.getNumericValue(newID.charAt(1));
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
