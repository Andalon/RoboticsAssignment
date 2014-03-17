package com.robotsagentshumans.assignment;


import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

import com.robotsagentshumans.assignment.PQComparator;

public class Astar {
		
	static PQComparator comparator = new PQComparator();
	static PriorityQueue<PathObject> PQ = new PriorityQueue<PathObject>(1000, comparator);
	static ClosedTreeComparator closedTreeComp = new ClosedTreeComparator(); 
	static TreeSet<PathObject> closedTree = new TreeSet<PathObject>(closedTreeComp); 
	
	//Checks for movement and being stuck on the map
	
	//Retries n number of times until decides if it is stuck
	int totalImpatientTicks = 5; 
	//Retries n number of times until decides if it is stuck
	int currentImpatientTicks = 0; 
	//Shouldn't be in the same state after a move is completed
	int previousState = -1; 
	//Shouldn't be defined as stuck if waiting for the A* alg to finish
	static boolean isWaitingForAStar = false;
	//Shouldn't be defined as stuck if waiting for the robot to finish moving
	boolean isWaitingForCompletedMove = false; 
	//The current state of the robot
	static int gCurrentState = -1; 
	
	static PathObject foundGoalPath = null; 
	static Stack<Tuple> goalPath = new Stack<Tuple>(); 
	///Initialize function to setup first path object
	/// Adds first state to priority queue
	public static void init(int currentState)
	{
		//Initialize the path object 
		PathObject po = new PathObject(); 
		po.hVal = 0; 
		po.parent = null; 
		po.heurStateValue= currentState; 	
		po.dir = null; 
		//Add path object to queue
		PQ.add(po); 
		
		gCurrentState = currentState; 
	}
	//Temporary? 
	//For testing unless gets info from simulator? 
	public static void main(String args[])
	{
		System.out.println("A* ");
		//First state
		init(getCurrentState());
		plan();  			
		System.out.println("End of Program");
	}
	
	///retrieve from simulator the current state of robot from grid world
	private static int getCurrentState() 
	{			
		return 0;
	}
	///A* High level method, Details of each step within
	public static void plan() 
	{
		//Set isWaitingForAStar to true
		isWaitingForAStar = true; 
				
		//continue with A* until the priority queue is empty
		while(!PQ.isEmpty())
		{
			//W = Pop first off PQ
			PathObject w = PQ.poll(); 
			System.out.println("Heur State " + w.heurStateValue); 
			//Check if W is at goal state; save goal state, continue evaluating
			if(isGoal(w))
			{
				//Save off the path only if there isn't a better one already found
				savePath(w); 
				//break;
			}
			//Check if W is in closedMap (already evaluated) 
			if(closedTree.contains(w))
			{
				//ignore w and keep going
				continue; 
			}
			//Generate offspring, calculate f = g + h,
			//add offspring to either open queue or closed tree
			generateOffspring(w); 	
			
			//Add W to closedMap
			closedTree.add(w); 
		}
		//if goal state found, yay! else problem
		if(foundGoalPath == null)
		{
			System.out.println("Uhoh! no path found..."); 
			System.exit(0); 
		}
		//Generate path w/ PathObject parents (recursively) 
		generatePath(foundGoalPath); 
		
		//Send path somewhere, 
			//at each move, trigger isWaiting until move is done
			//When move is done, check if the robot is stuck
		sendPath(goalPath); 
			
		//Clear PQ and closed list						
		PQ.clear();
		closedTree.clear();
		//Set isWaitingForAStar to false
		isWaitingForAStar = false; 
	}
	
	private static void sendPath(Stack<Tuple> path) {
		//at each move, trigger isWaiting until move is done
		//When move is done, check if the robot is stuck
		System.out.println("Goal path "); 
		while(!path.isEmpty())
		{
			Tuple t =  path.pop(); 
			System.out.println(t.state +  "  " + t.dir); 
		}
		
		
	}
	
	///Stacks the moves from beginning to end, along w/ moves to take
	private static void generatePath(PathObject path)
	{
		if(path.parent != null)
		{
			
			generatePath(path.parent); 
		}
		Tuple t = new Tuple(); 
		t.state = path.heurStateValue; 
		t.dir = path.dir; 
		goalPath.add(t); 
		
		
	}
	private static void generateOffspring(PathObject w) 
	{
		//for each move, generate kids 
		for (Move dir : Move.values()) 
		{
			  PathObject kid = new PathObject();
			  
			  //Set kid parent to w 
			  kid.parent = w; 
			  
			  //TODO need to figure out grid state value
			  kid.heurStateValue  = getStateVal(w.heurStateValue, dir); 
			  
			  //Set Move for state
			  kid.dir = dir; 
			  
			  //G(x) = parent val + distance from parent to kid (should be 1?)
			  kid.gVal = w.gVal + 1; 
			  
			  //H(x) = heuristic value from location to goal 
			  kid.hVal = heuristicVal(kid.heurStateValue); 
			  
			  //F(x) = G(x) + H(x)
			  kid.fVal = kid.gVal + kid.hVal; 			  			  
			  
			  //If not in closed tree, add it to open queue
			  if(!closedTree.contains(kid))
			  {
				  PQ.add(kid); 
			  }
			  else
			  {
				  System.out.println("closedTree contains " + kid.heurStateValue); 
			  }
		}

		
	}
	/// Gets the state value of the grid from parent state w/ move dir
	private static int getStateVal(int heurStateValue, Move dir) {
		//TODO from simulator 
		
		switch(dir)
		{
		case Down:
			break;
		case Left:
			break;
		case Right:
			break;
		case Up:
			break;
		default:
			break;
		
		}		
		//TODO remove random
		return new Random().nextInt(100);
	}
	
	/// Calculates Manhattan distance from Heuristic State value to the goal
	private static double heuristicVal(int heurStateValue) {
		Coordinate node = getStateCoordinates(heurStateValue); 		
		Coordinate goal = getStateCoordinates(getSimGoalState()); 
		double dx = Math.abs(node.x - goal.x);
		double dy = Math.abs(node.y - goal.y);
		return (dx+ dy);
	}
	private static Coordinate getStateCoordinates(int stateValue) {
		//TODO Get from simulator
		
		//TODO goal test: remove once info comes in
		if(stateValue == 10)
			return new Coordinate(10,11); 
		
		return new Coordinate(0,0); 
		
	}
	private static int getSimGoalState() {
		// TODO Get from simulator
		
		//TODO goal test: remove once info comes in
		return 10; 
	}
	private static void savePath(PathObject w)
	{
		//Only save path if no path has yet been found, or a shorter path exists
		if(foundGoalPath==null)
		{
			foundGoalPath = new PathObject(); 
			foundGoalPath.dir = w.dir; 
			foundGoalPath.fVal = w.fVal;
			foundGoalPath.gVal = w.gVal;
			foundGoalPath.hVal = w.hVal;
			foundGoalPath.parent = w.parent; 
			foundGoalPath.heurStateValue = w.heurStateValue; 
		}
		else if(foundGoalPath.fVal > w.fVal)
		{
			foundGoalPath.dir = w.dir; 
			foundGoalPath.fVal = w.fVal;
			foundGoalPath.gVal = w.gVal;
			foundGoalPath.hVal = w.hVal;
			foundGoalPath.parent = w.parent; 
			foundGoalPath.heurStateValue = w.heurStateValue;
		}
		
	}
	
	///Checks if the node heuristic state is equal to the goal state
	private static boolean isGoal(PathObject w)
	{		
		if(w.heurStateValue == getSimGoalState() )
		{
			return true;
		}
		return false;
	}
	
	///Call this method every x number of ticks 
	///Check if staying in a state for too long
	/// Is not stuck if waiting for A* to be done recalculating
	/// or when the robot is slowly moving
	public boolean isStuck()
	{
		//NOTE: keep in mind of velocity changes during robot movement
		//if actually stuck, replan 		
		if(getCurrentState() == previousState && 
				!isWaitingForAStar && 
				!isWaitingForCompletedMove)
		{
			currentImpatientTicks++; 
			//Return true if current ticks of waiting equals our impatient level
			if(currentImpatientTicks == totalImpatientTicks)
			{
				return true; 
			}
		}
		return false;			
	}
	
	///if isStuck is activated, replan from the current state on grid and rerun A*
	public void replan()
	{
		init(getCurrentState()); 
		plan(); 
		
	}

}
