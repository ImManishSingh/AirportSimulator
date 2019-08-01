package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;
import com.as.Driver.Driver;

public class Airport {
	
	public static double graphArray[][];

	
	public int getNumberOfGates(boolean graphFlag) 
	{
		Runway.setNoOfRunways(1);
		int numberOfGates=0;
		int totalWaitsum[] = TotalWaitSum.getTotalWaitTime();
		
		int minimumWaitTime = totalWaitsum[0];
		
		
		// This loop will find the number of gates for which the total wait time is
		// minimum.
		for (int i = 0; i < Resources.getNumberOfFlights(); i++) {
			if (totalWaitsum[i] < minimumWaitTime && totalWaitsum[i] != 0) {
				numberOfGates = i + 1;
				minimumWaitTime = totalWaitsum[i];
			}
			
		}
		if (graphFlag) 
		{
			int numberOfFlights = Resources.getNumberOfFlights();
			graphArray = new double[2][numberOfFlights];
			for (int i = 0; i < numberOfFlights; i++) 
			{
				graphArray[0][i] = i + 1;
				graphArray[1][i] = totalWaitsum[i] / ((double)numberOfFlights * (numberOfFlights - 1));

			}
		}
		return numberOfGates;
		
	}
	
	public int getNumberOfEmergencyLandings(int OptimalNumberOfGates,int PT) 
	{
		int LastNumberOfFlights = Resources.getNumberOfFlights();
		int NewNumberOfGates;
		
		boolean graphFlag = false;
		
		int time;
		
		int NumberOfFlights=LastNumberOfFlights;
		while (true) {

			NumberOfFlights = NumberOfFlights + 1;
			Driver.Add();
			Resources.setNumberOfFlights(NumberOfFlights);
			Resources.initiliseFreedTimeArray(NumberOfFlights);
			NewNumberOfGates = getNumberOfGates(graphFlag);
			time=Resources.freedTimeArray[OptimalNumberOfGates]/(NumberOfFlights-1);
			System.out.println("Time is "+time);
			
			if (NewNumberOfGates != OptimalNumberOfGates  ||  time>PT) {

				
				System.out.println("For flights= " + NumberOfFlights);
				break;
			}
		}

		int numberOfEmergencyLandings = NumberOfFlights - LastNumberOfFlights - 1;

		return numberOfEmergencyLandings;
	}
}
