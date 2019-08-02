package com.as.Airport;

import java.util.LinkedList;
//For the case of multiple runway, this will check and assign runway to the incoming/outgoing flights
public class Runway {
	static int noOfRunways=0;
	boolean status;  // This variable denotes whether the runway is occupied or not.
	int runwayNumber;  // This variable denotes number assigned to each gate.
	int rdt;    //Runway wait time
	
	
	public Runway()
	{
		this.status=true;
		this.rdt=0;
	}
	
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