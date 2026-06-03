/**
 * 
 */
package algo.reporting;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
	public TreeMap<String, Integer> store = new TreeMap<String, Integer>(
//			new ReportingToolValueComparator(store)
			//new ReportingToolEntryComparator()
	);

	public void insert (Entry e) {
		
		// TODO ensure non null key
		if(this.store.containsKey(e.cName)) {
			int existing = this.store.get(e.cName);
			this.store.put(e.cName, (existing + e.fSize));
		} else {
			this.store.put(e.cName, e.fSize);
		}
	}

	TreeMap<String, Integer> getStore() {
		return this.store;
	}

}
