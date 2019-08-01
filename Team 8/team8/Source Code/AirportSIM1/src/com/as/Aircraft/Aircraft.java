package com.as.Aircraft;

import com.as.Airport.*;

public class Aircraft {
	public int AircraftNumber; //This variable denotes the number assigned ton each aircraft.
	public AircraftType at=new AircraftType();
	public int bWaitTime,sWaitTime,eWaitTime, efwt=0 ,sfwt=0,gateNo;
	public int astatetimer=0,cstatetimer=0,dstatetimer=0,rstatetimer=0,rdstatetimer=0;
	//public String state;

	private State currentPosition;
	public int RunwayNumber;
	public static int freedtime=0;

	public Aircraft(AircraftType State, int initPosition) {
		return at;
	}

	public void allotPosition(State newposition) {
		this.currentPosition.release();
		this.currentPosition = newposition;
		newposition.occupy();
	}
}
