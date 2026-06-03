package my.code.graph.dfs.dag;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;


public class DagHelper {
	
	public static List<String> buildDependencyList(BuildSystem bs) {
		Map<String, String> producers = buildProducers(bs);
		
		List<String> buildOrder = new ArrayList<>();
		buildDag(bs, producers, null, buildOrder);
		
		return buildOrder;
	}
	
	public static Map<String, String> buildProducers(BuildSystem bs) {
		Map<String, String> ret = new HashMap<>();
		
		for(String f: bs.getFiles()) {
			Set<String> tokens = bs.getTokensProduced(f);
			for(String tk: tokens) {
				if(ret.containsKey(tk)) {
					throw new IllegalStateException(
						String.format("Cannot have token %s produced by another file %s. "
								+ "The file %s already produced the token", 
								tk, f, ret.get(tk))
							);
				}
				ret.put(tk, f);
			}
		}
		
		return ret;
	}
	
	public static Set<String> getDeps(String f, 
			BuildSystem bs, 
			Map<String, String> producers) {
		Set<String> tks = bs.getTokensConsumed(f);
		return tks.stream().map(x -> (producers.get(x))).collect(Collectors.toSet());
	}
	
	public static void buildDag(
			BuildSystem bs,
			Map<String, String> producers,
			Map<String, Integer> visited,
			List<String> buildOrder) {
		
		if(visited == null) {
			visited = new HashMap<>();
		}
			
		for(String f: bs.getFiles()) {
			buildDfsDag(f, producers, bs, visited, buildOrder);
		}
		
	}
	
	public static void buildDfsDag(String f, Map<String, String> producers,
			BuildSystem bs, Map<String, Integer> visited,
			List<String> buildOrder) {
		
		if(visited.containsKey(f)) {
			if(visited.get(f) == 1) {
				throw new IllegalStateException(
						"Cycle detected chasing dependencies of file: "+ f);
			}
			
			if(visited.get(f) == 2) {
				// All GOOD!
				return;
			}
			
			throw new IllegalStateException(
					"Unrecognized visit-state for file "+
							f+ ": "+ visited.get(f));
		}
		
		// Start visiting down the lane...
		visited.put(f, 1);
		
		Set<String> deps = getDeps(f , bs, producers);
		for(String dep: deps) {
			buildDfsDag(dep, producers, bs, visited, buildOrder);
		}
		
		// Mark completed!
		visited.put(f, 2);
		buildOrder.add(f);
	}
}
