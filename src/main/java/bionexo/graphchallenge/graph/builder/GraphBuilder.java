package bionexo.graphchallenge.graph.builder;

import java.math.BigDecimal;
import java.util.Arrays;

import bionexo.graphchallenge.graph.model.Client;
import bionexo.graphchallenge.graph.model.Edge;
import bionexo.graphchallenge.graph.model.Graph;

/**
* GraphBuilder implements the building a structure of a Graph class that is necessary in this application
* to perform all the functions and tests
* 
* this class read the input that is required and use Regex patterns to validate then and build Graph. 
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class GraphBuilder {
	
	private Graph graph = null;
	private String INVALID_INPUT_MESSAGE = "There are incompatible characters in input.";
	
	public GraphBuilder() {
		this.graph = new Graph();
	}
	
	public GraphBuilder withInput(String input) {
		
		String[] inputEdges = input.replaceAll(" ", "").split(",");
		
		Arrays.asList(inputEdges).forEach(inputEdge -> {
			validateInput(inputEdge);
			Client source = new Client(inputEdge.substring(0,1), "Client_" + inputEdge.substring(0,1));
			Client destination = new Client(inputEdge.substring(1,2), "Client_" + inputEdge.substring(1,2));
			Edge edge = new Edge(
							source.getId().concat("_").concat(destination.getId()),
							source,
							destination,
							new BigDecimal(inputEdge.substring(2)));
			
			graph.getClients().add(source);
			graph.getClients().add(destination);
			graph.getEdges().add(edge);
		});

		return this;
	}

	public Graph build() {
		return this.graph;
	}
	
	private void validateInput(String input) throws  IllegalArgumentException {
		if (!input.substring(0, 1).matches("[A-Z]") || 
			!input.substring(1, 2).matches("[A-Z]") ||
			!input.substring(2).matches("^[0-9]+$"))
			throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
	}
	
}
