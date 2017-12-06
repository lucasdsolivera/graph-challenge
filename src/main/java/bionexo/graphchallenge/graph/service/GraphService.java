package bionexo.graphchallenge.graph.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bionexo.graphchallenge.graph.model.Client;
import bionexo.graphchallenge.graph.model.Edge;
import bionexo.graphchallenge.graph.model.Graph;

/**
* 
*  GraphService class implements all the searches and interactions on the Graph 
*  
*  Was used the Depth-First Search algorithm to cross the Graph and build routes.
*  This algorithm uses recursion and to stop the recursion was used the @loopValidation method 
*  	
*  Along this class we have some comments to helps understand the logic used
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class GraphService {
	
	private Graph graph;
	
	private Map<Integer, List<Edge>> routes = new HashMap<>();

	private int index;
	
	public GraphService(Graph graph) {
		this.graph = graph;
	}

	public HashSet<Client> getAdjacentsBackward(Client destination) {
		
		HashSet<Client> arriving = new HashSet<>();
        for (Edge edge : graph.getEdges()) {

        	//get only the edges going to @param destination
        	if (edge.getDestination().equals(destination)) {
        		arriving.add(edge.getSource());
            }
        }
        return arriving;
	}
	
	private List<Edge> getAdjacentsForward(Client source) {
		List<Edge> adjacents = new ArrayList<>();
        
		//get only the edges from @param source
		for (Edge edge : graph.getEdges()) {
            if (edge.getSource().equals(source)) {
            	adjacents.add(edge);
            }
		}
		
		return adjacents;
	}
	//this method is a interface to the recursion function
	public Map<Integer, List<Edge>> getRoutes(Client source, Client destination, Integer edgeLimit, BigDecimal valueLimit) {
		LinkedList<Edge> passed = new LinkedList<>();
		this.routes = new HashMap<>();
		this.index = 0;
		
		//call the recursion function
		return getRoutes(passed, source, destination, edgeLimit, valueLimit);
	}
	
	public Map<Integer, List<Edge>> getRoutes(LinkedList<Edge> passed, Client source, Client destination, Integer edgeLimit, BigDecimal valueLimit) {
        List<Edge> edges = getAdjacentsForward(passed.size() == 0 ? source : passed.getLast().getDestination());
        
        for (Edge edge : edges) {
        	//validating the end of recursion by some limits
            if (loopValidation(passed, edgeLimit, valueLimit)) {
                continue;
            }
            if (edge.getDestination().equals(destination)) {
                passed.add(edge);
                //creating a Route because we are in the destination
                createRoute(passed, edgeLimit, valueLimit);
                
                //remove to validate in the next for
                passed.removeLast();
            }
        }
        //we need to use recursion after create the route in DFS
        for (Edge edge : edges) {
        	//validating the end of recursion by some limits
            if (loopValidation(passed, edgeLimit, valueLimit)) {
                continue;
            }
            passed.addLast(edge);
            //call recursion to go inner the Graph
            getRoutes(passed, source, destination, edgeLimit, valueLimit);
            passed.removeLast();
        }
        
        return routes;
    }
	
	public List<Edge> getExpecificRoute(Map<Integer, List<Edge>> routes, List<Client> criteriaList) {
		int index = 0;
		for (List<Edge> route : routes.values()) {
			// different sizes, is not our route
			if (route.size() != criteriaList.size() -1) {
				continue;
			}
			
			for (Edge edge : route) {
				
				// different edges, is not our route
				if (!edge.getSource().equals(criteriaList.get(index))) {
					index = 0;
					break;
				}
				//here we can sure that exactly tha same route that passed in @param criteriaList
				if (index == route.size() -1 && edge.getDestination().equals(criteriaList.get(index+1))) {
					return route;
				}
				index++;
			}
		}
		return null;
	}

	public Map<Integer, List<Edge>> filterNumberOfEdges(Map<Integer, List<Edge>> routes, Integer numberEdges) {
		Map<Integer, List<Edge>> result = new HashMap<>();
		int index = 0;
		for (List<Edge> route : routes.values()) {
			//simple filter by number of Edges
			if (route.size() == numberEdges) {
				result.put(index,route);
				index++;
			}
		}
		return result;
	}
	
	private void createRoute(LinkedList<Edge> passed, Integer edgeLimit, BigDecimal valueLimit) {
		//we need to validate again because the last edge (destination) can be higher than the limit
		if (!loopValidation(passed, edgeLimit, valueLimit)) {
			routes.put(index++, new LinkedList<>(passed));
		}
	}

	private boolean loopValidation(LinkedList<Edge> passed, Integer edgeLimit, BigDecimal valueLimit) {
		//if zero passed we ignore the limits, otherwise check the passed list
		return (valueLimit.intValue() > 0 ? sumRouteCost(passed).compareTo(valueLimit) >= 0 : false)
				|| (edgeLimit > 0 ? passed.size() > edgeLimit : false);
	}

	public BigDecimal sumRouteCost(List<Edge> edges) {
		//Simple sum on the Edge costs
		BigDecimal cost = BigDecimal.ZERO;
		for (Edge edge : edges) {
			cost = cost.add(edge.getCost());
		}
		return cost.setScale(2, RoundingMode.HALF_EVEN);
	}

	public List<Edge> getShortestRoute(Map<Integer, List<Edge>> routes) {
		List<Edge> shortestRoute = null;
		
		//Simple interation to find the shotest by replacing the larger
		for (List<Edge> route : routes.values()) {
			if (shortestRoute == null || route.size() < shortestRoute.size()) {
				shortestRoute = route;
			}
		}
		
		return shortestRoute;
	}

}
