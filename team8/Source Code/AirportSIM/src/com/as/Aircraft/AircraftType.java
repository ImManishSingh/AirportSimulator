package com.as.Aircraft;



public class AircraftType {

	
	
	private int runwayTime; //Runway time of each airplane
	private int boardingTime;       //Time spent by flight on the gate
	
	public AircraftType(int runwayTime,int boardingTime)
	{
		this.runwayTime=runwayTime;
		this.boardingTime=boardingTime;
	}

	public int getRunwayTime() {
		return runwayTime;
	}

	public int getBoardingTime() {
		return boardingTime;
	}

	public AircraftType() {
		runwayTime=0;
		boardingTime=0;		
	}
	
}
