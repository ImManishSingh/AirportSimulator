package com.as.Airport;

import com.as.Driver.Driver;


//This class essentially calls the function which are required for calculating the number of gates and number of emergency landings.

public class Airport {
	
	public static double graphArray[][];

	
	public int getNumberOfGates(boolean graphFlag) 
	{
	    int noOfRunways=1;
		Runway.setNoOfRunways(noOfRunways);   //Here runway is assumed to be one.
		int numberOfGates=0;
		int totalWaitsum[] = TotalWaitTimeCalculator.getTotalWaitTime();    //Total wait time for '1' to 'n' number of gates.
				
		numberOfGates=getMinimumWaitTime(totalWaitsum); //Retrives the optimum gate number having minimum wait time.
		
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
	
	private int getMinimumWaitTime(int totalWaitSum[]) {
		int minimumWaitTime = totalWaitSum[0];
		int numberOfGates=1;
		
		for (int i = 0; i < Resources.getNumberOfFlights(); i++) {
			if (totalWaitSum[i] < minimumWaitTime && totalWaitSum[i] != 0) {
				numberOfGates = i + 1;
				minimumWaitTime = totalWaitSum[i];
			}
		}
		return numberOfGates;
	}

	public int getNumberOfEmergencyLandings(int OptimalNumberOfGates,int PT) 
	{
		int oldNumberOfFlights = Resources.getNumberOfFlights();
		int NewNumberOfGates;
		
		boolean graphFlag = false;
		
		int time;
		
		int NumberOfFlights=oldNumberOfFlights;
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
		int numberOfEmergencyLandings = NumberOfFlights - oldNumberOfFlights - 1;
		return numberOfEmergencyLandings;
	}
}
