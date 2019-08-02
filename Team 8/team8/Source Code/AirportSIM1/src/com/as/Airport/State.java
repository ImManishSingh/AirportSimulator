package com.as.Airport;

public class State {
	String name;
	public int index;
	int vacancy;
	int capacity;
	int waitTime;
	State next;

	
	public State(int capacity, int index)
	{
		this.capacity=capacity;
		this.waitTime=0;
		this.vacancy=capacity;
		this.next=null;
	}
	public State nextState()
	{
		if(next.isAvailable())
			return next;
		else
			return this;
	}

	public void link(State S){
		this.next = S;
	}

	public void occupy()
	{
		vacancy--;
	}
	public void release() 
	{
		vacancy++;
	}

	public boolean isAvailable()
	{
		if(vacancy>0)
			return true;
		else
			return false;
	}

}