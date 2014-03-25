package com.robotsagentshumans.assignment;
//package edu.ucf.cap6671.learners.qlearning;



public class QEnvironment {
	private int size = 10;
	private int[][] rewards;
	private int[][] transition;
	private GridWorld originalWorld;
	
	public QEnvironment(GridWorld world){
		rewards = new int[size][size];
		transition = new int[size*size][size*size];
		originalWorld = world;
		
		setReward();
		setTransition();
	}
		
	public static int getStateForPosition(int row, int col, int ncols){
		return row * ncols + col;
	}
	
	public static Pair getPositionForState(int state){
		int row = state / 10;
		int col = state % 10;
		return new Pair(row, col);
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int[][] getRewards() {
		return rewards;
	}
	
	public int[][] getTransitions() {
		return transition;
	}
	private void setReward(){
		for(int i = 0; i < size; ++i){
			for(int j = 0; j < size; ++j){
				if(originalWorld.gridWorld[i][j].isGoalState()){
					rewards[i][j] = 100;
				}
				else{
					rewards[i][j] = -1 * originalWorld.gridWorld[i][j].getOccupied();
				}
			}
		}					
	}
	
	private void setTransition(){
		int nstates = size * size;
		for(int i = 0; i < nstates; ++i){
			for(int j = 0; j < nstates; ++j){
				if(canGoTo(i, j)){
					Pair coordinates = QEnvironment.getPositionForState(j);
					transition[i][j] = rewards[coordinates.getFirst()][coordinates.getSecond()];
				}
				else{
					transition[i][j] = -1;
				}
			}
		}
	}
	
	private boolean canGoTo(int src, int dest){
		Pair srcC = QEnvironment.getPositionForState(src);
		Pair destC = QEnvironment.getPositionForState(dest);
		
		int rowDistance = Math.abs(destC.getFirst() - srcC.getFirst());
		int colDistance = Math.abs(destC.getSecond() - srcC.getSecond());
		
		//left or right
		if(rowDistance == 0	&& colDistance == 1){
			return true;
		}
		//up or down
		else if(colDistance == 0 && rowDistance == 1){
			return true;
		}
		return false;
	}

}