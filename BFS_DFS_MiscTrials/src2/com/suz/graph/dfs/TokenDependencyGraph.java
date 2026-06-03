package com.suz.graph.dfs;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

/**
 * Ask:
 * 1. who is dependent on whom - those entities become the nodes. In this cases: Files.
 *    F1 depends on say, F3.
 *    Similarly, producers and consumers are node, in a food chain.
 * 2. what is the cause / object of dependency - tokens in this case.
 * 
 */
public class TokenDependencyGraph implements TokenMetadataIngester {
	
	private Map<String, List<String>> graph;
	
	private Map<Character, String> tokenProducers;
	
	private Map<String, char[]> producers;
	
	private Map<String, char[]> consumers;
	
	private List<String> zeroInDegreeNodes;

	/**
	 * 
	 */
	public TokenDependencyGraph() {
		this.tokenProducers = new HashMap<>();
		this.consumers = new HashMap<>();
		this.graph = new HashMap<>();
		this.zeroInDegreeNodes = new ArrayList<>();
		this.producers = new HashMap<>();
	}
	
	
	public void addFile(String fName, char[] tokensProduced, char[] tokensConsumed) {
		for(char tok: tokensProduced) {
			if(!this.tokenProducers.containsKey(tok)) {
				this.tokenProducers.put(tok, fName);
			} else {
				throw new IllegalArgumentException(String.format(
					"The token %s already produced by %s", tok,
					this.tokenProducers.get(tok)));
			}
		}
		
		this.producers.put(fName, tokensProduced);
		this.consumers.put(fName, tokensConsumed);
	}


	@Override
	public Map<String, List<String>> makeGraph() {
		System.out.println("Internal: consumers: "+ this.consumers);
		System.out.println("Internal: producers: "+ this.tokenProducers);
		zeroInDegreeNodes.addAll(this.consumers.keySet());
		
		for(String fName: this.consumers.keySet()) {
			// find dep tokens
			for(char tok: this.consumers.get(fName)) {
				// for each of the tok,
				if(this.tokenProducers.containsKey(tok)){
					// find the producer; make the edge
					if(!this.graph.containsKey(fName)) {
						this.graph.put(fName, new ArrayList<>());
					}
					graph.get(fName).add(this.tokenProducers.get(tok));
					
					// remove the producer from z-indegree
					this.zeroInDegreeNodes.remove(this.tokenProducers.get(tok));
				} else {
					throw new IllegalStateException(
							"No producer found for the token: "+ tok);
				}
			}
		}
		return this.graph;
	}


	@Override
	public List<String> getZeroIndegreeNodes() {
		return this.zeroInDegreeNodes;
	}
	
	@Override
	public List<String> getOrderedFileList() {
		// Do DFS traversal, and collect topo-sort
		
		List<String> ordered = new ArrayList<>();
		Map<String, Integer> visited = new HashMap<>();
		
		for(String node: this.zeroInDegreeNodes) {
			doDfs(graph, node, visited, ordered);
		}
		
		return ordered;
	}
	
	protected void doDfs(Map<String, List<String>> g,
			String node, 
			Map<String, Integer> visited,
			List<String> ordered ) {
		
		int status = visited.getOrDefault(node, 0);
		if(status == 1) {
			throw new IllegalStateException("Cycle detected at node: "+ node);
		}
		if(status == 2) {
			// Already visited and recorded!
			return;
		}
		
		if(!g.containsKey(node)) {
			// Already done! But not recorded yet.
			ordered.add(node);
			visited.put(node, 2);
			return;
		}
		
		
		// doDFS
		visited.put(node, 1);
		// visit and collect
		for(String child: g.get(node)) {
			doDfs(g, child, visited, ordered);
		}
		visited.put(node, 2);
		
		ordered.add(node);
	}
	
	@Override
	public List<Character> getOrderedTokenProducedList() {
		List<String> ordFiles = getOrderedFileList();
		final List<Character> ordTokens = new ArrayList<>();
		
//		ordFiles.stream().forEachOrdered( f -> {
//			ordTokens.add(producers.get(f));
//		});
		
		for(final String f: ordFiles) {
			//List<Character> collect = Arrays.asList(  );
			for(char tok: producers.get(f)) {
				ordTokens.add(tok);
			}
		}
		return ordTokens;
	}


}
