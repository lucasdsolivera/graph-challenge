package bionexo.graphchallenge.graph.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import bionexo.graphchallenge.graph.model.Client;
import bionexo.graphchallenge.graph.model.Edge;
import bionexo.graphchallenge.graph.model.Graph;
/**
* The GraphServiceTest class tests all the Graph structure based on input with the pattern: 
* 
* AD4, DE1, EC8, CB2, BA6
* 
* Where A and D are Clients
* A -> D is a Edge with cost of 4
* 
* This test class validates incorrect inputs and patterns
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
@RunWith(MockitoJUnitRunner.class)
public class GraphBuilderTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldCreateGraph() {
		assertNotNull(new GraphBuilder().build());
		assertTrue(Graph.class.equals(new GraphBuilder().build().getClass()));
	}
	
	@Test
	public void shouldNotCreateGraph() {
		thrown.expect(IllegalArgumentException.class);
	    thrown.expectMessage("There are incompatible characters in input.");
	    
		new GraphBuilder().withInput("AB1***BC").build();
	}
	
	@Test
	public void shouldCreateGraphWith2Clients() {
		Graph graph = new GraphBuilder().withInput("AB1").build();
		Client clientA = new Client("A", "Client_A");
		Client clientB = new Client("B", "Client_B");
		assertEquals(new HashSet<Client>(
				Arrays.asList( clientA, clientB)),
				graph.getClients());
	}

	@Test
	public void shouldCreateGraphWithAEdge() {
		Graph graph = new GraphBuilder().withInput("AB1").build();
		Client clientA = new Client("A", "Client_A");
		Client clientB = new Client("B", "Client_B");
		
		assertEquals(2, graph.getClients().size());
		assertEquals(1, graph.getEdges().size());
		assertEquals(new HashSet<Edge>(
				Arrays.asList(new Edge("A_B", clientA, clientB, BigDecimal.ONE))),
				graph.getEdges());
	}
	
	@Test
	public void shouldCreateGraphWithManyEdges() {
		Graph graph = new GraphBuilder().withInput("AB1, BC1, AC3").build();
		Client clientA = new Client("A", "Client_A");
		Client clientB = new Client("B", "Client_B");
		Client clientC = new Client("C", "Client_C");
		
		assertEquals(3, graph.getClients().size());
		assertEquals(3, graph.getEdges().size());
		assertEquals(new HashSet<Edge>(
				Arrays.asList(new Edge("A_B", clientA, clientB, BigDecimal.ONE),
							  new Edge("B_C", clientB, clientC, BigDecimal.ONE),
							  new Edge("A_C", clientA, clientC, new BigDecimal("3.00")))),
				graph.getEdges());
	}
	
}
