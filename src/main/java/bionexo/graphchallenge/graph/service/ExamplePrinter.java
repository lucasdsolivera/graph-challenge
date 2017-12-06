package bionexo.graphchallenge.graph.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import bionexo.graphchallenge.graph.model.Client;
import bionexo.graphchallenge.graph.model.Edge;

/**
* 
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class ExamplePrinter {
	
	private Client clientA, clientB, clientC, clientD, clientE, clientF;
	private GraphService service = null;
	private String noRoute = "NO SUCH ROUTE";

	public ExamplePrinter(GraphService service) {
		this.service = service;
		initClients();
	}

	public void printExample1() {
		List<Client> especificRoute = Arrays.asList(clientA, clientD, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 2, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		if (validateNull(result, 1)) 
			return;
		System.out.println("Output #1: " + service.sumRouteCost(result).intValue());
	}

	public void printExample2() {
		List<Client> especificRoute = Arrays.asList(clientA, clientF, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 2, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		if (validateNull(result, 2)) 
			return;
		System.out.println("Output #2: " + service.sumRouteCost(result).intValue());
	}
	
	public void printExample3() {
		List<Client> especificRoute = Arrays.asList(clientE, clientC, clientB);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientE, clientB, 3, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		System.out.println("Output #3: " + service.sumRouteCost(result).intValue());
	}
	
	public void printExample4() {
		List<Client> especificRoute = Arrays.asList(clientB, clientD, clientF, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientB, clientE, 3, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		System.out.println("Output #4: " + service.sumRouteCost(result).intValue());
	}
	
	public void printExample5() {
		List<Client> especificRoute = Arrays.asList(clientF, clientC);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientF, clientC, 1, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		System.out.println("Output #5: " + service.sumRouteCost(result).intValue());
	}
	
	public void printExample6() {
		HashSet<Client> edges = service.getAdjacentsBackward(clientC);
		System.out.println("Output #6: " + edges.size());
	}
	
	public void printExample7() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientB, clientA, 5, BigDecimal.ZERO);
		System.out.println("Output #7: " + routes.size());
	}
	
	public void printExample8() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientA, 5, BigDecimal.ZERO);
		Map<Integer, List<Edge>> filter = service.filterNumberOfEdges(routes, 3);
		System.out.println("Output #8: " + filter.size());
	}
	
	public void printExample9() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 4, BigDecimal.ZERO);
		List<Edge> route = service.getShortestRoute(routes);
		System.out.println("Output #9: " + service.sumRouteCost(route).intValue());
	}
	
	public void printExample10() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientC, clientE, 4, BigDecimal.ZERO);
		List<Edge> route = service.getShortestRoute(routes);
		System.out.println("Output #10: " + service.sumRouteCost(route).intValue());
	}
	
	public void printExample11() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientB, 0, new BigDecimal("40.00"));
		System.out.println("Output #11: " + routes.size());
	}
	
	public void printExample12() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientE, clientD, 0, new BigDecimal("60.00"));
		System.out.println("Output #12: " + routes.size());
	}

	private void initClients() {
		clientA = new Client("A", "Client_A");
		clientB = new Client("B", "Client_B");
		clientC = new Client("C", "Client_C");
		clientD = new Client("D", "Client_D");
		clientE = new Client("E", "Client_E");
		clientF = new Client("F", "Client_F");
	}

	private boolean validateNull(List<Edge> result, Integer example) {
		if (result == null) {
			System.out.println("Output #" + example + ": " + noRoute);
			return true;
		}
		return false;
	}
}
