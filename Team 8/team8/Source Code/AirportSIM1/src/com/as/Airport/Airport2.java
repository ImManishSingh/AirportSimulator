package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;
import com.as.Driver.*;

public class Airport2 {
	
/*
	int numberOfGates; // This variable is used for number of gates.
	
	int numberOfEmergencyLandings; // This variable is used to denote the emergency number of landings the airport can handle.
											
	static int numberOfRunway = 1; // This variable is used for number of runways in the airport.
	
	static int taxiTime = 15; // This variable is used to denote taxi time required for all aircrafts.
	
	public static double graphArray[][]; // This variable is used to give total wait time for each number of gates.
	
	boolean timeExceedFlag = false;
	boolean freedTimeFlag=true;
	 int [] freedTimeArray;

	// This function will determine number of optimal number of gates.
	public int getNumberOfGates(int numberOfFlights, LinkedList<Aircraft> aircraftLinkedList, boolean graphFlag) {
		System.out.println("Running....");
		numberOfGates = 1;
		
		
		freedTimeArray=new int[numberOfFlights];
		// g denotes number of grounded flights and ng denotes number of non grounded
		// flights.
		int grounded = 1, nonGrounded = numberOfFlights - 1;
		// buffer denotes maximum number of flights in s state.
		int time = 0, flightBuffer = 7, s_count = 0;
		// sums,sume,sumb denotes the wait time of flight at s,e and b states
		// respectively.
		int s_state_wait = 0, e_state_wait = 0, b_state_wait = 0;
		int totalWaitsum[] = new int[numberOfFlights];

		// This loop will check the total wait time for each number of gates from 1 to
		// number of flights.
		for (; numberOfGates <= numberOfFlights; numberOfGates++) {
			time=0;
			LinkedList<Gate> gateLinkedList;
			gateLinkedList = createLinkedList(numberOfGates);
			
			LinkedList<Runway> runwayLinkedList;
			runwayLinkedList = createRunwayLinkedList(numberOfRunway);

			// This loop will check total wait time for each case of grounded and non
			// grounded number of flights for each number of gate.
			
			for (; grounded <= numberOfFlights - 1 && nonGrounded >= 1; grounded++, nonGrounded--) {
				int flightCounter = 0;
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
				// This loop will assign the state s to the aircrafts in the initial case.
				while (flightCounter < numberOfFlights && flightCounter < grounded + flightBuffer) {
					aircraftLinkedList.get(flightCounter).state = "s";

					s_count++;
					flightCounter++;
				}
				// This loop will assign the state z to the aircrafts in the initial case.
				while (flightCounter < numberOfFlights) {
					aircraftLinkedList.get(flightCounter).state = "z";

					flightCounter++;
				}
				while (true) {
					

					// This loop will change the state of flight from z to s.
					for (int flightLinkedListCounter = 0; flightLinkedListCounter < aircraftLinkedList.size(); flightLinkedListCounter++) {
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("z")) {
							if (s_count < flightBuffer) {
								aircraftLinkedList.get(flightLinkedListCounter).state = "s";
							}
						}
						// This loop will change the state of flight from s to r'.
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("s")) {
							boolean s_state_flag = false;
							
							// The variable j denotes the number of runways.
							for (int runwayIterator = 0; runwayIterator < numberOfRunway; runwayIterator++) {
								// This loop will check the priority of the fight for the runway.
								for (int outerIterator = 0; outerIterator < aircraftLinkedList.size(); outerIterator++) {
									for (int innerIterator = 0; innerIterator < aircraftLinkedList.size() && innerIterator != outerIterator; innerIterator++) {
										if (aircraftLinkedList.get(outerIterator).sWaitTime >= aircraftLinkedList.get(innerIterator).sWaitTime
												&& aircraftLinkedList.get(outerIterator).sWaitTime >= aircraftLinkedList.get(innerIterator).eWaitTime
												&& runwayLinkedList.get(runwayIterator).status == true) {
											{
												aircraftLinkedList.get(flightLinkedListCounter).state = "r'";
												aircraftLinkedList.get(flightLinkedListCounter).RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
												s_count--;
												aircraftLinkedList.get(flightLinkedListCounter).sfwt += aircraftLinkedList.get(flightLinkedListCounter).sWaitTime;

												aircraftLinkedList.get(flightLinkedListCounter).sWaitTime = 0;
												runwayLinkedList.get(runwayIterator).status = false;
												s_state_flag = true;
												break;
											}
										}
									}

								}
							}
							if (s_state_flag == false)
								aircraftLinkedList.get(flightLinkedListCounter).sWaitTime++;
						}
						// This loop will change the state of flight from r' to a.
						
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("r'")) {
							aircraftLinkedList.get(flightLinkedListCounter).rdstatetimer++;
							// The variable j denotes the number of runways.
							for (int j = 0; j < numberOfRunway; j++) {
								if (runwayLinkedList.get(j).status == false) {

									runwayLinkedList.get(j).rdt++;

								}
								if (runwayLinkedList.get(j).rdt == aircraftLinkedList.get(flightLinkedListCounter).at.runwayTime) {
									runwayLinkedList.get(j).status = true;
									runwayLinkedList.get(j).rdt = 0;
								}
							}

							if (aircraftLinkedList.get(flightLinkedListCounter).rdstatetimer == aircraftLinkedList.get(flightLinkedListCounter).at.runwayTime) {
								aircraftLinkedList.get(flightLinkedListCounter).state = "a";
								runwayLinkedList.get(aircraftLinkedList.get(flightLinkedListCounter).RunwayNumber).status = true;

							}
						}
						// This loop will change the state of flight from a to b.
						else if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("a")) {
							aircraftLinkedList.get(flightLinkedListCounter).astatetimer++;
							if (aircraftLinkedList.get(flightLinkedListCounter).astatetimer == taxiTime) {
								aircraftLinkedList.get(flightLinkedListCounter).state = "b";
							}
						}
						// This loop will change the state of flight from b to c.
						else if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("b")) {
							boolean b_flag = false;
							// The j variable denotes the number of gates
							int j;
							for (j = 0; j < numberOfGates; j++) {
								if (gateLinkedList.get(j).status == true) {
									aircraftLinkedList.get(flightLinkedListCounter).state = "c";
									aircraftLinkedList.get(flightLinkedListCounter).gateNo = j;
									gateLinkedList.get(j).status = false;
									b_flag = true;
									break;
								}
							}
							if (b_flag == false)
								aircraftLinkedList.get(flightLinkedListCounter).bWaitTime++;
						}
						// This loop will change the state of flight from c to d.
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("c")) {
							aircraftLinkedList.get(flightLinkedListCounter).cstatetimer++;
							if (aircraftLinkedList.get(flightLinkedListCounter).cstatetimer == aircraftLinkedList.get(flightLinkedListCounter).at.boardingTime) {
								aircraftLinkedList.get(flightLinkedListCounter).state = "d";
								gateLinkedList.get(aircraftLinkedList.get(flightLinkedListCounter).gateNo).status = true;
							}
						}
						// This loop will change the state of flight from d to e.
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("d")) {
							aircraftLinkedList.get(flightLinkedListCounter).dstatetimer++;
							if (aircraftLinkedList.get(flightLinkedListCounter).dstatetimer == taxiTime)
								aircraftLinkedList.get(flightLinkedListCounter).state = "e";
						}
						// This loop will change the state of flight from e to r.
						else if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("e")) {
							boolean e_flag = false;
							
							// The j variable denotes the number of runways.
							for (int runwayIterator = 0; runwayIterator < numberOfRunway; runwayIterator++) {
								// This loop will check the priority of the fight for the runway.
								for (int outerIterator = 0; outerIterator < aircraftLinkedList.size(); outerIterator++) {
									for (int innerItaretor = 0; innerItaretor < aircraftLinkedList.size() && innerItaretor != outerIterator; innerItaretor++) {
										if (aircraftLinkedList.get(outerIterator).eWaitTime >= aircraftLinkedList.get(innerItaretor).sWaitTime
												&& aircraftLinkedList.get(outerIterator).eWaitTime >= aircraftLinkedList.get(innerItaretor).eWaitTime) {

											if (runwayLinkedList.get(runwayIterator).status == true) {

												aircraftLinkedList.get(flightLinkedListCounter).state = "r";
												aircraftLinkedList.get(flightLinkedListCounter).RunwayNumber = runwayLinkedList.get(runwayIterator).runwayNumber;
												runwayLinkedList.get(runwayIterator).status = false;
												e_flag = true;
												break;
											}
										}
									}
								}
							}
							if (e_flag == false)
								aircraftLinkedList.get(flightLinkedListCounter).eWaitTime++;
						}
						// This loop will change the state of flight from r to x.
						if (aircraftLinkedList.get(flightLinkedListCounter).state.equals("r")) {

							aircraftLinkedList.get(flightLinkedListCounter).rstatetimer++;

							if (aircraftLinkedList.get(flightLinkedListCounter).rstatetimer == aircraftLinkedList.get(flightLinkedListCounter).at.runwayTime) {
								aircraftLinkedList.get(flightLinkedListCounter).state = "x";
								runwayLinkedList.get(aircraftLinkedList.get(flightLinkedListCounter).RunwayNumber).status = true;
								aircraftLinkedList.get(flightLinkedListCounter).efwt += aircraftLinkedList.get(flightLinkedListCounter).eWaitTime;
								aircraftLinkedList.get(flightLinkedListCounter).eWaitTime = 0;

								aircraftLinkedList.get(flightLinkedListCounter).cstatetimer = 0;
								aircraftLinkedList.get(flightLinkedListCounter).astatetimer = 0;
								aircraftLinkedList.get(flightLinkedListCounter).dstatetimer = 0;
								aircraftLinkedList.get(flightLinkedListCounter).rstatetimer = 0;
								aircraftLinkedList.get(flightLinkedListCounter).rdstatetimer = 0;

							}
						}

					}
					int x_state_counter = 0;
					for (int i = 0; i < aircraftLinkedList.size(); i++) {
						if (aircraftLinkedList.get(i).state.equals("x")) {
							x_state_counter++;

						}
					}
					// This denotes the condition when all fights moves to state x.
					if (x_state_counter == numberOfFlights) {

						break;

					}
					time++;

				}

				
				// This loop will give sums,sumb and sume for each gate.
				for (int i = 0; i < aircraftLinkedList.size(); i++) {
					s_state_wait = aircraftLinkedList.get(i).sfwt + s_state_wait;
					b_state_wait = aircraftLinkedList.get(i).bWaitTime + b_state_wait;
					e_state_wait = aircraftLinkedList.get(i).efwt + e_state_wait;
				}

				for (int i = 0; i < aircraftLinkedList.size(); i++) {
					aircraftLinkedList.get(i).sfwt = 0;
					aircraftLinkedList.get(i).bWaitTime = 0;
					aircraftLinkedList.get(i).efwt = 0;

				}
				
				
			}
			Aircraft.freedtime = time;
			
				for(int i=0;i<numberOfFlights;i++)
				{
					freedTimeArray[i]=Aircraft.freedtime;
				}
			
			

			totalWaitsum[numberOfGates - 1] = s_state_wait + b_state_wait + e_state_wait;
			s_state_wait = 0;
			b_state_wait = 0;
			e_state_wait = 0;
			// This loop is used to free the linked list of gates.
			for (int i = 0; i < gateLinkedList.size(); i++) {
				gateLinkedList.remove(i);
			}
			// This loop is used to free the linked list of runways.
			for (int i = 0; i < runwayLinkedList.size(); i++) {
				runwayLinkedList.remove(i);
			}

			grounded = 1;
			nonGrounded = numberOfFlights - 1;

			time = 0;
		}

		for (int i = 0; i < numberOfFlights; i++) {
		}

		int minimumWaitTime = totalWaitsum[0];
		
		// This loop will find the number of gates for which the total wait time is
		// minimum.
		for (int i = 0; i < numberOfFlights; i++) {
			if (totalWaitsum[i] < minimumWaitTime && totalWaitsum[i] != 0) {
				numberOfGates = i + 1;
				minimumWaitTime = totalWaitsum[i];
			}
			
		}

		
		// This loop is used to create array of number of gates and their respective
		// total wait time.
		if (graphFlag) {
			graphArray = new double[2][numberOfFlights];
			for (int i = 0; i < numberOfFlights; i++) {
				graphArray[0][i] = i + 1;
				graphArray[1][i] = totalWaitsum[i] / ((double)numberOfFlights * (numberOfFlights - 1));

			}

		}
		return numberOfGates;

	}
	
	
	// This function initializes runwayLinkedList 
	private LinkedList<Runway> createRunwayLinkedList(int numberOfRunways) {

		LinkedList<Runway> runwayLinkedList = new LinkedList<Runway>();

		for (int runwayCounter = 0; runwayCounter < numberOfRunways; runwayCounter++) {
			Runway runwayObject = new Runway();
			runwayObject.runwayNumber = runwayCounter;
			runwayLinkedList.add(runwayObject);
		}
		return runwayLinkedList;
	}
	
	
	
// This function initializes gateLinkedList 
	private LinkedList<Gate> createLinkedList(int numberOfGate) {
		int gateCounter = 0;
		LinkedList<Gate> gateLinkedList = new LinkedList<Gate>();
		while (gateCounter < numberOfGate) {
			Gate gateObject = new Gate(gateCounter+1,true);
			gateObject.GateNumber = gateCounter + 1;
			gateLinkedList.add(gateObject);

			gateCounter++;
		}

		return gateLinkedList;
	}

	public int getNumberOfEmergencyLandings(int NumberOfFlights, int OptimalNumberOfGates, LinkedList<Aircraft> aircraftLinkedList,int PT) {
		int LastNumberOfFlights = NumberOfFlights;
		int NewNumberOfGates;
		
		boolean graphFlag = false;
		
		int time;
		
		while (true) {

			NumberOfFlights = NumberOfFlights + 1;
			Driver.Add();
			NewNumberOfGates = getNumberOfGates(NumberOfFlights, aircraftLinkedList, graphFlag);
			time=freedTimeArray[OptimalNumberOfGates]/(NumberOfFlights-1);
			System.out.println("Time is "+time);
			
			if (NewNumberOfGates != OptimalNumberOfGates  ||  time>PT) {

				
				System.out.println("For flights= " + NumberOfFlights);
				break;
			}
		}

		numberOfEmergencyLandings = NumberOfFlights - LastNumberOfFlights - 1;

		return numberOfEmergencyLandings;
	}
*/
}
