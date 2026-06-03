/**
 *
 */
package algo.tree.interval;

/**
 * Based on Cormen/Rivest book - "Interval Trees; chapter 14".
 *
 * Does not implement Red-Black mechanism. Thus balanced tree (and thus
 * O(log(n)) is not always guaranteed.
 *
 * Updating left or right nested subtrees does not guarantee update of
 * parent.max
 *
 * @author sujeet
 *
 */
class IntervalNode {

    /**
     * The interval
     */
    private final Interval interval;

    /**
     * The maximum value of the 'end's of all intervals of the subtrees rooted
     * at this node.
     */
    private int max;

    private IntervalNode left;
    private IntervalNode right;

    /**
     *
     * @param in
     */
    public IntervalNode(Interval in) {
        if (in == null) {
            throw new IllegalArgumentException(
                    "specified interval cannot be null");
        }
        this.interval = in;
        this.max = in.getEnd();
    }

    /**
     *
     * @return
     */
    public Interval getInterval() {
        return interval;
    }

    /**
     *
     * @return
     */
    public IntervalNode getLeft() {
        return left;
    }

    /**
     *
     * @param left
     */
    public void setLeft(IntervalNode left) {
        if (left == null) {
            throw new IllegalArgumentException(
                    "specified interval cannot be null");
        }

        if (this == left) {
            throw new IllegalArgumentException(
                    "Assigning left as itself will create a cycle");
        }

        this.left = left;
        this.max = left.max > this.max ? left.max : this.max;
    }

    /**
     *
     * @return
     */
    public IntervalNode getRight() {
        return right;
    }

    /**
     *
     * @param right
     */
    public void setRight(IntervalNode right) {
        if (right == null) {
            throw new IllegalArgumentException(
                    "specified interval cannot be null");
        }

        if (this == right) {
            throw new IllegalArgumentException(
                    "Assigning right as itself will create a cycle");
        }
        this.right = right;
        this.max = right.max > this.max ? right.max : this.max;
    }

    /**
     *
     * @return
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    /**
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @return
     */
    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return String.format("[%d,  %d] {%d}",
                this.interval.getStart(),
                this.interval.getEnd(),
                this.max);
    }

    /**
     * Compares the 'low' / 'start' values for insertion
     *
     * @param in
     * @return
     */
    public boolean toGoLeftForInsertion(Interval in) {
        if(in==null) {
            throw new IllegalArgumentException("Specified Interval cannot be null");
        }
        return in.getStart() < this.interval.getStart();
    }

    /**
     *
     * @param in
     * @return
     */
    public boolean toGoLeftForSearch(Interval in) {
        if (in == null) {
            throw new IllegalArgumentException(
                    "Specified Interval cannot be null");
        }

        // TODO comment
        boolean goRight = this.left == null;
        goRight = goRight || this.left.max < in.getStart();

        return !goRight;
    }

    // TBD: rotateRight, rotateLeft
}
