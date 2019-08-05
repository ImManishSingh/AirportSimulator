package com.as.Airport;

import com.as.Driver.Driver;

public class StateChange {

	public int getNumberOfGates(){

		int initNoOfGates = 1;
		int freedTime = 0;

		int noOfAired = 1;

		int averageWaitTime = 0, prevAverageWaitTime = 1;
		int optimalGates = 0, prevNoOfGates = 1;
		int totalNoOfFlights = Driver.totalNoOfFlights, noOfFreedFlights = 0;

		Airport.linkAllStates();

		for(; noOfAired < totalNoOfFlights-1 ; noOfAired++){
			for (int noOfGates = initNoOfGates; noOfGates < totalNoOfFlights ; noOfGates++){

				Airport.allotStatestoAll(Airport.Start,0, noOfAired);
				Airport.allotStatestoAll(Airport.Gates, noOfAired-1, noOfGates);
				Airport.allotStatestoAll(Airport.GateQueue, noOfAired + noOfGates-1, totalNoOfFlights - noOfGates - noOfAired);

				int time  = 0;
				while(noOfFreedFlights <= totalNoOfFlights){
					Airport.updateAll(time);
					time++;
				}

				if(averageWaitTime == prevAverageWaitTime) {
					optimalGates = noOfGates;
					break;
				}

				prevAverageWaitTime = averageWaitTime;
			}

			if(optimalGates == prevNoOfGates)
				break;

			prevNoOfGates = optimalGates;
		}

		return optimalGates;
	}

}
