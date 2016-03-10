package com.ynov.transpotter.models;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Coordinates departure = new Coordinates();
    private Coordinates arrival = new Coordinates();
    private List<ArrayList<Coordinates>> path = new ArrayList<ArrayList<Coordinates>>();

    public Trip() {
	super();
    }

    public Trip(Coordinates departure, Coordinates arrival) {
	super();
	this.departure = departure;
	this.arrival = arrival;
    }

    public Coordinates getDeparture() {
	return departure;
    }

    public void setDeparture(Coordinates departure) {
	this.departure = departure;
    }

    public Coordinates getArrival() {
	return arrival;
    }

    public void setArrival(Coordinates arrival) {
	this.arrival = arrival;
    }

    public List<ArrayList<Coordinates>> getPath() {
	return path;
    }

    public void setPath(List<ArrayList<Coordinates>> path) {
	this.path = path;
    }
}
