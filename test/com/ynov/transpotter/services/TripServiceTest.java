package com.ynov.transpotter.services;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ynov.transpotter.models.Trip;
import com.ynov.transpotter.services.TripService;

public class TripServiceTest {
    private static final TripService tripService = new TripService();
    private static final Trip trip = new Trip();
    private static int idTrip = 0;

    @BeforeClass
    public static void before() {
	idTrip = tripService.add(trip);
    }
    
    @Test
    public void testTripService() {
	System.out.println(tripService.size());
	assertTrue(tripService.size() == 1);
    }

    @Test
    public void testTripServiceListOfTrip() {
	TripService anotherTripService = new TripService(new ArrayList<Trip>());
	assertTrue(anotherTripService.size() == 0);
    }

    @Test
    public void testGet() {
	assertEquals(tripService.get(idTrip), trip);
    }

    @Test
    public void testRemove() {
	Trip t = new Trip();
	int initialSize = tripService.size();
	
	int id = tripService.add(t);
	assertEquals(tripService.get(id), t);
	
	int addSize = tripService.size();
	tripService.remove(id);
	
	int rmSize = tripService.size();
	assertTrue(rmSize != addSize);
	assertTrue(rmSize == initialSize);
    }

    @Test
    public void testAdd() {
	Trip t = new Trip();
	int id = tripService.add(t);
	assertEquals(tripService.get(id), t);
	tripService.remove(id);
    }

    @Test
    public void testSize() {
	List<Trip> list = new ArrayList<Trip>();
	list.add(new Trip());
	list.add(new Trip());
	TripService anotherTripService = new TripService(list);
	
	assertTrue(anotherTripService.size() == 2);
    }

}
