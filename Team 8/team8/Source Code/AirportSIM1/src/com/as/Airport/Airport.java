package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;
import com.as.Driver.Driver;

import com.as.Airport.State;

public class Airport {

	public static double graphArray[][];
	public Aircraft[] flightsArray;

	public static State Start = new State(100,0);
	public static State Hover = new State(5,1);
	public static State Landing = new State(1,2);
	public static State GateQueue = new State(100,3);
	public static State Gates = new State(1,4);
	public static State RunwayQueue = new State(100,5);
	public static State TakeOff = new State(1,6);
	public static State Flew = new State(100,7);

	public static void linkAllStates(){

		Start.link(Hover);
		Hover.link(Landing);
		Landing.link(GateQueue);
		GateQueue.link(Gates);
		Gates.link(RunwayQueue);
		RunwayQueue.link(TakeOff);
		TakeOff.link(Flew);
	}


	public int getNumberOfGates(boolean graphFlag) {

		Runway.setNoOfRunways(1);
		int numberOfGates = 0;
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
		if (graphFlag) {
			int numberOfFlights = Resources.getNumberOfFlights();
			graphArray = new double[2][numberOfFlights];
			for (int i = 0; i < numberOfFlights; i++) {
				graphArray[0][i] = i + 1;
				graphArray[1][i] = totalWaitsum[i] / ((double) numberOfFlights * (numberOfFlights - 1));

			}
		}
		return numberOfGates;

	}

	public int getNumberOfEmergencyLandings(int OptimalNumberOfGates, int PT) {
		int LastNumberOfFlights = Resources.getNumberOfFlights();
		int NewNumberOfGates;

		boolean graphFlag = false;

		int time;

		int NumberOfFlights = LastNumberOfFlights;
		while (true) {

			NumberOfFlights = NumberOfFlights + 1;
			//sDriver.Add();
			Resources.setNumberOfFlights(NumberOfFlights);
			Resources.initiliseFreedTimeArray(NumberOfFlights);
			NewNumberOfGates = getNumberOfGates(graphFlag);
			time = Resources.freedTimeArray[OptimalNumberOfGates] / (NumberOfFlights - 1);
			System.out.println("Time is " + time);

			if (NewNumberOfGates != OptimalNumberOfGates || time > PT) {


				System.out.println("For flights= " + NumberOfFlights);
				break;
			}
		}

		int numberOfEmergencyLandings = NumberOfFlights - LastNumberOfFlights - 1;

		return numberOfEmergencyLandings;
	}

	public static void allotStatestoAll(State S, int initValue, int N){
		for (int i = initValue ; i< N+initValue-1 ; i++){
			Driver.aircraftLinkedList.get(i).allotPosition(S);
		}
	}

	public static void updateAll( int time){
		for(int i =0; i<Driver.aircraftLinkedList.size(); i++){
			Driver.aircraftLinkedList.get(i).updatePosition(time);
		}
	}
}



