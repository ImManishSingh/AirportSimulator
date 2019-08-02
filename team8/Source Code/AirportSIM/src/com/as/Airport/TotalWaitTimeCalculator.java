package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;
//Calculates total wait time for each gate i(i=1,....,n)
public class TotalWaitTimeCalculator {
	
	static int numberOfGates; 										
	static int numberOfRunway = 1; 
	
	static int taxiTime = 15; 
	
	public static double graphArray[][]; 
	
	boolean timeExceedFlag = false;
	boolean freedTimeFlag=true;
	static int [] freedTimeArray;
	
	
	public static int[] getTotalWaitTime()
	{
		LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
		int numberOfFlights = Resources.getNumberOfFlights();
		numberOfGates = 1;
		
		
		freedTimeArray=new int[numberOfFlights];
		
		int grounded = 1, nonGrounded = numberOfFlights - 1;
		
		int time = 0, flightBuffer = 7, s_count = 0;
		
		int s_state_wait = 0, e_state_wait = 0, b_state_wait = 0;
		int totalWaitsum[] = new int[numberOfFlights];
		
		for (; numberOfGates <= numberOfFlights; numberOfGates++) 
		{
			System.out.println("#######\n"+"Gates:"+numberOfGates);
			Resources.time=0;
			totalWaitsum[numberOfGates-1] = GateWaitTimeCalculator.getGateWaitTime(numberOfGates); 
			//System.out.println("$$"+totalWaitsum[numberOfGates-1]);
		}
		
		return totalWaitsum;
		
	}

}
