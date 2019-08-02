package com.as.Aircraft;

import com.as.Airport.*;
import com.as.Aircraft.*;

import java.util.*;

public class Aircraft {
	public int AircraftNumber; //This variable denotes the number assigned ton each aircraft.
	public int aircraftType;
	public int bWaitTime,sWaitTime,eWaitTime, efwt=0 ,sfwt=0,gateNo, waitTime, minWaitTime;
	public int astatetimer=0,cstatetimer=0,dstatetimer=0,rstatetimer=0,rdstatetimer=0;
	public String state;
	int transitionTime;

	private State currentPosition;
	public int RunwayNumber;

	public Aircraft(int Type, State initPosition) {
		this.aircraftType = Type;
		this.currentPosition = initPosition;
	}

	public void allotPosition(State newposition) {
		this.currentPosition.release();
		this.currentPosition = newposition;
		newposition.occupy();
	}

	public void updatePosition(int time) {
		State nextPosition = currentPosition.nextState();
		if (nextPosition != null && time-transitionTime>= minWaitTime){
			allotPosition(nextPosition);
			this.transitionTime = time;
			minWaitTime = GateWaitTime.WaitTimeMatrix[this.aircraftType][this.currentPosition.index] ; //WaitTimeMatrix(Type, currentPosition);
		}
	}

}
