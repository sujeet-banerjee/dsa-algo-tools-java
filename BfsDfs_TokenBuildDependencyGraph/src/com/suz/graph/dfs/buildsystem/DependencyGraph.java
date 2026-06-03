package com.suz.graph.dfs.buildsystem;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Not designed for concurrency!
 */
public class DependencyGraph {
	
	public static class FileInput {
		final String fName;
		final List<Character> produces;
		final List<Character> consumes;
		
		public FileInput(String fName, 
				List<Character> produces, 
				List<Character> consumes) {
			this.fName = fName;
			this.consumes = consumes;
			this.produces = produces;
		}
	}

	private final Map<String, List<String>> depGraph = new HashMap<>();
	
	/**
	 * Nodes with indegree = 0, the ones to start DFS with
	 * Node <==> File
	 */
	private final Set<String> absoluteConsumers = new HashSet<>();
	
	/**
	 * Topologically sorted Node-values (files)
	 */
	private final List<String> ordered = new ArrayList<>();
	
	/**
	 * Map from token(char) --> FIle (producer Node)
	 */
	private final Map<Character, String> tokenToProducer = new HashMap<>();
	
	public DependencyGraph() {
		
		// Build Graph
		// Record the absolute consumers
		
		// Do DFS for each abs-consumer
		// While doing the DFS collect the topologically sorted nodes
		
	}
	
	public void buildGraph(List<FileInput> fInput) {
		if(fInput==null || fInput.isEmpty()) {
			throw new IllegalArgumentException("The input cannot be null or empty");
		}
		
		// TODO Cleanup the datastructs to avoid stale data!
		
		// Pass 1: map the token producers
		for(FileInput fIn: fInput) {
			this.absoluteConsumers.add(fIn.fName);
			for(Character tk: fIn.produces) {
				if(this.tokenToProducer.containsKey(tk)) {
					throw new IllegalArgumentException(
						String.format(
							"The token %s aready produced by the file %s",
								tk, this.tokenToProducer.get(tk))
							);
				}
				this.tokenToProducer.put(tk, fIn.fName);
			}
		}
		
		// Pass 2: Build the graph and collect the absolute-consumers
		for(FileInput fIn: fInput) {
			List<String> dependencies = this.depGraph.getOrDefault(
					fIn.fName, new ArrayList<String>());
			
			for(Character tk: fIn.consumes) {
				
				if(!this.tokenToProducer.containsKey(tk)) {
					throw new IllegalArgumentException(
						"No producer found for this token: "+ tk);
				}
				
				String dependency = this.tokenToProducer.get(tk);
				// The dependency (dest) node now has an in-degree, thus,
				this.absoluteConsumers.remove(dependency);
				dependencies.add(dependency);
			}
			
			this.depGraph.put(fIn.fName, dependencies);
		}
	}
	
	public void buildOrderedDependencies() {
		// init visited
		
		Map<String, Integer> visited = new HashMap<>();
		
		// For each abs-consumer, do DFS
		for(String node: this.absoluteConsumers) {
			doDFS(this.depGraph, node, visited);
		}
	}
	
	public void doDFS(Map<String, List<String>> graph,
			String start, Map<String, Integer> visited
			) {
		
		// Basic checks (already visited etc?)
		int state = visited.getOrDefault(start, 0);
		if(state == 2) {
			// ALready visited and completed (recorded)
			return;
		}
		if(state == 1) {
			// Under visit already ==> cycle detected!
			throw new IllegalStateException("Cycle detected at node: "+ start);
		}
		
		// updated visited - BEGIN
		visited.put(start, 1);
		
		/*
		 * Does not work!
		 * Graph: {F6=[F1, F5], F10=[], F11=[F10], F1=[F2, F4], F2=[F4], F3=[F1, F5], F4=[], F5=[F4]}

		 * Output
		 * Ordered Build: [F3, F10, F11, F5, F4, F2, F1, F6]
		   		 */
		// update result (ordered) - updating at the Head-recursion this time
		// this.ordered.add(0, start);
		
		// Recursive doDFS
		if(graph.containsKey(start)) {
			// It's possible there is a lone island node 
			// (i.e. produces or consumes nothing)
			for(String child: graph.get(start)) {
				this.doDFS(graph, child, visited);
			}
		} else {
			System.out.println("No node in the graph with name: "+ start);
		}
		
		// update visited - END
		visited.put(start, 2);
		
		this.ordered.add(start);
	}

	
	public List<String> getOrderedDependencies() {
		return this.ordered;
	}
	
	public Map<String, List<String>> getDependencyGraph() {
		return this.depGraph;
	}
	
	public Set<String> getAbsoluteConsumers() {
		return this.absoluteConsumers;
	}
}
