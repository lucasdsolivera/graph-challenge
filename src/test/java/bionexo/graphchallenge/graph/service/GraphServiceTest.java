package bionexo.graphchallenge.graph.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import bionexo.graphchallenge.graph.builder.GraphBuilder;
import bionexo.graphchallenge.graph.model.Client;
import bionexo.graphchallenge.graph.model.Edge;
import bionexo.graphchallenge.graph.model.Graph;


/**
* The GraphServiceTest class tests all the Bionexo's challenge outputs requirements
* using GraphService implementation with @Before method initializing all the structure in the example.
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
@RunWith(MockitoJUnitRunner.class)
public class GraphServiceTest {
	
	private GraphService service;
	private Client clientA, clientB, clientC, clientD, clientE, clientF; 
	
	@Before
	public void init() {
		Graph graph = new GraphBuilder().withInput("AD4, DE1, EC8, CB2, BA6, AC9, DF7, FC5, FE9, BD3, FA3").build();
		service = new GraphService(graph);
		initClients();
	}

	/*
	 * 1. The cost of the route A-D-E.
	 */
	@Test
	public void shouldGetCostRoute_A_D_E() {
		List<Client> especificRoute = Arrays.asList(clientA, clientD, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 2, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		
		assertEquals(clientA, result.get(0).getSource());
		assertEquals(clientD, result.get(0).getDestination());
		assertEquals(clientD, result.get(1).getSource());
		assertEquals(clientE, result.get(1).getDestination());
		assertEquals(new BigDecimal("5.00"), service.sumRouteCost(result));
	}
	
	/*
	 * 2. The cost of the route A-F-E.
	 */
	@Test
	public void shouldNotGetRoute_A_F_E() {
		List<Client> especificRoute = Arrays.asList(clientA, clientF, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 2, BigDecimal.ZERO);
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		assertNull(result);
	}
	
	/*
	 * 3. The cost of the route E-C-B.
	 */
	@Test
	public void shouldGetCostRoute_E_C_B() {
		List<Client> especificRoute = Arrays.asList(clientE, clientC, clientB);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientE, clientB, 3, BigDecimal.ZERO);
		
		assertEquals(1, routes.size());
		
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		
		assertEquals(clientE, result.get(0).getSource());
		assertEquals(clientC, result.get(0).getDestination());
		assertEquals(clientC, result.get(1).getSource());
		assertEquals(clientB, result.get(1).getDestination());
		assertEquals(new BigDecimal("10.00"), service.sumRouteCost(routes.get(0)));
	}
	
	/*
	 * 4. The cost of the route B-D-F-E.
	 */
	@Test
	public void shouldGetCostRoute_B_D_F_E() {
		List<Client> especificRoute = Arrays.asList(clientB, clientD, clientF, clientE);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientB, clientE, 3, BigDecimal.ZERO);
		
		assertTrue(routes.size() > 1);
		
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		
		assertEquals(clientB, result.get(0).getSource());
		assertEquals(clientD, result.get(0).getDestination());
		assertEquals(clientD, result.get(1).getSource());
		assertEquals(clientF, result.get(1).getDestination());
		assertEquals(clientF, result.get(2).getSource());
		assertEquals(clientE, result.get(2).getDestination());
		assertEquals(new BigDecimal("19.00"), service.sumRouteCost(result));
	}
	
	/*
	 * 5. The cost of the route F-C.
	 */
	@Test
	public void shouldGetCostRoute_F_C() {
		List<Client> especificRoute = Arrays.asList(clientF, clientC);
		Map<Integer, List<Edge>> routes = service.getRoutes(clientF, clientC, 1, BigDecimal.ZERO);
		
		assertEquals(1, routes.size());
		
		List<Edge> result = service.getExpecificRoute(routes, especificRoute);
		
		assertEquals(clientF, result.get(0).getSource());
		assertEquals(clientC, result.get(0).getDestination());
		assertEquals(new BigDecimal("5.00"), service.sumRouteCost(result));
		
	}
	
	/*
	 * 6. How many edges are arriving at client `C`.
	 */
	@Test
	public void shouldGetEdgesArravingClientC() {
		HashSet<Client> edges = service.getAdjacentsBackward(clientC);
		
		assertEquals(3, edges.size());
		assertTrue(edges.contains(clientA));
		assertTrue(edges.contains(clientE));
		assertTrue(edges.contains(clientF));
	}
	
	/*
	 * 7. How many routes start at client `B` and end at client `A` with a maximum of 5 stops.
	 */
	@Test
	public void shouldGetRoutes_B_A_WithMaximum_5_Stops() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientB, clientA, 5, BigDecimal.ZERO);
		
		assertEquals(6, routes.size());
	}
	
	/*
	 * 8. How many routes start at client `A` and end at the same client (`A`) with exactly 3 stops.
	 */
	@Test
	public void shouldGetRoutes_A_A_WithExactly_3_Stops() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientA, 5, BigDecimal.ZERO);
		
		assertEquals(5, routes.size());
		
		Map<Integer, List<Edge>> filter = service.filterNumberOfEdges(routes, 3);
		
		assertEquals(2, filter.size());
	}
	
	/*
	 * 9. The cost of the shortest route between the clients `A` and `E`.
	 */
	@Test
	public void shouldGetCostOFShortestRoute_A_E() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientE, 4, BigDecimal.ZERO);
		
		assertEquals(3, routes.size());
		
		List<Edge> route = service.getShortestRoute(routes);
		
		assertEquals(new BigDecimal("5.00"), service.sumRouteCost(route));
	}
	
	/*
	 * 10. The cost of the shortest route between the clients `C` and `E`.
	 */
	@Test
	public void shouldGetCostOFShortestRoute_C_E() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientC, clientE, 4, BigDecimal.ZERO);
		
		assertEquals(3, routes.size());
		
		List<Edge> route = service.getShortestRoute(routes);
		
		assertEquals(new BigDecimal("6.00"), service.sumRouteCost(route));
	}
	
	/*
	 * 11. The number of different routes between the clients `A` and `B` that cost less than 40.
	 */
	@Test
	public void shouldTraceARoute_A_B() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientA, clientB, 0, new BigDecimal("40.00"));
		assertEquals(27, routes.size());
	}
	
	/*
	 * 12. The number of different routes between the clients `E` and `D` that cost less than 60.
	 */
	@Test
	public void shouldTraceARoute_E_D() {
		Map<Integer, List<Edge>> routes = service.getRoutes(clientE, clientD, 0, new BigDecimal("60.00"));
		assertEquals(137, routes.size());
	}

	private void initClients() {
		clientA = new Client("A", "Client_A");
		clientB = new Client("B", "Client_B");
		clientC = new Client("C", "Client_C");
		clientD = new Client("D", "Client_D");
		clientE = new Client("E", "Client_E");
		clientF = new Client("F", "Client_F");
	}
}
