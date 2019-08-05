package com.as.Airport;

//Calculates total wait time for each gate i(i=1,....,n)
public class TotalWaitTimeCalculator {

	
	static int[] freedTimeArray;

	public static int[] getTotalWaitTime() {
		int numberOfGates;
		int numberOfFlights = Resources.getNumberOfFlights();
		numberOfGates = 1;

		freedTimeArray = new int[numberOfFlights];
		int totalWaitsum[] = new int[numberOfFlights];

		for (; numberOfGates <= numberOfFlights; numberOfGates++) {

			Resources.time = 0;
			totalWaitsum[numberOfGates - 1] = GateWaitTimeCalculator.getGateWaitTime(numberOfGates);
		}

		return totalWaitsum;

	}

}
