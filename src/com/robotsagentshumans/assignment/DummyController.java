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
public class DummyController extends ControlSystemMFN150
	{
	public final static boolean DEBUG = true;
	private final static double LOW_RANGE = 0.001;
	private final static double SOUTH_RAD_VALUE = 4.712388980384689576939650749193;
	private final static double NORTH_RAD_VALUE = 1.5707963267948966192313216916398;
	private final static double EAST_RAD_VALUE = 0.0;
	private final static double WEST_RAD_VALUE = 3.1415926535897932384626433832795;
	private NodeVec2	turret_configuration;
	private NodeVec2	steering_configuration;
	private double mtggain = 1.0;
	private double oldmtggain = 1.0;
	private double avoidgain = 0.8;
	private double oldavoidgain = 0.8;
	private double noisegain = 0.2;
	private double oldnoisegain = 0.2;

	private String startState = "";
	private String endState = "";
	private List<String> directions = new ArrayList<String>();
	private double goalStateX = 0;
	private double goalStateY = 0;
	private double currentStateX = 0;
	private double currentStateY = 0;
	
	private DecimalFormat df = new DecimalFormat("#.0");
	
	/**
	 * Configure the control system using Clay.
	 */
	public void configure()
	{
		File configurableFile = new File("AStarPath.txt");		
		
		abstract_robot.setBaseSpeed(0.4*abstract_robot.MAX_TRANSLATION);
		
		NodeVec2 globalPosition = new v_GlobalPosition_r(abstract_robot);
		
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
		Vec2 result;
		Vec2 location;
		long curr_time = abstract_robot.getTime();
		
		abstract_robot.setSpeed(curr_time, 0.4*abstract_robot.MAX_TRANSLATION);
		location = abstract_robot.getPosition(curr_time);
		
		//1.499 && location.x < 1.51
		//if ((Double.parseDouble(df.format(location.x))) == 1.5){
		if (location.x > (currentStateX - LOW_RANGE + 1.0) && location.x < (currentStateX + LOW_RANGE + 1.0))
		{
			abstract_robot.setSpeed(curr_time, 0);
			System.out.println("Current Heading" + abstract_robot.getSteerHeading(curr_time));
			abstract_robot.setSteerHeading(curr_time, SOUTH_RAD_VALUE);
			if (abstract_robot.getSteerHeading(curr_time) == SOUTH_RAD_VALUE)
			{
				currentStateX = currentStateX + 2.0;
			}
		}
		
		if (location.y > (currentStateY - LOW_RANGE - 1.0) && location.y < (currentStateY + LOW_RANGE - 1.0))
		{
			abstract_robot.setSpeed(curr_time, 0);
			System.out.println("Current Heading" + abstract_robot.getSteerHeading(curr_time));
			abstract_robot.setSteerHeading(curr_time, WEST_RAD_VALUE);
			if (abstract_robot.getSteerHeading(curr_time) == WEST_RAD_VALUE)
			{
				currentStateY = currentStateY + 2.0;
			}
		}
		
		System.out.println("Coordinates?" + location.x + "," + location.y);

		return(CSSTAT_OK);
	}
	
	
	}
