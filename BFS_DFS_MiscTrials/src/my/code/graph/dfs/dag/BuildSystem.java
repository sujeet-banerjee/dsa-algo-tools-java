package my.code.graph.dfs.dag;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BuildSystem {
	
	private Map<String, Set<String>> consumes = new HashMap<>();
	private Map<String, Set<String>> produces = new HashMap<>();
	
	public void addFile(String f, List<String> produces, List<String> consumes) {
		if(!this.produces.containsKey(f)) {
			this.produces.put(f, new HashSet<>());
		}
		this.produces.get(f).addAll(produces);
		
		if(!this.consumes.containsKey(f)) {
			this.consumes.put(f, new HashSet<>());
		}
		this.consumes.get(f).addAll(consumes);
	}
	
	public Set<String> getTokensProduced(String f) {
		return this.produces.get(f);
	}
	
	public Set<String> getTokensConsumed(String f) {
		return this.consumes.get(f);
	}
	
	public Set<String> getFiles() {
		return this.produces.keySet();
	}
	
	@Override
	public String toString() {
		StringBuilder sbBuilder = new StringBuilder();
		for(String f: produces.keySet()) {
			sbBuilder.append(f);
			sbBuilder.append(" --> ");
			
			for(String tk: produces.get(f)) {
				sbBuilder.append("P(");
				sbBuilder.append(tk);
				sbBuilder.append("), ");
			}
			
			for(String tk: consumes.get(f)) {
				sbBuilder.append("C(");
				sbBuilder.append(tk);
				sbBuilder.append("), ");
			}
			
			sbBuilder.append("\n");
		}
		
		return sbBuilder.toString();
	}
}
