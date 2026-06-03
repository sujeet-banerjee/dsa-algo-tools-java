/**
 * 
 */
package algo.reporting.dual;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.extension.ExtensionContext.Store;

/**
 * 
 * Imagine we have a system that stores files, and these files can be grouped into collections. We are interested in knowing where our resources are being taken up.
For this system, we would like to generate a report that lists:
The total size of all files stored; and
The top N collections (by file size) where N can be a user-defined value

An example input into your report generator might look like:
file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 300) in collection "collection2"
file5.txt (size: 10)
 * 
 * 
 */
public class ReportingTool {
	private Map<String, Integer> o1Map = new HashMap<>(); 
	private SortedSet<Entry> ordered = new TreeSet<Entry>();

	public void insert (Entry e) {
		System.out.println("\n >>> Insert: ["+ (e.cName) + ", " + (e.fSize) + "]" );
		if(this.o1Map.containsKey(e.cName)) {
			int existing = this.o1Map.get(e.cName);
			this.o1Map.put(e.cName, (existing + e.fSize));
			
			// If you don't remove there will be duplicates, as the entries
			// are compared based on their Values, and not keys
			
			boolean rem = this.ordered.remove(e.copyUpdateSize(existing, "~"));
			
			this.ordered.add(e.copyUpdateSize(existing + e.fSize, e.fileName));
			
			System.out.println("Lin Map: "+ this.o1Map);
			System.out.println("Tree Map: "+ this.ordered);
			
		} else {
			this.o1Map.put(e.cName, e.fSize);
			this.ordered.add(e);
			
			System.out.println("Lin Map: "+ this.o1Map);
			System.out.println("Tree Map: "+ this.ordered);
		}
	}

	SortedSet<Entry> getStore() {
		return this.ordered;
	}

}
