package com.as.Airport;

import java.util.LinkedList;

//For the case of multiple runway, this will check and assign runway to the incoming/outgoing flights
public class Runway {
	static int noOfRunways = 0;
	boolean status; // This variable denotes whether the runway is occupied or not.
	private int runwayNumber; // This variable denotes runway number assigned to each flight
	int runwayWaitTime; // Time status for runway during landing

	public Runway() {
		this.status = true;
		this.runwayWaitTime = 0;
	}

	public static void setNoOfRunways(int no) {
		noOfRunways = no;
	}

	public boolean isStatus() {
		return status;
	}

	public int getRunwayNumber() {
		return runwayNumber;
	}

	public int getRunwayWaitTime() {
		return runwayWaitTime;
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