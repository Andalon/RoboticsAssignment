package com.robotsagentshumans.assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import EDU.gatech.cc.is.abstractrobot.ControlSystemMFN150;

public class DummyController extends ControlSystemMFN150 
{
	public void configure()
	{
		File configurableFile = new File("AStarPath.txt");		
		
		Scanner in;
		try {
			in = new Scanner(configurableFile);
			String input;
			System.out.print("Path: ");
			while (in.hasNext())
			{
				input = in.next();
				System.out.print(input + " ");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int takeStep()
	{
		return 0;
	}

}
