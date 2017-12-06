package bionexo.graphchallenge.graph.model;

import java.util.HashSet;

/**
* 
*  Model Graph class represent the Graph structure 
*  
*  Is the main class of this application, all features and function are based on this.
*  
*  Got a Set of Clients and Edges, use Set class to force only one representation of Client and Edge (guaranteed by id property on then)	
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class Graph {
	
	private final HashSet<Client> clients;
	private final HashSet<Edge> edges;
	
	public Graph(HashSet<Client> clients, HashSet<Edge> edges) {
		this.clients = clients;
		this.edges = edges;
	}
	
	public Graph() {
		this.clients = new HashSet<>();
		this.edges = new HashSet<>();
	}
	
	public HashSet<Client> getClients() {
		return clients;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}
}
