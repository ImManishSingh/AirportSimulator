package com.as.Aircraft;

import com.as.Airport.*;
import com.as.Aircraft.*;

public class Aircraft {
	public int AircraftNumber; //This variable denotes the number assigned ton each aircraft.
	public AircraftType Type;
	public int bWaitTime,sWaitTime,eWaitTime, efwt=0 ,sfwt=0,gateNo, WaitTime;
	public int astatetimer=0,cstatetimer=0,dstatetimer=0,rstatetimer=0,rdstatetimer=0;
	public String state;
	int transitionTime;

	private State currentPosition;
	public int RunwayNumber;
	public static int freedtime=0;

	public Aircraft(AircraftType Type, State initPosition) {
		this.Type = Type;
		this.currentPosition = initPosition;
	}

	private void allotPosition(State newposition) {
		this.currentPosition.release();
		this.currentPosition = newposition;
		newposition.occupy();
	}

	public void updatePosition(int time) {
		State nextPosition = currentPosition.nextState();
		if (nextPosition != null && time-transitionTime>= WaitTime){
			allotPosition(nextPosition);
			this.transitionTime = time;
			WaitTime = 0 ; //WaitTimeMatrix(Type, currentPosition);
		}
	}
}
