/**
 *
 */
package algo.reporting;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * Original problem statement deleted, to honor proprietary information.
 *
 */
public class ReportingTool {
    private Map<String, Integer> o1Map = new HashMap<>();
    private SortedSet<Entry> ordered = new TreeSet<Entry>();

    /**
     * Insert an entry
     *
     * @param newEntry
     */
    public void insert(Entry newEntry) {
        // FIXME convert to log
        System.out.println(
                String.format("\n >>> Insert: [%s, %d --> %s]", newEntry.fileName,
                        newEntry.fSize, newEntry.cName));

        if (this.o1Map.containsKey(newEntry.cName)) {
            int existing = this.o1Map.get(newEntry.cName);
            this.o1Map.put(newEntry.cName, (existing + newEntry.fSize));

            /*
             *  If you don't remove there will be duplicates, as the entries
             *  are compared based on their Values, and not keys.
             *
             * @see @Entry#compareTo docs
             */
            Entry existingEntry = newEntry.copyUpdateSize(existing, "");
            boolean rem = this.ordered.remove(existingEntry);
            if(rem == false) {
                System.out.println(
                        "Warning: the entry was not removed: " + existingEntry);
            }

            this.ordered.add(newEntry.copyUpdateSize(
                    existing + newEntry.fSize, newEntry.fileName));

            // debug-logs
            /**
             * toString on a sorted collction is O(n) - avoid it, to maintain
             * O(lg n) inserstion
             */

        } else {
            this.o1Map.put(newEntry.cName, newEntry.fSize);
            this.ordered.add(newEntry);

            // debug-logs
            /**
             * toString on a sorted collction is O(n) - avoid it, to maintain
             * O(lg n) insertion
             */
        }
    }

    /**
     *
     * @return Sorted Entries (with decreasing order of resource consumption)
     */
    public SortedSet<Entry> getReport() {
        return this.ordered;
    }

    /**
     * Print the Ordered report in O(n) time
     */
    public void printReport() {
        System.out.println(this.ordered);
    }
}
