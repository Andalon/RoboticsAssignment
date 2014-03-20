package com.robotsagentshumans.assignment;
//package edu.ucf.cap6671.learners.qlearning;

public class QEnvironment {
	private int size = 6;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int[][] getRewards() {
		return Rewards;
	}
	private final int Rewards[][] = new int[][] {
		{-1, -1, -1, -1, 0, -1}, 
        {-1, -1, -1, 0, -1, 100}, 
        {-1, -1, -1, 0, -1, -1}, 
        {-1, 0, 0, -1, 0, -1}, 
        {0, -1, -1, 0, -1, 100}, 
        {-1, 0, -1, -1, 0, 100}};
	
}
