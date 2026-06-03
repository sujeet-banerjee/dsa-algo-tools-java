package com.suz.graph.dfs;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestTokenProcessingOrder {

	public TestTokenProcessingOrder() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testCreateTreeAndListOrders() {
		TokenMetadataIngester tmi = createData();
		Map<String, List<String>> graph = tmi.makeGraph();
		
		System.out.println("Graph: "+ graph);
		System.out.println("Zero Indegree Nodes: "+ 
		tmi.getZeroIndegreeNodes());
		
		System.out.println("Ordered Files: "+ 
				tmi.getOrderedFileList());
		System.out.println("Ordered Tokens produced: "+ 
				tmi.getOrderedTokenProducedList());
	}
	
	@Test
	public void testCreateTree() {
		TokenMetadataIngester tmi = createData();
		Map<String, List<String>> graph = tmi.makeGraph();
		
		System.out.println("Graph: "+ graph);
		System.out.println("Zero Indegree Nodes: "+ 
		tmi.getZeroIndegreeNodes());
	}

	private TokenMetadataIngester createData() {
		TokenMetadataIngester tmi = new TokenDependencyGraph() ;
		
		// Island 1
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
		
		return tmi;
	}

}
