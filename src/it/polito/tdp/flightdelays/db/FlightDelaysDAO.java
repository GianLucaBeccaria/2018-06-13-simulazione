package it.polito.tdp.flightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.Airport;
import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.Flight;

public class FlightDelaysDAO {

	public List<Airline> loadAllAirlines(AirlineIdMap aidmap) {
		String sql = "SELECT id, airline from airlines order by id ";
		List<Airline> result = new ArrayList<Airline>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airline airline =(new Airline(rs.getString("ID"), rs.getString("airline")));
				result.add(aidmap.get(airline));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Airport> loadAllAirports(Airline airline, AirportIdMap arIdMap ) {
		String sql ="select distinct a.ID as id , a.AIRPORT as airport, a.LATITUDE as latitude, a.LONGITUDE as longitude from airports a, flights f where f.AIRLINE = ? and  (DESTINATION_AIRPORT_ID = a.ID)||(ORIGIN_AIRPORT_ID = a.ID) ORDER BY a.ID";
				
		List<Airport> result = new ArrayList<Airport>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, airline.getId());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airport airport = new Airport(rs.getString("id"), rs.getString("airport"), rs.getDouble("latitude"), rs.getDouble("longitude"));
				result.add(arIdMap.get(airport));
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Flight> loadAllFlights(AirlineIdMap airlineIdMap, AirportIdMap airportIdMap, Airline airline ) {
		String sql = "select ID as a ,AIRLINE as b ,ORIGIN_AIRPORT_ID as c ,DESTINATION_AIRPORT_ID as d ,DEPARTURE_DELAY as e ,ARRIVAL_DELAY as f " + 
				"from flights where airline = ? and ORIGIN_AIRPORT_ID in(select id from airports) and " + 
				"DESTINATION_AIRPORT_ID in(select id from airports)";
		List<Flight> result = new LinkedList<Flight>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, airline.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airline a = airlineIdMap.get(rs.getString("b"));
				Airport o = airportIdMap.get(rs.getString("c"));
				Airport d = airportIdMap.get(rs.getString("d"));
				Flight flight = new Flight(rs.getInt("a"), a, o, d, rs.getDouble("e"), rs.getDouble("f"));
				result.add(flight);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
