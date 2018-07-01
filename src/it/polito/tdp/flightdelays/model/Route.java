package it.polito.tdp.flightdelays.model;

public class Route implements Comparable<Route>{

	private Airport origine;
	private Airport destinazione;
	private double sommaRitardi;
	private int numeroOccorrenze;
	public Route(Airport origine, Airport destinazione) {
		super();
		this.origine = origine;
		this.destinazione = destinazione;
//		this.sommaRitardi = 0;
//		this.numeroOccorrenze = 1;
	}
	
	public void somma(double ritardo) {
	 this.sommaRitardi+=ritardo;
	}
	
	public void unoInPiu() {
		this.numeroOccorrenze++;
	}
	
	public Airport getOrigine() {
		return origine;
	}
	public void setOrigine(Airport origine) {
		this.origine = origine;
	}
	public Airport getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(Airport destinazione) {
		this.destinazione = destinazione;
	}
	public double getSommaRitardi() {
		return sommaRitardi;
	}
	public void setSommaRitardi(double sommaRitardi) {
		this.sommaRitardi = sommaRitardi;
	}
	public int getNumeroOccorrenze() {
		return numeroOccorrenze;
	}
	public void setNumeroOccorrenze(int numeroOccorrenze) {
		this.numeroOccorrenze = numeroOccorrenze;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinazione == null) ? 0 : destinazione.hashCode());
		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
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
		Route other = (Route) obj;
		if (destinazione == null) {
			if (other.destinazione != null)
				return false;
		} else if (!destinazione.equals(other.destinazione))
			return false;
		if (origine == null) {
			if (other.origine != null)
				return false;
		} else if (!origine.equals(other.origine))
			return false;
		return true;
	}

	@Override
	public int compareTo(Route o) {
		// TODO Auto-generated method stub
		double d= o.getSommaRitardi()-this.getSommaRitardi();
		if(d>0) {
			return 1;
		}
		else if(d<0) {
			return -1;
		}
		else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Route [origine=" + origine + ", destinazione=" + destinazione + ", sommaRitardi=" + sommaRitardi + "]";
	}

//	@Override
//	public int compareTo(Route o) {
//		// TODO Auto-generated method stub
//		return ;
//	}

//	@Override
////	public int compareTo(Route o) {
////		// TODO Auto-generated method stub
////		//return ;
////	}
	
	
	
}
