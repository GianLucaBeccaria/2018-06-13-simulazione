package it.polito.tdp.flightdelays.model;

import java.util.HashMap;
import java.util.Map;

public class AirlineIdMap {

	private Map<String,Airline> map ;

	public AirlineIdMap() {
		
		map = new HashMap<>();
	}
	
	public Airline get(String airlineId) {
		return map.get(airlineId);
	}
	public Airline get(Airline airline) {
		Airline old = map.get(airline.getId());
		if(old==null) {
			map.put(airline.getId(), airline);
			return airline;
		}
		return old;
	}
	
	public void put(Airline airline, 	String airlineId) {
		map.put(airlineId, airline);
	}
	
}
