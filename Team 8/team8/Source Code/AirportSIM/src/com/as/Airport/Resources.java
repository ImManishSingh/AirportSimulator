package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

public class Resources {
	
	private static LinkedList<Aircraft> aircraftLinkedList=null;
	private static int numberOfFlights=0;
	private static int flightBuffer=7;
	public static int s_count=0;
	public static int time=0;
	static int taxiTime = 15;
	public static int [] freedTimeArray;
	public static LinkedList<Gate> gateLinkedList;
	public static LinkedList<Runway> runwayLinkedList;
	
	
	public static void setAircraftLinkedList(LinkedList<Aircraft> list)
	{
		aircraftLinkedList = list;
	}
	public static LinkedList<Aircraft> getAircraftLinkedList()
	{
		return aircraftLinkedList;
	}
	public static void setNumberOfFlights(int no)
	{
		numberOfFlights = no;
	}
	public static int getNumberOfFlights()
	{
		return numberOfFlights;
				
	}
	public static int getFlightBuffer()
	{
		return flightBuffer;
	}
	public static void initiliseFreedTimeArray(int numberOfFlights)
	{
		freedTimeArray=new int[numberOfFlights];
	}

}
