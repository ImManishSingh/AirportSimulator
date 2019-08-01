package com.as.Aircraft;



public class AircraftType {
	public int runwayTime; //This variable denotes runway time of each aircraft.
	public int boardingTime; //This variable denotes runway time of each aircraft.
	
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
