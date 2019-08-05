package com.as.Airport;

import com.as.Driver.Driver;

//This class essentially calls the function which are required for calculating the number of gates and number of emergency landings.

public class Airport {

	//public static double graphArray[][];


	public int getNumberOfGates(boolean graphFlag) {
		 int noOfRunways = 1;
		Runway.setNoOfRunways(noOfRunways); // Here runway is assumed to be one.
		int numberOfGates = 0;
		int totalWaitsum[] = TotalWaitTimeCalculator.getTotalWaitTime(); // Total wait time for '1' to 'n' number of
																			// gates.

		numberOfGates = getMinimumWaitTime(totalWaitsum); // Retrives the optimum gate number having minimum wait time.
		if (graphFlag) {
			int numberOfFlights = Resources.getNumberOfFlights();

			Resources.graphArray = new double[2][numberOfFlights];
			for (int i = 0; i < numberOfFlights; i++) {
				Resources.graphArray[0][i] = i + 1;
				Resources.graphArray[1][i] = totalWaitsum[i] / ((double) numberOfFlights * (numberOfFlights - 1));
			}
		}

		return numberOfGates;


	}

	private int getMinimumWaitTime(int totalWaitSum[]) {
		int minimumWaitTime = totalWaitSum[0];
		int numberOfGates = 1;

		for (int i = 0; i < Resources.getNumberOfFlights(); i++) {
			if (totalWaitSum[i] < minimumWaitTime && totalWaitSum[i] != 0) {
				numberOfGates = i + 1;
				minimumWaitTime = totalWaitSum[i];
			}
		}
		return numberOfGates;
	}

	}
