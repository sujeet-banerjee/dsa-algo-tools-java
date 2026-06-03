package com.suz.graph.dfs;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public interface TokenMetadataIngester {
	/**
	 * 
	 * @param fName
	 * @param tokensProduced
	 * @param tokensConsumed
	 */
	public void addFile(String fName, char[] tokensProduced, char[] tokensConsumed);
	
	public Map<String, List<String>> makeGraph();
	
	public List<String> getZeroIndegreeNodes();
	
	public List<String> getOrderedFileList();
	
	public List<Character> getOrderedTokenProducedList();
}
