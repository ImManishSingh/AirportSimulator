package com.as.Airport;

//Calculates total wait time for each gate i(i=1,....,n)
public class TotalWaitTimeCalculator {

	static int numberOfGates;
	//static int numberOfRunway = 1;

	static int taxiTime = 15;

	public static double graphArray[][];

	boolean timeExceedFlag = false;
	boolean freedTimeFlag = true;
	static int[] freedTimeArray;

	public static int[] getTotalWaitTime() {
		int numberOfFlights = Resources.getNumberOfFlights();
		numberOfGates = 1;

		freedTimeArray = new int[numberOfFlights];
		int totalWaitsum[] = new int[numberOfFlights];

		for (; numberOfGates <= numberOfFlights; numberOfGates++) {
			System.out.println("#######\n" + "Gates:" + numberOfGates);
			Resources.time = 0;
			totalWaitsum[numberOfGates - 1] = GateWaitTimeCalculator.getGateWaitTime(numberOfGates);
		}

		return totalWaitsum;

	}

}
