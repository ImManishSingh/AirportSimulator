package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;

//Checks the present state and assigns next state accordingly.
public class StateUpdater {

	public static void doChange(int numberOfGates) {
		LinkedList<Aircraft> aircraftLinkedList = Resources.getAircraftLinkedList();
		LinkedList<Runway> runwayLinkedList = Resources.runwayLinkedList;
		LinkedList<Gate> gateLinkedList = Resources.gateLinkedList;
		for (int flightLinkedListCounter = 0; flightLinkedListCounter < aircraftLinkedList
				.size(); flightLinkedListCounter++) {
			Aircraft airCraftObject = aircraftLinkedList.get(flightLinkedListCounter);
			if (airCraftObject.state.equals("z")) {
				airCraftObject = zStateOperation(airCraftObject);
			}
			if (airCraftObject.state.equals("s")) {
				airCraftObject = sStateOperation(airCraftObject, runwayLinkedList);

			}
			if (airCraftObject.state.equals("r'")) {
				airCraftObject = rDashStateOperation(airCraftObject, runwayLinkedList);
			} else if (airCraftObject.state.equals("a")) {
				airCraftObject = aStateOperation(airCraftObject);
			} else if (airCraftObject.state.equals("b")) {
				airCraftObject = bStateOperation(airCraftObject, gateLinkedList, numberOfGates);
			}
			if (airCraftObject.state.equals("c")) {
				airCraftObject = cStateOperation(airCraftObject, gateLinkedList);
			}
			if (airCraftObject.state.equals("d")) {
				airCraftObject = dStateOperation(airCraftObject);
			} else if (airCraftObject.state.equals("e")) {
				airCraftObject = eStateOperation(airCraftObject, runwayLinkedList);
			}
			if (airCraftObject.state.equals("r")) {
				airCraftObject = rStateOperation(airCraftObject, runwayLinkedList);
			}
		}
	}

	private static Aircraft eStateOperation(Aircraft airCraftObject, LinkedList<Runway> runwayLinkedList) {
		boolean e_flag = false;
		// The j variable denotes the number of runways.
		for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++) {
			// This loop will check the priority of the fight for the runway.
			for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++) {
				for (int innerItaretor = 0; innerItaretor < Resources.getAircraftLinkedList().size()
						&& innerItaretor != outerIterator; innerItaretor++) {
					if (Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources
							.getAircraftLinkedList().get(innerItaretor).sWaitTime
							&& Resources.getAircraftLinkedList().get(outerIterator).eWaitTime >= Resources
									.getAircraftLinkedList().get(innerItaretor).eWaitTime) {
						if (runwayLinkedList.get(runwayIterator).status == true) {
							airCraftObject.state = "r";
							airCraftObject.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
							runwayLinkedList.get(runwayIterator).status = false;
							e_flag = true;
							break;
						}
					}
				}
			}
		}
		if (e_flag == false)
			airCraftObject.eWaitTime++;
		return airCraftObject;
	}

	private static Aircraft rStateOperation(Aircraft airCraftObject, LinkedList<Runway> runwayLinkedList) {
		airCraftObject.rstatetimer++;
		if (airCraftObject.rstatetimer == airCraftObject.at.runwayTime) {
			airCraftObject.state = "x";
			runwayLinkedList.get(airCraftObject.RunwayNumber).status = true;
			airCraftObject.eStateSingleConfigWait += airCraftObject.eWaitTime;
			airCraftObject.eWaitTime = 0;
			airCraftObject.cstatetimer = 0;
			airCraftObject.astatetimer = 0;
			airCraftObject.dstatetimer = 0;
			airCraftObject.rstatetimer = 0;
			airCraftObject.rdstatetimer = 0;
		}
		return airCraftObject;
	}

	private static Aircraft dStateOperation(Aircraft airCraftObject) {
		airCraftObject.dstatetimer++;
		if (airCraftObject.dstatetimer == Resources.taxiTime)
			airCraftObject.state = "e";
		return airCraftObject;
	}

	private static Aircraft cStateOperation(Aircraft airCraftObject, LinkedList<Gate> gateLinkedList) {
		airCraftObject.cstatetimer++;
		if (airCraftObject.cstatetimer == airCraftObject.at.boardingTime) {
			airCraftObject.state = "d";
			gateLinkedList.get(airCraftObject.gateNo).status = true;
		}
		return airCraftObject;
	}

	private static Aircraft bStateOperation(Aircraft airCraftObject, LinkedList<Gate> gateLinkedList, int numberOfGates) {
		boolean b_flag = false;
		// The j variable denotes the number of gates
		int j;
		for (j = 0; j < numberOfGates; j++) {
			if (gateLinkedList.get(j).status == true) {
				airCraftObject.state = "c";
				airCraftObject.gateNo = j;
				gateLinkedList.get(j).status = false;
				b_flag = true;
				break;
			}
		}
		if (b_flag == false)
			airCraftObject.bWaitTime++;

		return airCraftObject;

	}

	private static Aircraft aStateOperation(Aircraft airCraftObject) {
		airCraftObject.astatetimer++;
		if (airCraftObject.astatetimer == Resources.taxiTime) {
			airCraftObject.state = "b";
		}
		return airCraftObject;
	}

	private static Aircraft rDashStateOperation(Aircraft airCraftObject, LinkedList<Runway> runwayLinkedList) {
		airCraftObject.rdstatetimer++;
		// The variable j denotes the number of runways.
		for (int j = 0; j < Runway.getNoOfRunways(); j++) {
			if (runwayLinkedList.get(j).status == false) {
				runwayLinkedList.get(j).runwayWaitTime++;
			}
			if (runwayLinkedList.get(j).runwayWaitTime == airCraftObject.at.runwayTime) {
				runwayLinkedList.get(j).status = true;
				runwayLinkedList.get(j).runwayWaitTime = 0;
			}
		}
		if (airCraftObject.rdstatetimer == airCraftObject.at.runwayTime) {
			airCraftObject.state = "a";
			runwayLinkedList.get(airCraftObject.RunwayNumber).status = true;
		}
		return airCraftObject;
	}

	private static Aircraft sStateOperation(Aircraft airCraftObject, LinkedList<Runway> runwayLinkedList) {
		boolean s_state_flag = false;
		// The variable j denotes the number of runways.
		for (int runwayIterator = 0; runwayIterator < Runway.getNoOfRunways(); runwayIterator++) {
			// This loop will check the priority of the fight for the runway.
			for (int outerIterator = 0; outerIterator < Resources.getAircraftLinkedList().size(); outerIterator++) {
				for (int innerIterator = 0; innerIterator < Resources.getAircraftLinkedList().size()
						&& innerIterator != outerIterator; innerIterator++) {
					if (checkRunwayPriority(outerIterator, innerIterator, runwayIterator, runwayLinkedList)) {
						airCraftObject.state = "r'";
						airCraftObject.RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
						Resources.sCount--;
						airCraftObject.sStateSingleConfigWait += airCraftObject.sWaitTime;
						airCraftObject.sWaitTime = 0;
						runwayLinkedList.get(runwayIterator).status = false;
						s_state_flag = true;
						break;
					}
				}

			}
		}
		if (s_state_flag == false)
			airCraftObject.sWaitTime++;

		return airCraftObject;
	}

	private static Aircraft zStateOperation(Aircraft airCraftObject) {
		if (Resources.sCount < Resources.getFlightBuffer()) {
			airCraftObject.state = "s";
		}
		return airCraftObject;
	}

	private static boolean checkRunwayPriority(int outerIterator, int innerIterator, int runwayIterator,
			LinkedList<Runway> runwayLinkedList) {
		if (Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList()
				.get(innerIterator).sWaitTime
				&& Resources.getAircraftLinkedList().get(outerIterator).sWaitTime >= Resources.getAircraftLinkedList()
						.get(innerIterator).eWaitTime
				&& runwayLinkedList.get(runwayIterator).status == true) {
			return true;
		}
		return false;
	}
}
