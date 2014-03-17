package com.robotsagentshumans.assignment;

import java.util.Comparator;

public class ClosedTreeComparator implements Comparator<PathObject> {
	@Override
	public int compare(PathObject po1, PathObject po2) {
		//System.out.println("In tree equals " + po1.heurStateValue + "  " + po2.heurStateValue); 
		return (po1.heurStateValue > po2.heurStateValue)?1: ((po1.heurStateValue < po2.heurStateValue)?-1:0) ; 
	} 
}
