package com.as.Airport;

import java.util.LinkedList;

public class Gate {

	int GateNumber; // This variable denotes number assigned to each gate. 
	boolean status; // This variable denotes whether the gate is occupied or not.
	
	public Gate(int GateNumber,boolean status)
	{
		this.GateNumber=GateNumber;
		this.status=status;
	}
	
	// This function initializes gateLinkedList 
		public static LinkedList<Gate> createLinkedList(int numberOfGates) {
			int gateCounter = 0;
			LinkedList<Gate> gateLinkedList = new LinkedList<Gate>();
			while (gateCounter < numberOfGates) {
				Gate gateObject = new Gate(gateCounter+1,true);
				
				gateLinkedList.add(gateObject);

				gateCounter++;
			}

			return gateLinkedList;
		}
}
