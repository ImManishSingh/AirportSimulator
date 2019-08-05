package com.as.Driver;


import java.util.LinkedList;
import com.as.Aircraft.Aircraft;
import com.as.Aircraft.AircraftType;
import com.as.Airport.*;


public class Driver {
	public static int bt1,bt2,bt3,peakTime;
	public static   LinkedList<Aircraft> aircraftLinkedList=new LinkedList<Aircraft>();
	public static int totalNoOfFlights;

	public static void main(String[] args) {

		int noOfTypeOne;
		int noOfTypeTwo;
		int noOfTypeThree;

		//Airport object

		Airport ap=new Airport();

		StateChange ST = new StateChange();
		
		AirportSimulatorGUI obj = new AirportSimulatorGUI();
		synchronized(obj) {
		try {
			obj.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		noOfTypeOne=obj.getNF1();
		noOfTypeTwo=obj.getNF2();
		noOfTypeThree=obj.getNF3();

		totalNoOfFlights = noOfTypeOne + noOfTypeTwo + noOfTypeThree;

		peakTime=obj.getPT();
		
		Resources.setAircraftLinkedList(aircraftLinkedList);
		Resources.setNumberOfFlights(totalNoOfFlights);
		Resources.initiliseFreedTimeArray(totalNoOfFlights);
		
		int noOfOptimalGates = ST.getNumberOfGates();
		System.out.println("Number of Optimal Gates "+ noOfOptimalGates);
		
	
		  int noOfEmergencyLandings=ap.getNumberOfEmergencyLandings(noOfOptimalGates,peakTime);
		  System.out.println("Number of Emergency landings : "+ noOfEmergencyLandings);
		  obj.dispose();
		
		  OutputGUI infoObj=new OutputGUI(noOfOptimalGates,noOfEmergencyLandings, 1);
		 
		  
		  Graph graphObj=new Graph(Airport.graphArray);
		 
			 
		}

		//WaitTimeMatrix(Type, currentPosition);
	}

	int[][] WaitTimeMatrix = new int[3][8];


	public static void Add(AircraftType aircraftTypeOneObject,State z)
	{
		Aircraft ac=new Aircraft(1,z);
		aircraftLinkedList.addFirst(ac);
		Resources.setAircraftLinkedList(aircraftLinkedList);
	}
	
}

