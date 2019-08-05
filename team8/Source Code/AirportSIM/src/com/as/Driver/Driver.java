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
			appendLinkedList(noOfTypeOne, aircraftTypeOneObject);
			// Insert type 1 flights in the linked list.

			rt2 = obj.getrunwayTime2();
			bt2 = obj.getboardingTime2();

			AircraftType aircraftTypeTwoObject = new AircraftType(rt2, bt2);
			appendLinkedList(noOfTypeTwo, aircraftTypeTwoObject);

			rt3 = obj.getrunwayTime3();
			bt3 = obj.getboardingTime3();
			AircraftType aircraftTypeThreeObject = new AircraftType(rt3, bt3);
			appendLinkedList(noOfTypeThree, aircraftTypeThreeObject);

			peakTime = obj.getpeakTime();

			Resources.setAircraftLinkedList(aircraftLinkedList);
			Resources.setNumberOfFlights(totalFlights);
			Resources.initiliseFreedTimeArray(totalFlights);

			int noOfOptimalGates = ap.getNumberOfGates(true);
			System.out.println("Number of Optimal Gates " + noOfOptimalGates);
			EmergencyGateCaculator emergencyGateObject = new EmergencyGateCaculator();
			int noOfEmergencyLandings = emergencyGateObject.getNumberOfEmergencyLandings(noOfOptimalGates, peakTime);
			System.out.println("Number of Emergency landings : " + noOfEmergencyLandings);
			obj.dispose();

			OutputGUI infoObj = new OutputGUI(noOfOptimalGates, noOfEmergencyLandings, 1);

			Graph graphObj = new Graph(Resources.graphArray);

		}
	}

	private static void appendLinkedList(int noOfTypeOne, AircraftType aircraftTypeObject) {
		for (int i = 0; i < noOfTypeOne; i++) {
			Aircraft ac = new Aircraft();
			ac.at = aircraftTypeObject;

			aircraftLinkedList.add(ac);
		}

	}

	public static void Add() {
		Aircraft ac = new Aircraft();
		AircraftType aircraftTypeObject = new AircraftType(rt1, bt1);
		ac.at = aircraftTypeObject;
		aircraftLinkedList.addFirst(ac);
		Resources.setAircraftLinkedList(aircraftLinkedList);
	}

}
