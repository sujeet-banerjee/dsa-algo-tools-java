package algo.reporting;

import java.util.Comparator;
import java.util.Map;

/**
 * 
 */
public final class ReportingToolValueComparator implements Comparator<String>{
	final Map<String, Integer> map;
	
	@Override
	public int compare(String o1, String o2) {
		return this.map.get(o2).compareTo(this.map.get(o1));
	}

	public ReportingToolValueComparator(Map<String, Integer> map) {
		this.map = map;
	}
}