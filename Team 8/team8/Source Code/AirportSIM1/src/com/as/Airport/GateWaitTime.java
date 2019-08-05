package com.as.Airport;

import java.util.LinkedList;

import com.as.Aircraft.Aircraft;
import com.as.Driver.AirportSimulatorGUI;


public class GateWaitTime {

	static int totalTypes = 3;
	static int totalStates = 8;

	public static int[][] WaitTimeMatrix = new int[totalTypes][totalStates];

	public static void designMatrix(){
		AirportSimulatorGUI obj = new AirportSimulatorGUI();
		synchronized (obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			//state 0 = air
			// state 1 = buffer
			// state 2 = runway
			// state 3 = queue
			// state 4 = gates
			// state 5 = queue
			// state 6 = runway
			// state 7 = air

			WaitTimeMatrix[0][2] = obj.getRT1();
			WaitTimeMatrix[1][2] = obj.getRT2();
			WaitTimeMatrix[2][2] = obj.getRT3();

			WaitTimeMatrix[0][4] = obj.getBT1();
			WaitTimeMatrix[1][4] = obj.getBT2();
			WaitTimeMatrix[2][4] = obj.getBT3();

			for (int i = 0; i < totalTypes; i++) {
				WaitTimeMatrix[i][0] = 0;
				WaitTimeMatrix[i][1] = 0;
				WaitTimeMatrix[i][7] = 0;
				WaitTimeMatrix[i][3] = 15;
				WaitTimeMatrix[i][5] = 15;
				WaitTimeMatrix[i][6] = WaitTimeMatrix[i][2];
			}
		}
	}

}
