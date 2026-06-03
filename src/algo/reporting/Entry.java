/**
 *
 */
package algo.reporting;

/**
 * Entry (immutable)
 *
 * We implement Comparable to allow the objects as keys (if Sorted Map) Or
 * values (if Sorted set)
 */
public final class Entry implements Comparable<Entry> {
    final String fileName;
    final int fSize;
    final String cName;

    public Entry(String fName, int fSize, String cName) {
        if (fName == null || fName.isEmpty()) {
            throw new IllegalArgumentException(
                    "File Name cannot be null or empty");
        }

        if (fSize < 0) {
            throw new IllegalArgumentException("size cannot be -ve");
        }

        this.fileName = fName;
        this.fSize = fSize;
        this.cName = cName == null || cName.isEmpty() ? fName : cName;
    }

    /**
     * This is not used for comparing/ordering while traversing the tree (in
     * Sorted Map/Set).
     *
     * Rather, this would be used to check the equality.
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that instanceof Entry) {
            Entry thatE = (Entry) that;
            return thatE.cName.equals(this.cName);
        }
        return false;
    }

    /**
     * Honoring the Equals-Hashcode contract (if we plan to use this in
     * HashMap/HashSet, this is a must)
     */
    @Override
    public int hashCode() {
        return this.cName.hashCode();
    }

    /**
     * Follows Partial-Order comparison, which is used for TreeMap/TreeSet and
     * is NOT coherent with 'Equals'.
     *
     * Example, if cNames are accidentally same (which must be avoided, but
     * nonetheless) while the Metric values (i.e. fZise) are different, the
     * 'equals' will report true, while the compareTo will treat them as
     * different. This is not an expected situation, and the users of this class
     * must take care of avoiding this situation explicitly.
     */
    @Override
    public int compareTo(Entry that) {
        if (that == null) {
            System.out.println("Cannot compare against null");
        }

        /*
         * TODO this could be made configurable (forward/reverse ordering
         * control)
         */

        // For reverse order
        int bySize = -this.fSize + that.fSize;

        /*
         * Partitioning based on Equivalence-Class derived from the multi-key
         * attributes (i.e. compose with 'cName' i.e. the resource-label, to
         * ensure uniqueness), if the compared attribute results into equal
         * values (i.e. collision).
         *
         * Tomorrow, if we want to Sort based on multiple values, the relative
         * priorities amongst the compared attributes must be predefined, say:
         * <{fSize, other_metric1, other_metric1, ..., cName}>; i.e. 'cName' in
         * the end.
         *
         * In other words, (only if) 'fZise' are equal, then compareTo
         * 'other_metric1'; if that's equal as well, then include the next, and
         * until 'cName' (which is unique). This will guarantee:
         *
         * 1. There are no collisions, while there is Ordering requirements are
         * met as per JDK TreeMap/TreeSet
         *
         * 2. Searching/Accessing/Deleting is O(lg n); While printing the sorted
         * list is O(n) - i.e. no explicit sorting required, that would
         * otherwise cost O(n * lg (n))
         *
         */
        return bySize != 0 ? bySize : that.cName.compareTo(this.cName);
    }

    /**
     * Creates a new immutable copy
     *
     * @param newSize  must be >= 0
     * @param newFName leave empty to use the existing name
     * @return
     */
    public final Entry copyUpdateSize(int newSize, String newFName) {
        if (newFName == null) {
            throw new IllegalArgumentException(
                    "File Name cannot be null");
        }

        if (newSize < 0) {
            throw new IllegalArgumentException("size cannot be -ve");
        }

        newFName = newFName.isEmpty() ? this.fileName : newFName;
        return new Entry(this.fileName + newFName, newSize, this.cName);
    }

    @Override
    public String toString() {
        return String.format("{%s, %d}", this.cName, this.fSize);
    }
}
