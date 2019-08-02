package com.as.Aircraft;

import com.as.Aircraft.AircraftType;

public class Aircraft {
	
	//The algorithm used assumes different spots on Airport as different states. eg. Runway for landing is "r'", 
	//z is buffer of planes in air
	//s is entry point in system
	//wait queue is 'a' and 'b'.
	//r' is landing end of runway
	//a and b are two ends of wait queue
	//'c' is gate 
	//d and e(queue before take-off) are two ends of another wait queue
	//r is take-off end of runway
	//x is exit point
	
	public int AircraftNumber; //This variable denotes the number assigned to each aircraft.
	public AircraftType at=new AircraftType();
	public int bWaitTime,sWaitTime,eWaitTime,efwt,sfwt,gateNo; //efwt and sfwt is e-state and s-state wait time for one gate upto 'n' gates. 
	public int astatetimer,cstatetimer,dstatetimer,rstatetimer,rdstatetimer;   //This timer which is local to each state
	public String state;       //Defines the current state the plane is in.
	public int RunwayNumber;    //This is defined if there are multiple runways. (For now, we have only one).
	public static int freedtime;  //Holds amount of time required for each individual gate  
	
	public Aircraft()
	{
		astatetimer=0;
		cstatetimer=0;
		dstatetimer=0;
		rstatetimer=0;
		rdstatetimer=0;
		efwt=0;
		sfwt=0;
		freedtime=0;
	}

}
