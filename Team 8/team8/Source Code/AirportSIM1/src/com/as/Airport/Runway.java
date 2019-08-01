package com.as.Airport;

import java.util.LinkedList;

public class Runway {
	static int noOfRunways=0;
	boolean status = true;  // This variable denotes whether the gate is occupied or not.
	int runwayNumber;  // This variable denotes number assigned to each gate.
	int rdt=0;
	
	public static void setNoOfRunways(int no)
	{
		noOfRunways = no;
	}
	
	public static int getNoOfRunways()
	{
		return noOfRunways;
	}
	
	public static LinkedList<Runway> createRunwayLinkedList(int numberOfRunways) {

		LinkedList<Runway> runwayLinkedList = new LinkedList<Runway>();

		for (int runwayCounter = 0; runwayCounter < numberOfRunways; runwayCounter++) {
			Runway runwayObject = new Runway();
			runwayObject.runwayNumber = runwayCounter;
			runwayLinkedList.add(runwayObject);
		}
		return runwayLinkedList;
	}

}