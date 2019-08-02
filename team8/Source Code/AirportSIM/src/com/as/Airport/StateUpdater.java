package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

public class StateUpdater {
	
	public static void doChange(int numberOfGates)
	{
		LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
		LinkedList<Runway> runwayLinkedList = Resources.runwayLinkedList;
		LinkedList<Gate> gateLinkedList = Resources.gateLinkedList;
		for (int flightLinkedListCounter = 0; flightLinkedListCounter < aircraftLinkedList.size(); flightLinkedListCounter++)
		{
			Aircraft ac= aircraftLinkedList.get(flightLinkedListCounter);
			if (ac.state.equals("z")){
				ac=zStateOperation(ac);	
			}
			if (ac.state.equals("s")) {
				ac=sStateOperation(ac,runwayLinkedList);		

			}
			if (ac.state.equals("r'")) {
				ac=rDashStateOperation(ac,runwayLinkedList);				
			}
			else if (ac.state.equals("a")) 			{
				ac=aStateOperation(ac);
			}
			else if (ac.state.equals("b")) 	{
				ac=bStateOperation(ac,gateLinkedList,numberOfGates);
			}
			if (ac.state.equals("c")) 			{
				ac=cStateOperation(ac,gateLinkedList);
			}
			if (ac.state.equals("d")) {
				ac=dStateOperation(ac);
			}
			else if (ac.state.equals("e")) {
				ac=eStateOperation(ac,runwayLinkedList);
			}
			if (ac.state.equals("r")) {
				ac=rStateOperation(ac,runwayLinkedList);
			}
		}
	}

	

	private static Aircraft eStateOperation(Aircraft ac, LinkedList<Runway> runwayLinkedList) {
		boolean e_flag = false;
		// The j variable denotes the number of runways.
		for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++) 
		{
			// This loop will check the priority of the fight for the runway.
			for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++) 
			{
				for (int innerItaretor = 0; innerItaretor < Resources.getAircraftLinkedList().size() && innerItaretor != outerIterator; innerItaretor++) 
				{
					if (Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources.getAircraftLinkedList().get(innerItaretor).sWaitTime
							&&Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources.getAircraftLinkedList().get(innerItaretor).eWaitTime) 
					{
						if (runwayLinkedList.get(runwayIterator).status == true) 
						{
							ac.state = "r";
							ac.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
							runwayLinkedList.get(runwayIterator).status = false;
							e_flag = true;
							break;
						}
					}
				}
			}
		}
		if (e_flag == false)
			ac.eWaitTime++;
		return ac;
	}



	private static Aircraft rStateOperation(Aircraft ac, LinkedList<Runway> runwayLinkedList) {
		ac.rstatetimer++;
		if (ac.rstatetimer == ac.at.runwayTime) 
		{
			ac.state = "x";
			runwayLinkedList.get(ac.RunwayNumber).status = true;
			ac.efwt += ac.eWaitTime;
			ac.eWaitTime = 0;
			ac.cstatetimer = 0;
			ac.astatetimer = 0;
			ac.dstatetimer = 0;
			ac.rstatetimer = 0;
			ac.rdstatetimer = 0;
		}
		return ac;
	}



	private static Aircraft dStateOperation(Aircraft ac) {
		ac.dstatetimer++;
		if (ac.dstatetimer == Resources.taxiTime)
			ac.state = "e";
		return ac;
	}

	private static Aircraft cStateOperation(Aircraft ac, LinkedList<Gate> gateLinkedList) {
		ac.cstatetimer++;
		if (ac.cstatetimer == ac.at.boardingTime) 
		{
			ac.state = "d";
			gateLinkedList.get(ac.gateNo).status = true;
		}
		return ac;
	}

	private static Aircraft bStateOperation(Aircraft ac, LinkedList<Gate> gateLinkedList, int numberOfGates) {
		boolean b_flag = false;
		// The j variable denotes the number of gates
		int j;
		for (j = 0; j < numberOfGates; j++) 
		{
			if (gateLinkedList.get(j).status == true) 
			{
				ac.state = "c";
				ac.gateNo = j;
				gateLinkedList.get(j).status = false;
				b_flag = true;
				break;
			}
		}
		if (b_flag == false)
			ac.bWaitTime++;
		
		return ac;
		
	}
	private static Aircraft aStateOperation(Aircraft ac) {
		ac.astatetimer++;
		if (ac.astatetimer == Resources.taxiTime) {
			ac.state = "b";
		}
		return ac;
	}
	private static Aircraft rDashStateOperation(Aircraft ac, LinkedList<Runway> runwayLinkedList) {
		ac.rdstatetimer++;
		// The variable j denotes the number of runways.
		for (int j = 0; j < Runway.getNoOfRunways(); j++) 
		{
			if (runwayLinkedList.get(j).status == false) 
			{
				runwayLinkedList.get(j).rdt++;
			}
			if (runwayLinkedList.get(j).rdt == ac.at.runwayTime) 
			{
				runwayLinkedList.get(j).status = true;
				runwayLinkedList.get(j).rdt = 0;
			}
		}
		if (ac.rdstatetimer == ac.at.runwayTime) 
		{
			ac.state = "a";
			runwayLinkedList.get(ac.RunwayNumber).status = true;
		}
		return ac;
	}

	private static Aircraft sStateOperation(Aircraft ac,LinkedList<Runway> runwayLinkedList) {
		boolean s_state_flag = false;
		// The variable j denotes the number of runways.
		for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++) 
		{
			// This loop will check the priority of the fight for the runway.
			for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++) 
			{
				for (int innerIterator = 0; innerIterator < Resources.getAircraftLinkedList().size() && innerIterator != outerIterator; innerIterator++) 
				{
					if(checkRunwayPriority(outerIterator,innerIterator,runwayIterator,runwayLinkedList))
					{
							ac.state = "r'";
							ac.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
							Resources.s_count--;
							ac.sfwt += ac.sWaitTime;
							ac.sWaitTime = 0;
							runwayLinkedList.get(runwayIterator).status = false;
							s_state_flag = true;
							break;
					}
				}

			}
		}
		if (s_state_flag == false)
				ac.sWaitTime++;

		return ac;
	}

	private static Aircraft zStateOperation(Aircraft ac) {
		if (Resources.s_count < Resources.getFlightBuffer()) 
		{
			ac.state = "s";
		}
		return ac;
	}
	
	private static boolean checkRunwayPriority(int outerIterator,int innerIterator,int runwayIterator,LinkedList<Runway> runwayLinkedList)
	{
		if (Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList().get(innerIterator).sWaitTime
				&& Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList().get(innerIterator).eWaitTime
				&& runwayLinkedList.get(runwayIterator).status == true) 
		{
			return true;
		}
		return false;
	}		
}


