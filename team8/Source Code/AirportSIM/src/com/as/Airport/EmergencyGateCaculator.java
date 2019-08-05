package com.as.Airport;

import com.as.Driver.Driver;

public class EmergencyGateCaculator {
	public int getNumberOfEmergencyLandings(int OptimalNumberOfGates, int peakTime) {
		int oldNumberOfFlights = Resources.getNumberOfFlights();
		int NewNumberOfGates;
		boolean graphFlag = false;
		int time;
		int NumberOfFlights = oldNumberOfFlights;
		while (true) {

			NumberOfFlights = NumberOfFlights + 1;
			Driver.Add();
			Resources.setNumberOfFlights(NumberOfFlights);
			Resources.initiliseFreedTimeArray(NumberOfFlights);
			Airport ap=new Airport();
			NewNumberOfGates = ap.getNumberOfGates(graphFlag);
			time = Resources.freedTimeArray[OptimalNumberOfGates] / (NumberOfFlights - 1);
			System.out.println("Time is " + time);
			if (NewNumberOfGates != OptimalNumberOfGates || time > peakTime) {  
				System.out.println("For flights= " + NumberOfFlights);
				break;
			}
		}
		int numberOfEmergencyLandings = NumberOfFlights - oldNumberOfFlights - 1;
		return numberOfEmergencyLandings;
	}

}
