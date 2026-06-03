/**
 *
 */
package algo.tree.interval;

/**
 * Based on Cormen/Rivest book - "Interval Trees; chapter 14"
 *
 * @author sujeet
 *
 */
public class IntervalTree {
    private IntervalNode root;

    IntervalNode getRoot() {
        return root;
    }

    /**
     *
     * @param in
     * @return
     */
    public IntervalNode insert(Interval in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        IntervalNode toBeInserted = new IntervalNode(in);

        if (this.root == null) {
            this.root = toBeInserted;
            return this.root;
        }

        insert(this.root, toBeInserted);
        return this.root;
    }

    /**
     *
     * @param current      should be non-null
     * @param toBeInserted
     */
    private void insert(IntervalNode current, IntervalNode toBeInserted) {
        if (current.toGoLeftForInsertion(toBeInserted.getInterval())) {
            if (current.getLeft() == null) {
                current.setLeft(toBeInserted);
            } else {
                insert(current.getLeft(), toBeInserted);
            }

        } else {
            if (current.getRight() == null) {
                current.setRight(toBeInserted);
            } else {
                insert(current.getRight(), toBeInserted);
            }
        }

        int newMax = toBeInserted.getMax() > current.getMax()
                ? toBeInserted.getMax()
                : current.getMax();
        current.setMax(newMax);
    }

    public boolean isPresent(Interval in) {
        if (in == null) {
            throw new IllegalArgumentException(
                    "Specified interval cannot be null");
        }

        if (this.root == null) {
            return false;
        }

        return false;
    }

    /**
     *
     * @param in
     * @return The deleted Node if present, null otherwise.
     */
    public IntervalNode delete(Interval in) {
        if (!isPresent(in)) {
            throw new IllegalArgumentException(
                    "Could not find the interval to delete");
        }

        if (this.root == null) {
            return null;
        }

        return null;
    }

    /**
     *
     * @param in
     * @return
     */
    public boolean hasAnOverlap(Interval in) {
        if (in == null) {
            throw new IllegalArgumentException(
                    "Specified interval cannot be null");
        }

        if (this.root == null) {
            return false;
        }

        return hasAnOverlap(this.root, in);
    }

    private boolean hasAnOverlap(IntervalNode current, Interval in) {
        if (current.getInterval().isOverlapping(in)) {
            return true;
        }

        if (current.isLeaf()) {
            return false;
        }

        if (current.toGoLeftForSearch(in)) {
            // No need to check for left-->null, as that would mean
            // going right.
            return hasAnOverlap(current.getLeft(), in);
        }

        // Going Right in the hunt:
        return hasAnOverlap(current.getRight(), in);
    }
}
