package bionexo.graphchallenge.app;

import bionexo.graphchallenge.module.MainModule;

/**
* Main method Application class.
*
* @author  Lucas Oliveira
* @version 1.0
* @since   2017-12-05 
*/
public class GraphChallengeApplication {
	
	public static MainModule module = null;
	
	private static void init() {
		module = MainModule.getSingleton();
		module.init();
	}

	public static void main(String[] args) {
		init();
	}
}
