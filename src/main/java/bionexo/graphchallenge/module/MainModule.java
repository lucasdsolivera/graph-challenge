package bionexo.graphchallenge.module;

import java.util.Scanner;

import bionexo.graphchallenge.graph.builder.GraphBuilder;
import bionexo.graphchallenge.graph.model.Graph;
import bionexo.graphchallenge.graph.service.ExamplePrinter;
import bionexo.graphchallenge.graph.service.GraphService;

/**
* MainModule class is a representation of Modular programming
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class MainModule {

	private static final String WELCOME_MESSAGE = "Graph Challenge Bionexo";
	private static final String INPUT_MESSAGE = "Type the routes separeted by comma:";

	private static MainModule singleton = null;
	private static Graph graph = null;
	private static GraphService service = null;
	private static ExamplePrinter examplePrinter = null;
	
	public Scanner scanner = null;

	public static MainModule getSingleton() {
		if (MainModule.singleton == null) {
			MainModule.singleton = new MainModule();
		}
 
		return MainModule.singleton;
	}
	
	public void init() {
		this.scanner = new Scanner(System.in);
		String input = inputArgs();
		buildGraph(input);
		processAndPrint();
	}
	
	public void processAndPrint() {
		examplePrinter = new ExamplePrinter(service);
		examplePrinter.printExample1();
		examplePrinter.printExample2();
		examplePrinter.printExample3();
		examplePrinter.printExample4();
		examplePrinter.printExample5();
		examplePrinter.printExample6();
		examplePrinter.printExample7();
		examplePrinter.printExample8();
		examplePrinter.printExample9();
		examplePrinter.printExample10();
		examplePrinter.printExample11();
		examplePrinter.printExample12();
	}

	private String inputArgs() {
		printNewLine();
		printString(WELCOME_MESSAGE);
		printNewLine();
		printString(INPUT_MESSAGE);
		printNewLine();
		return readLine();
	}
	
	public void buildGraph(String input) {
		graph = new GraphBuilder().withInput(input).build();
		service = new GraphService(graph);
	}
	
	private String readLine() {
		return this.scanner.nextLine();
	}

	public void printNewLine() {
		System.out.println();
	}

	public void printString(String value) {
		System.out.print(value);
	}	  
		
}
