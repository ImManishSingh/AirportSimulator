package com.as.Airport;

public class State {
	String name;
	int vacancy;
	int capacity;
	int waitTime;
	State next;

	public State(int capacity, int waitTime) {
		this.capacity = capacity;
		this.waitTime = waitTime;
		this.vacancy = capacity;
		this.next = null;
	}

	public State nextState(int time) {
		if (time == waitTime)
			return next;
		else
			return null;
	}

	public void occupy() {
		vacancy--;
	}

	public void release() {
		vacancy++;
	}

	public boolean isAvailable() {
		if (vacancy > 0)
			return true;
		else
			return false;
	}

}
