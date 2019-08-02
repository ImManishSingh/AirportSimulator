package com.as.Driver;

import java.util.LinkedList;
import com.as.Aircraft.Aircraft;
import com.as.Aircraft.AircraftType;
import com.as.Airport.*;

//This class takes input from GUI and passes it to the respective functions
public class Driver {
	public static int rt1, rt2, rt3; // Input runway time of plane type1(rt1), and respectively for rest of the
										// types.
	public static int bt1, bt2, bt3, peakTime; // Input boarding time of plane type1(bt1), and respectively for rest of
												// the types. peakTime in hours.
	public static LinkedList<Aircraft> aircraftLinkedList = new LinkedList<Aircraft>();

	public static void main(String[] args) {
		int noOfTypeOne; // Number of type one flights.
		int noOfTypeTwo;
		int noOfTypeThree;
		// Airport object

		Airport ap = new Airport();

		AirportSimulatorGUI obj = new AirportSimulatorGUI();
		synchronized (obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			noOfTypeOne = obj.getnoOfFlight1();
			noOfTypeTwo = obj.getnoOfFlight2();
			noOfTypeThree = obj.getnoOfFlight3();
			int totalFlights = noOfTypeOne + noOfTypeTwo + noOfTypeThree;

			rt1 = obj.getrunwayTime1();
			bt1 = obj.getboardingTime1();

			AircraftType aircraftTypeOneObject = new AircraftType(rt1, bt1);
			// Insert type 1 flights in the linked list.
			for (int i = 0; i < noOfTypeOne; i++) {
				Aircraft ac = new Aircraft();
				ac.at = aircraftTypeOneObject;

				aircraftLinkedList.add(ac);
			}

			rt2 = obj.getrunwayTime2();
			bt2 = obj.getboardingTime2();

			AircraftType aircraftTypeTwoObject = new AircraftType(rt2, bt2);

			for (int i = 0; i < noOfTypeTwo; i++) {
				Aircraft ac = new Aircraft();
				ac.at = aircraftTypeTwoObject;

				aircraftLinkedList.add(ac);
			}

			rt3 = obj.getrunwayTime3();
			bt3 = obj.getboardingTime3();
			AircraftType aircraftTypeThreeObject = new AircraftType(rt3, bt3);

			for (int i = 0; i < noOfTypeThree; i++) {
				Aircraft ac = new Aircraft();
				ac.at = aircraftTypeThreeObject;
				aircraftLinkedList.add(ac);
			}

			peakTime = obj.getpeakTime();

			Resources.setAircraftLinkedList(aircraftLinkedList);
			Resources.setNumberOfFlights(totalFlights);
			Resources.initiliseFreedTimeArray(totalFlights);

			int noOfOptimalGates = ap.getNumberOfGates(true);
			System.out.println("Number of Optimal Gates " + noOfOptimalGates);

			int noOfEmergencyLandings = ap.getNumberOfEmergencyLandings(noOfOptimalGates, peakTime);
			System.out.println("Number of Emergency landings : " + noOfEmergencyLandings);
			obj.dispose();

			OutputGUI infoObj = new OutputGUI(noOfOptimalGates, noOfEmergencyLandings, 1);

			Graph graphObj = new Graph(Airport.graphArray);

		}
	}

	public static void Add() {
		Aircraft ac = new Aircraft();
		ac.at.runwayTime = rt1;
		ac.at.boardingTime = bt1;
		aircraftLinkedList.addFirst(ac);
		Resources.setAircraftLinkedList(aircraftLinkedList);
	}

}
