package com.as.Aircraft;

import com.as.Aircraft.AircraftType;

public class Aircraft {
	public int AircraftNumber; //This variable denotes the number assigned ton each aircraft.
	public AircraftType at=new AircraftType();
	public int bWaitTime,sWaitTime,eWaitTime,efwt=0,sfwt=0,gateNo;
	public int astatetimer=0,cstatetimer=0,dstatetimer=0,rstatetimer=0,rdstatetimer=0;
	public String state;
	public int RunwayNumber;
	public static int freedtime=0;

}
