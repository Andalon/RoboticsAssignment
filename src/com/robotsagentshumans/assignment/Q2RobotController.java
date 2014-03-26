/*
 * SchemaDemo.java
 */
package com.robotsagentshumans.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import EDU.gatech.cc.is.abstractrobot.ControlSystemMFN150;
import EDU.gatech.cc.is.clay.NodeVec2;
import EDU.gatech.cc.is.clay.v_FixedPoint_;
import EDU.gatech.cc.is.clay.v_GlobalPosition_r;
import EDU.gatech.cc.is.util.Vec2;


/**
 * Demonstrates navigation with a few motor schemas: move-to-goal,
 * noise, and avoid-obstacle.
 *
 * <A HREF="../../docs/copyright.html">Copyright</A>
 * (c)1999, 2000 Tucker Balch, Georgia Tech Research Corporation and CMU.
 *
 * @author Tucker Balch
 * @version $Revision: 1.1 $
 */
public class Q2RobotController extends ControlSystemMFN150
	{
	public final static boolean DEBUG = true;
	private final static double LOW_RANGE = 0.005;
	private final static double SOUTH_RAD_VALUE = 4.712388980384689576939650749193;
	private final static double NORTH_RAD_VALUE = 1.5707963267948966192313216916398;
	private final static double EAST_RAD_VALUE = 6.283185307179586476925286766559;
	private final static double WEST_RAD_VALUE = 3.1415926535897932384626433832795;

	private String startState = "";
	private String endState = "";
	private List<String> directions = new ArrayList<String>();
	private double goalStateX = 0;
	private double goalStateY = 0;
	private double currentStateX = 0;
	private double currentStateY = 0;
	
	private int directionCount = 0;
	private double currentHeadingSet = 0;
	private double newLocX = 0;
	private double newLocY = 0;
	private boolean goalMet = false;
	
	/**
	 * Configure the control system using Clay.
	 */
	public void configure()
	{
		File configurableFile = new File("qLearnPath1.txt");		
		
		abstract_robot.setBaseSpeed(0.4*abstract_robot.MAX_TRANSLATION);
		
		Scanner in;
		try {
			in = new Scanner(configurableFile);
			startState = in.next();
			
			while (in.hasNext())
			{
				if (in.hasNextInt())
				{
					endState = in.next();
					break;
				}
				directions.add(in.next());
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Start State: " + startState);
		System.out.println("Directions: " + directions);
		System.out.println("End State: " + endState);
		
		currentStateY = Character.getNumericValue(startState.charAt(0));
		currentStateX = Character.getNumericValue(startState.charAt(1));
		goalStateY = Character.getNumericValue(endState.charAt(0));
		goalStateX = Character.getNumericValue(endState.charAt(1));
		
		currentStateY = 10 - (currentStateY + 0.5);
		currentStateX = currentStateX + 0.5;
		goalStateY = 10 - (goalStateY + 0.5);
		goalStateX = goalStateX + 0.5;
		
		//Change strings to all lower case
		String x;
		for (int i=0; i<directions.size(); i++)
		{
			x = directions.get(i).toLowerCase();
			directions.remove(i);
			directions.add(i, x);
		}
		
		System.out.println("Direction List: " + directions);
		NodeVec2 homeBaseGoalLoc = new v_FixedPoint_(goalStateX, goalStateY);
		System.out.println("Start State X,Y = " + currentStateX + "," + currentStateY);
		System.out.println("goalState X,Y = " + goalStateX + "," + goalStateY);
		System.out.println("HOMebase? " + homeBaseGoalLoc);
		
	}

	/**
	 * Called every timestep to allow the control system to
	 * run.
	 */
	public int takeStep()
	{
		Vec2 location;
		long curr_time = abstract_robot.getTime();
		location = abstract_robot.getPosition(curr_time);
		if (goalMet == false)
		{
			abstract_robot.setSpeed(curr_time, 0.4*abstract_robot.MAX_TRANSLATION);
		}
		else
		{
			abstract_robot.setSpeed(curr_time, 0);
		}
		
		if (location.x > (currentStateX - LOW_RANGE) && location.x <= (currentStateX + LOW_RANGE) && 
				location.y > (currentStateY - LOW_RANGE) && location.y <= (currentStateY + LOW_RANGE) && goalMet == false)
		{
			abstract_robot.setSpeed(curr_time, 0);
			System.out.println("Current Heading" + abstract_robot.getSteerHeading(curr_time));
			
			if (location.x > (goalStateX - LOW_RANGE) && location.x <= (goalStateX + LOW_RANGE) &&
					location.y > (goalStateY - LOW_RANGE) && location.y <= (goalStateY + LOW_RANGE))
			{
				System.out.println("Reached Goal, congratulations!");
				goalMet = true;
			}
			
			else if (directions.get(directionCount).equals("up"))
			{
				currentHeadingSet = NORTH_RAD_VALUE;
				abstract_robot.setSteerHeading(curr_time, currentHeadingSet);
				newLocX = currentStateX;
				newLocY = currentStateY + 1.0;
				
			}
			else if (directions.get(directionCount).equals("down"))
			{
				currentHeadingSet = SOUTH_RAD_VALUE;
				abstract_robot.setSteerHeading(curr_time, currentHeadingSet);
				newLocX = currentStateX;
				newLocY = currentStateY - 1.0;
			}
			else if (directions.get(directionCount).equals("left"))
			{
				currentHeadingSet = WEST_RAD_VALUE;
				abstract_robot.setSteerHeading(curr_time, currentHeadingSet);
				newLocX = currentStateX - 1.0;
				newLocY = currentStateY;
			}
			else if (directions.get(directionCount).equals("right"))
			{
				currentHeadingSet = EAST_RAD_VALUE;
				abstract_robot.setSteerHeading(curr_time, currentHeadingSet);
				newLocX = currentStateX + 1.0;
				newLocY = currentStateY;
			}
			
			if (currentHeadingSet == EAST_RAD_VALUE)
			{
				if (abstract_robot.getSteerHeading(curr_time) == currentHeadingSet || abstract_robot.getSteerHeading(curr_time) == 0.0)
				{
					directionCount++;
					currentStateX = newLocX;
					currentStateY = newLocY;
					abstract_robot.setSpeed(curr_time, 0.4*abstract_robot.MAX_TRANSLATION);
				}
			}
			
			else if (currentHeadingSet == abstract_robot.getSteerHeading(curr_time))
			{
				directionCount++;
				currentStateX = newLocX;
				currentStateY = newLocY;
				abstract_robot.setSpeed(curr_time, 0.4*abstract_robot.MAX_TRANSLATION);
			}
		}

		return(CSSTAT_OK);
	}
}
