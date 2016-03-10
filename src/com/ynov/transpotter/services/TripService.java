package com.ynov.transpotter.services;

import java.util.ArrayList;
import java.util.List;

import com.ynov.transpotter.models.Trip;

public class TripService {
    private List<Trip> trips = new ArrayList<Trip>();

    public TripService() {
	super();
    }

    public TripService(List<Trip> trips) {
	super();
	this.trips = trips;
    }
    
    public Trip get(int index) {
	return this.trips.get(index);
    }
    
    public void remove(int index) {
	this.trips.remove(index);
    }
    
    public int add(Trip trip) {
	this.trips.add(trip);
	return this.trips.size() - 1;
    }
    
    public int size() {
	return this.trips.size();
    }
}
