package com.as.Aircraft;



public class AircraftType {

	
	
	public int runwayTime; //Runway time of each airplane
	public int boardingTime;       //Time spent by flight on the gate

	

	
	
	
	public AircraftType(int runwayTime,int boardingTime)
	{
		this.runwayTime=runwayTime;
		this.boardingTime=boardingTime;
	}

	public AircraftType() {
		runwayTime=0;
		boardingTime=0;		
	}
	
}
