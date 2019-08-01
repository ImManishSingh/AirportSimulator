package com.as.Aircraft;



public class AircraftType {
	public int runwayTime; 
	public int boardingTime; 
	
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
