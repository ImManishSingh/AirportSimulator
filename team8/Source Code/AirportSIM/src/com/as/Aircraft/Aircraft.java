package com.as.Aircraft;

import com.as.Aircraft.AircraftType;

public class Aircraft {
	
	//The algorithm used assumes different spots on Airport as different states. eg. Runway for landing is "r'", 
	//wait queue is 'a' and 'b'.
	//s is entry point in system
	//r' is landing end of runway
	//a and b are two ends of wait queue
	//'c' is gate 
	//d and e are two ends of another wait queue
	//r is take-off end of runway
	//x is exit point
	
	public int AircraftNumber; //This variable denotes the number assigned ton each aircraft.
	public AircraftType at=new AircraftType();
	public int bWaitTime,sWaitTime,eWaitTime,efwt,sfwt,gateNo; //efwt and sfwt is e-state and s-state wait time
	public int astatetimer,cstatetimer,dstatetimer,rstatetimer,rdstatetimer;
	public String state;
	public int RunwayNumber;
	public static int freedtime=0;
	
	public Aircraft()
	{
		astatetimer=0;
		cstatetimer=0;
		dstatetimer=0;
		rstatetimer=0;
		rdstatetimer=0;
		efwt=0;
		sfwt=0;
	}

}
