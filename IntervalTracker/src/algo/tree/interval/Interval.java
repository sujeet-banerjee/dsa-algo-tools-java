/**
 *
 */
package algo.tree.interval;

/**
 * This represents a closed interval.
 *
 * How do you define overlap at boundary cases?
 *
 * How do you represent start and end - int or float?
 *
 * @author sujeet
 *
 */
public final class Interval {
    private final int start;
    private final int end;

    /**
     * TBD describe
     *
     * @param start
     * @param end
     */
    public Interval(int start, int end) {

        if (start < 0 || end < 0) {
            throw new IllegalArgumentException(
                    "start or end cannot be negative");
        }

        if (start > end) {
            throw new IllegalArgumentException(
                    "Invalid interval: start must be <= end");
        }

        this.start = start;
        this.end = end;
    }

    /**
     *
     * @param i2
     * @return
     */
    public boolean isOverlapping(Interval i2) {
        if (i2 == null) {
            throw new IllegalArgumentException(
                    "The specified interval cannot be null");
        }

        boolean nonOverlapping = i2.end <= this.start || this.end <= i2.start;
        return !nonOverlapping;
    }

    /**
     * Non-overlapping and to the right.
     *
     * @param i2
     * @return
     */
    public boolean isAtRight(Interval i2) {
        if (i2 == null) {
            throw new IllegalArgumentException(
                    "The specified interval cannot be null");
        }

        return this.end <= i2.start;
    }

    /**
     * Non-overlapping and to the left.
     *
     * @param i2
     * @return
     */
    public boolean isAtLeft(Interval i2) {
        if (i2 == null) {
            throw new IllegalArgumentException(
                    "The specified interval cannot be null");
        }

        return i2.end <= this.start;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

}
