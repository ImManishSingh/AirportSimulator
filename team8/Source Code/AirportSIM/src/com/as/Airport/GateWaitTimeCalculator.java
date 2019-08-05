package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

//This class calculates the wait time spent by all the planes for each i gate(i,...,n) 

 class GateWaitTimeCalculator {
	public static int getGateWaitTime(int numberOfGates) {
		LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
		int numberOfFlights = Resources.getNumberOfFlights();
		Resources.gateLinkedList = Gate.createLinkedList(numberOfGates);
		Resources.runwayLinkedList = Runway.createRunwayLinkedList(Runway.noOfRunways);
		int grounded = 1, nonGrounded = numberOfFlights - 1;
		int s_state_wait = 0, e_state_wait = 0, b_state_wait = 0;
		for (; grounded <= numberOfFlights - 1 && nonGrounded >= 1; grounded++, nonGrounded--) {
			InitialStateAllocator.initilise(numberOfGates, grounded);
			while (true) {
				StateUpdater.doChange(numberOfGates);
				Resources.time++;
				if (checkExitState())
					break;
			}
			//This loop is for calculating the total wait time of state s, state b, state e
			for (int i = 0; i < aircraftLinkedList.size(); i++) {
				s_state_wait = aircraftLinkedList.get(i).sStateSingleConfigWait + s_state_wait;
				b_state_wait = aircraftLinkedList.get(i).bWaitTime + b_state_wait;
				e_state_wait = aircraftLinkedList.get(i).eStateSingleConfigWait + e_state_wait;
			}

			aircraftLinkedList = resetToZero(aircraftLinkedList);
		}
		Aircraft.freedtime = Resources.time;
		for (int i = 0; i < numberOfFlights; i++) {
			Resources.freedTimeArray[i] = Aircraft.freedtime;
		}
		int totalWaitsum = s_state_wait + b_state_wait + e_state_wait;

		s_state_wait = b_state_wait = e_state_wait = 0;
		Resources.gateLinkedList.clear();
		Resources.runwayLinkedList.clear();
		Resources.time = 0;
		return totalWaitsum;
	}

	private static LinkedList<Aircraft> resetToZero(LinkedList<Aircraft> aircraftLinkedList) {

		for (int i = 0; i < aircraftLinkedList.size(); i++) {
			aircraftLinkedList.get(i).sStateSingleConfigWait = 0;
			aircraftLinkedList.get(i).bWaitTime = 0;
			aircraftLinkedList.get(i).eStateSingleConfigWait = 0;

		}
		return aircraftLinkedList;
	}

	public static boolean checkExitState() {
		int x_state_counter = 0;
		for (int i = 0; i < Resources.getAircraftLinkedList().size(); i++) {
			if (Resources.getAircraftLinkedList().get(i).state.equals("x")) {
				x_state_counter++;
			}
		}
		// This denotes the condition when all fights moves to state x(exit state).
		if (x_state_counter == Resources.getNumberOfFlights())
			return true;
		else
			return false;
	}

}
