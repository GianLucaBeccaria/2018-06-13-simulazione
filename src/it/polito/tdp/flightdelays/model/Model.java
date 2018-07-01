package it.polito.tdp.flightdelays.model;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.sound.sampled.LineEvent;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {

	private FlightDelaysDAO dao;
	private List<Airline> linee;
	private AirlineIdMap aidmap;
	private AirportIdMap aridmap;
	private SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private HashSet<Airport> aereoporti;
	private List<Flight> voli;
	private List<Route> routes;
	private List<Route> finalroutes;
	
	
	public Model() {
		aidmap = new AirlineIdMap();
		aridmap = new AirportIdMap();
		dao = new FlightDelaysDAO();
		linee = new ArrayList<>(dao.loadAllAirlines(aidmap));
	}


	public List<Airline> getLinee() {
		return linee;
	}


	public List<Route> creaGrafo(Airline airline) {
		// TODO Auto-generated method stub
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		aereoporti = new HashSet(dao.loadAllAirports(airline, aridmap));
		
		Graphs.addAllVertices(this.grafo, aereoporti);
		//System.out.println(this.grafo.vertexSet());
		
		voli = new ArrayList<>(dao.loadAllFlights(aidmap, aridmap, airline));
		System.out.println(voli.size());
		routes = new ArrayList<>();
		for(Flight f: voli) {
			
			Airport o = f.getOriginAirport();
			Airport d = f.getDestinationAirport();
			Double ritardo = f.getArrivalDelay();
			
		    Route r = new Route(o, d);
		    
		    if(routes.contains(r)) {
		    	for(Route rr: routes) {
		    		if(rr.equals(r)) {
		    			rr.setSommaRitardi(ritardo);
		    			rr.unoInPiu();
		    		}
		    	}
		    }
		    else {
		    	r.setNumeroOccorrenze(1);
		    	r.setSommaRitardi(ritardo);
		    	routes.add(r);
		    }
		}
		
		System.out.println(routes.size());
		
		//archi
		
		
		finalroutes = new ArrayList<>();
			for(Route r: routes) {
				
				Airport source = r.getOrigine();
				Airport destination =r.getDestinazione();
				if(!source.equals(destination)) {
				double weight = LatLngTool.distance(new LatLng(source.getLatitude(),source.getLongitude()),
						new LatLng(destination.getLatitude(),destination.getLongitude()), LengthUnit.KILOMETER);
				Graphs.addEdge(grafo, source, destination, (r.getSommaRitardi()/r.getNumeroOccorrenze())/weight);
				Route fin= new Route(r.getOrigine(), r.getDestinazione());
				fin.setSommaRitardi((r.getSommaRitardi()/r.getNumeroOccorrenze())/weight);
				finalroutes.add(fin);
				}
			
		}
			
//			System.out.println(grafo.vertexSet().size());
//			System.out.println(grafo.edgeSet().size());
			List<Route> risul = new ArrayList<>();
			Collections.sort(finalroutes);
			for(int i=0;i<10;i++) {
				risul.add(finalroutes.get(i));
			}
			return risul;
		
	}
	
	
	
	
	
}
