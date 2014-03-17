package com.robotsagentshumans.assignment;


import java.util.Comparator;

//Priority Queue Comparator giving priority of fVal
public class PQComparator implements Comparator<PathObject> {
	@Override
	public int compare(PathObject po1, PathObject po2) {
		return (po1.fVal > po2.fVal)?1: ((po1.fVal < po2.fVal)?-1:0) ; 
	} 

}
