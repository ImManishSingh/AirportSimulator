package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

//This class sets the values used by other classes.
public class Resources {

	public static int sCount = 0;
	public static int time = 0;
	public static final int taxiTime = 15; // This is the time taken from runway to boarding gate which is taken to be
											// constant for all type of planes
	public static int[] freedTimeArray;
	public static LinkedList<Gate> gateLinkedList;
	public static LinkedList<Runway> runwayLinkedList;
	private static LinkedList<Aircraft> aircraftLinkedList = null;
	private static int numberOfFlights = 0;
	private static int flightBuffer = 7;
	public static double graphArray[][];

	public static void setAircraftLinkedList(LinkedList<Aircraft> list) {
		aircraftLinkedList = list;
	}

	public static LinkedList<Aircraft> getAircraftLinkedList() {
		return aircraftLinkedList;
	}

	public static void setNumberOfFlights(int no) {
		numberOfFlights = no;
	}

	public static int getNumberOfFlights() {
		return numberOfFlights;

	}

	public static int getFlightBuffer() {
		return flightBuffer;
	}

	public static void initiliseFreedTimeArray(int numberOfFlights) {
		freedTimeArray = new int[numberOfFlights];
	}

}
