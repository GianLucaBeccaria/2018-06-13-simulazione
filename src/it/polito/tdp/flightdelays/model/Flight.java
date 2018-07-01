package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int id;
	private Airline airline;
//	private int flightNumber;
	private Airport originAirport;
	private Airport destinationAirport;
//	private LocalDateTime scheduledDepartureDate;
//	private LocalDateTime arrivalDate;
	private double departureDelay;
	private double arrivalDelay;
//	private int airTime;
//	private int distance;
	
	public Flight(int id, Airline airline, Airport originAirport, Airport destinationAirport,double d, double e)
			{
		this.id = id;
		this.airline = airline;
//		this.flightNumber = flightNumber;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
//		this.scheduledDepartureDate = scheduledDepartureDate;
//		this.arrivalDate = arrivalDate;
		this.departureDelay = d;
		this.arrivalDelay = e;
//		this.airTime = airTime;
//		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public double getDepartureDelay() {
		return departureDelay;
	}

	public void setDepartureDelay(int departureDelay) {
		this.departureDelay = departureDelay;
	}

	public double getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(int arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", airline=" + airline + ", originAirport=" + originAirport
				+ ", destinationAirport=" + destinationAirport + ", departureDelay=" + departureDelay
				+ ", arrivalDelay=" + arrivalDelay + "]";
	}

	
}
