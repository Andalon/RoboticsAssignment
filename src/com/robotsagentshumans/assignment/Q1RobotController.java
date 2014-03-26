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
public class Q1RobotController extends ControlSystemMFN150
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
		File configurableFile = new File("qLearnPath0.txt");		
		
		
	}

	/**
	 * Called every timestep to allow the control system to
	 * run.
	 */
	public int takeStep()
	{
		return 0;
	}
}
