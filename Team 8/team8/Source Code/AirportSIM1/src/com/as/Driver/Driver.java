package com.as.Driver;


import java.util.LinkedList;
import com.as.Aircraft.Aircraft;
import com.as.Aircraft.AircraftType;
import com.as.Airport.*;


public class Dr
		iver {
	public static int bt1,bt2,bt3,peakTime;
	public static   LinkedList<Aircraft> aircraftLinkedList=new LinkedList<Aircraft>();
	public static void main(String[] args) {

		int noOfTypeOne;
		int noOfTypeTwo;
		int noOfTypeThree;
		//Airport object
		
		Airport ap=new Airport();
		
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

		int totalFlights = noOfTypeOne + noOfTypeTwo + noOfTypeThree;

		AircraftType aircraftTypeOneObject =new AircraftType(obj.getRT1(),obj.getBT1());
		AircraftType aircraftTypeTwoObject =new AircraftType(obj.getRT2(),obj.getBT2());
		AircraftType aircraftTypeThreeObject =new AircraftType(obj.getRT3(),obj.getBT3());

		State z = new State(1,0);

		for(int i=0;i<noOfTypeOne;i++)
		{
			Aircraft ac=new Aircraft(aircraftTypeOneObject,z);
			aircraftLinkedList.add(ac);
		}

		for(int i=0;i<noOfTypeTwo;i++)
		{
			Aircraft ac=new Aircraft(aircraftTypeTwoObject,z);
			aircraftLinkedList.add(ac);
		}

		for(int i=0;i<noOfTypeThree;i++)
		{
			Aircraft ac=new Aircraft(aircraftTypeThreeObject,z);
			aircraftLinkedList.add(ac);
		}
		
		peakTime=obj.getPT();
		
		Resources.setAircraftLinkedList(aircraftLinkedList);
		Resources.setNumberOfFlights(totalFlights);
		Resources.initiliseFreedTimeArray(totalFlights);
		
		int noOfOptimalGates=ap.getNumberOfGates(true);
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
		Aircraft ac=new Aircraft(aircraftTypeOneObject,z);
		aircraftLinkedList.addFirst(ac);
		Resources.setAircraftLinkedList(aircraftLinkedList);
	}
	
}

