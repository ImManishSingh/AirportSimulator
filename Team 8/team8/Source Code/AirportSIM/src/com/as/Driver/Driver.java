package com.as.Driver;


import java.util.LinkedList;
import com.as.Aircraft.Aircraft;
import com.as.Aircraft.AircraftType;
import com.as.Airport.*;
public class Driver {
	public static int rt1,rt2,rt3;
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
		int totalFlights=noOfTypeOne+noOfTypeTwo+noOfTypeThree;
		
		
		
		
		rt1=obj.getRT1();
		bt1=obj.getBT1();
		
		AircraftType aircraftTypeOneObject =new AircraftType(rt1,bt1);
		
		for(int i=0;i<noOfTypeOne;i++)
		{
			Aircraft ac=new Aircraft();
			ac.at=aircraftTypeOneObject;
			
			aircraftLinkedList.add(ac);
		}
		
	
		rt2=obj.getRT2();
		bt2=obj.getBT2();
		
		AircraftType aircraftTypeTwoObject =new AircraftType(rt2,bt2);
		
		for(int i=0;i<noOfTypeTwo;i++)
		{
			Aircraft ac=new Aircraft();
			ac.at=aircraftTypeTwoObject ;
			
			aircraftLinkedList.add(ac);
		}
		
		
		rt3=obj.getRT3();
		bt3=obj.getBT3();
		AircraftType aircraftTypeThreeObject =new AircraftType(rt3,bt3);
		for(int i=0;i<noOfTypeThree;i++)
		{
			Aircraft ac=new Aircraft();
			ac.at=aircraftTypeThreeObject;		
			aircraftLinkedList.add(ac);
		}
		
		peakTime=obj.getPT();
		
		
		
		int noOfOptimalGates=ap.getNumberOfGates(totalFlights, aircraftLinkedList, true);
		System.out.println("Number of Optimal Gates "+ noOfOptimalGates);
		
		
			
			
			  int noOfEmergencyLandings=ap.getNumberOfEmergencyLandings(totalFlights,noOfOptimalGates,aircraftLinkedList,peakTime);
			  System.out.println("Number of Emergency landings : "+ noOfEmergencyLandings);
			  obj.dispose();
			
			  OutputGUI infoObj=new OutputGUI(noOfOptimalGates,noOfEmergencyLandings, 1);
			 
			  
			  Graph graphObj=new Graph(Airport.graphArray);
			 
			 
	}
	}
	public static void Add()
	{
		Aircraft ac = new Aircraft();
		ac.at.runwayTime=rt1;
		ac.at.boardingTime=bt1;
		aircraftLinkedList.addFirst(ac);
	}
	
}

