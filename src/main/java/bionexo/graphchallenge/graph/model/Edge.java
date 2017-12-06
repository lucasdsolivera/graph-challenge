package bionexo.graphchallenge.graph.model;

import java.math.BigDecimal;

/**
* 
*  Model Edge class represent a "connection" between two Clients
*  
*  it have a source and destination Client, and a cost to travel between then
*  
*  the id property is a union of source Client id and destination Client id separated by "_".
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class Edge {

	private String id;
	private Client source;
	private Client destination;
	private BigDecimal cost;
	
	public Edge(String id, Client source, Client destination, BigDecimal cost) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.setCost(cost);
	}

	public Edge() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Client getSource() {
		return source;
	}

	public void setSource(Client source) {
		this.source = source;
	}

	public Client getDestination() {
		return destination;
	}

	public void setDestination(Client destination) {
		this.destination = destination;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Edge [id=" + id + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Edge other = (Edge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
