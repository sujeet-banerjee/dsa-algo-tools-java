package com.suz.graph.dfs.buildsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.suz.graph.dfs.buildsystem.DependencyGraph.FileInput;

class TestDepsGraphProducerBuildOrder {
	
	private DependencyGraph depsG;
	
	public FileInput makeFileInput(String fName, char[] produces, char[] consumes) {
		List<Character> produceList = new ArrayList<>();
		for(Character tk: produces) {
			produceList.add(tk);
		}
		List<Character> consumerList = new ArrayList<>();
		for(Character tk: consumes) {
			consumerList.add(tk);
		}
		return new FileInput(fName, 
				produceList, consumerList
				);
	}

	@BeforeEach
	void setUp() throws Exception {
		this.depsG = new DependencyGraph();

		/*
		 * // Island 1
		tmi.addFile("F1", 
				new char[] {'D'},
				new char[] {'A', 'B'});
		tmi.addFile("F2", 
				new char[] {'A'},
				new char[] {'B'});
		tmi.addFile("F3", 
				new char[] {},
				new char[] {'D', 'X'});
		tmi.addFile("F4", 
				new char[] {'B', 'Y'},
				new char[] {});
		tmi.addFile("F5", 
				new char[] {'X'},
				new char[] {'B'});
		tmi.addFile("F6", 
				new char[] {},
				new char[] {'D', 'X'});
		
		// Island 2
		tmi.addFile("F10", 
				new char[] {'P'},
				new char[] {});
		tmi.addFile("F11", 
				new char[] {'Q'},
				new char[] {'P'});
		 */
		
		
		List<FileInput> files = new ArrayList<>();
		// Island 1
		files.add(makeFileInput("F1", 
				new char[] {'D'},
				new char[] {'A', 'B'}));
		files.add(makeFileInput("F2", 
				new char[] {'A'},
				new char[] {'B'}));
		files.add(makeFileInput("F3", 
				new char[] {},
				new char[] {'D', 'X'}));
		files.add(makeFileInput("F4", 
				new char[] {'B', 'Y'},
				new char[] {}));
		files.add(makeFileInput("F5", 
				new char[] {'X'},
				new char[] {'B'}));
		files.add(makeFileInput("F6", 
				new char[] {},
				new char[] {'D', 'X'}));
		
		// Island 2
		files.add(makeFileInput("F10", 
				new char[] {'P'},
				new char[] {}));
		files.add(makeFileInput("F11", 
				new char[] {'Q'},
				new char[] {'P'}));
		
		this.depsG.buildGraph(files);
		this.depsG.buildOrderedDependencies();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		//fail("Not yet implemented");
		System.out.println(
			"Absolute Consumers: "+ 
			this.depsG.getAbsoluteConsumers());
		
		System.out.println(
				"Ordered Build: "+ 
				this.depsG.getOrderedDependencies());
		
		System.out.println(
				"Deps Graph: "+ 
				this.depsG.getDependencyGraph());
	}

}
