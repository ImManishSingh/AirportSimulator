package com.as.Airport;

import java.util.LinkedList;
//This class creates linked list of gates. 
public class Gate {

	private int GateNumber;
	boolean status; // This variable denotes whether the gate is occupied or not.
	public Gate(int GateNumber, boolean status) {
		this.GateNumber = GateNumber;
		this.status = status;
	}

	// This function initializes gateLinkedList
	public static LinkedList<Gate> createLinkedList(int numberOfGates) {
		int gateCounter = 0;
		LinkedList<Gate> gateLinkedList = new LinkedList<Gate>();
		while (gateCounter < numberOfGates) {
			Gate gateObject = new Gate(gateCounter + 1, true);
			gateLinkedList.add(gateObject);
			gateCounter++;
		}
		return gateLinkedList;
	}
}
