/**
 * 
 */
package algo.reporting;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


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
public class ReportingTool2 {
	public Map<String, Integer> store = new HashMap<String, Integer>(
	);
	
	public Map<String, Integer> sortedStore = new TreeMap<>(
			new ReportingToolValueComparator(store)
			);

	public void insert (Entry e) {
		System.out.println("\n >>> Insert: ["+ (e.cName) + ", " + (e.fSize) + "]" );
		if(this.store.containsKey(e.cName)) {
			int existing = this.store.get(e.cName);
			this.store.put(e.cName, (existing + e.fSize));
			Integer rem = this.sortedStore.remove(e.cName);
			System.out.println("~~ SR(x): "+ this.sortedStore + " --> removed: "+ rem);
			this.sortedStore.put(e.cName, (existing + e.fSize));
			System.out.println("~~ SR(.): "+ this.sortedStore);
		} else {
			this.store.put(e.cName, e.fSize);
			this.sortedStore.put(e.cName, e.fSize);
			System.out.println("~~ SR(_): "+ this.sortedStore);
		}
	}

	Map<String, Integer> getStore() {
		return this.store;
	}
	
	Map<String, Integer> getSortedStore() {
		return this.sortedStore;
	}

}
