package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

//When number of gates are determined, this class is reponsible for allocating 'intial state' to each flights
public class InitialStateAllocator {

	public static void initilise(int numberOfGates, int grounded) {
		LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
		LinkedList<Gate> gateLinkedList = Resources.gateLinkedList;
		int numberOfFlights = Resources.getNumberOfFlights();
		int flightCounter = 0;
		// int grounded=1;
		// This loop will assign the state c to the aircrafts in the initial case.
		while (flightCounter < numberOfGates && flightCounter < grounded) {
			aircraftLinkedList.get(flightCounter).state = "c";

			aircraftLinkedList.get(flightCounter).gateNo = flightCounter;
			gateLinkedList.get(flightCounter).status = false;
			flightCounter++;
		}
		// This loop will assign the state b to the aircrafts in the initial case.
		while (flightCounter < grounded) {
			aircraftLinkedList.get(flightCounter).state = "b";

			flightCounter++;
		}
		int flightBuffer = Resources.getFlightBuffer();
		// This loop will assign the state s to the aircrafts in the initial case.
		while (flightCounter < numberOfFlights && flightCounter < grounded + flightBuffer) {
			aircraftLinkedList.get(flightCounter).state = "s";

			Resources.sCount++;
			flightCounter++;
		}
		// This loop will assign the state z to the aircrafts in the initial case.
		while (flightCounter < numberOfFlights) {
			aircraftLinkedList.get(flightCounter).state = "z";

			flightCounter++;
		}
	}

}
