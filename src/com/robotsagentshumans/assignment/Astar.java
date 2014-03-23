package com.robotsagentshumans.assignment;

import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;


public class Astar {
		
	PQComparator comparator = new PQComparator();
	PriorityQueue<PathObject> PQ = new PriorityQueue<PathObject>(1000, comparator);
	ClosedTreeComparator closedTreeComp = new ClosedTreeComparator(); 
	TreeSet<PathObject> closedTree = new TreeSet<PathObject>(closedTreeComp); 
	File file = new File("AStarPath.txt");
    // creates a FileWriter Object
    FileWriter writer; 
	//Checks for movement and being stuck on the map
	
	//Retries n number of times until decides if it is stuck
	int totalImpatientTicks = 5; 
	//Retries n number of times until decides if it is stuck
	int currentImpatientTicks = 0; 
	//Shouldn't be in the same state after a move is completed
	int previousState = -1; 
	//Shouldn't be defined as stuck if waiting for the A* alg to finish
	boolean isWaitingForAStar = false;
	//Shouldn't be defined as stuck if waiting for the robot to finish moving
	boolean isWaitingForCompletedMove = false; 
	//The current state of the robot
	int gCurrentState = -1; 
	
	GridWorld gridWorld; 
	static PathObject foundGoalPath = null; 
	static Stack<Tuple> goalPath = new Stack<Tuple>(); 
	public Astar(GridWorld gridworld) {
		System.out.println("A* ");
		gridWorld = gridworld; 
		//First state
		init(getCurrentState());		
	}
	///Initialize function to setup first path object
	/// Adds first state to priority queue
	public void init(int currentState)
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

	
	///retrieve from simulator the current state of robot from grid world
	private int getCurrentState() 
	{
		//TODO for Griff :D
		return gridWorld.getCurrentState();
		//return 0; 
	}
	///A* High level method, Details of each step within
	public void plan() 
	{
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		//Set isWaitingForAStar to true
		isWaitingForAStar = true; 
				
		//continue with A* until the priority queue is empty
		while(!PQ.isEmpty())
		{
			//W = Pop first off PQ
			PathObject w = PQ.poll(); 
			//System.out.println("Heur State " + w.heurStateValue); 
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
			//Print for example map
			  System.out.println("StateID: " + w.heurStateValue); 
			  System.out.println("G(x): " + w.gVal); 
			  System.out.println("H(x): " + w.hVal); 
			  System.out.println("F(x): " + w.fVal); 
			//Generate offspring, calculate f = g + h,
			//add offspring to either open queue or closed tree
			generateOffspring(w); 	
			
			//Add W to closedMap
			closedTree.add(w); 
		}
		//if goal state found, yay! else problem
		if(foundGoalPath == null)
		{
			System.out.println("Uh oh! no path found..."); 
			System.exit(0); 
		}
		//Generate path w/ PathObject parents (recursively) 
		generatePath(foundGoalPath); 
		System.out.println(gridWorld.getGoalState()); 
		try {
			writer.write(""+gridWorld.getGoalState());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Send path somewhere, 
			//at each move, trigger isWaiting until move is done
			//When move is done, check if the robot is stuck
			
		//Clear PQ and closed list						
		PQ.clear();
		closedTree.clear();
		//Set isWaitingForAStar to false
		isWaitingForAStar = false; 
		try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End of A*");
	}
	
	
	///Stacks the moves from beginning to end, along w/ moves to take
	private void generatePath(PathObject path)
	{
		if(path.parent != null)
		{
			
			generatePath(path.parent); 
		}
		//First reach will be start state
		if(path.dir == null){
			System.out.print(path.heurStateValue + ", ");
			try {
				writer.write(path.heurStateValue+" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.print(path.dir  + ", ");
			try {
				writer.write(path.dir+" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
	}
	private void generateOffspring(PathObject w) 
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
				  //System.out.println("closedTree contains " + kid.heurStateValue); 
			  }
		}

		
	}
	/// Gets the state value of the grid from parent state w/ move dir
	private int getStateVal(int heurStateValue, Move dir) {
		
		return gridWorld.getHeuristicState(heurStateValue, dir.toString()); 		
		//return new Random().nextInt(100); 
	}
	
	/// Calculates Euclidean distance from Heuristic State value to the goal
	private double heuristicVal(int heurStateValue) {
		Point node = getStateCoordinates(heurStateValue); 		
		Point goal = getStateCoordinates(getSimGoalState()); 
		//System.out.println("Node " + node.x + ", " + node.y) ;
		//System.out.println("Goal " + goal.x + ", " + goal.y) ;
		double dx = Math.abs(node.x - goal.x);
		double dy = Math.abs(node.y - goal.y);
		//System.out.println("dx " + dx) ;
		//System.out.println("dy " + dy) ;
		//System.out.println("Euclid Dist " + Math.sqrt(Math.pow((dx+ dy), 2))); 
		return Math.sqrt(Math.pow((dx+ dy), 2));
	}
	private Point getStateCoordinates(int stateValue) {
		
		return gridWorld.getXYCoordinatesofState(stateValue);
		//return new Point(5,5); 
		
	}
	private int getSimGoalState() {
		
		return gridWorld.getGoalState();
		//return 10; 
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
	private boolean isGoal(PathObject w)
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
