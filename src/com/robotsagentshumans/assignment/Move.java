package com.robotsagentshumans.assignment;


public enum Move {
	Down,
	Up, 
	Left, 
	Right; 
	
	@Override
	  public String toString() {
		    switch(this) {
		      case Down: return "Down";
		  case Up: return "Up";
		  case Left: return "Left";
		  case Right: return "Right";
		  default: throw new IllegalArgumentException();
		}
	}
}
