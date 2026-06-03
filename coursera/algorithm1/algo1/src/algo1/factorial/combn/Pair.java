/**
 *
 */
package algo1.factorial.combn;

/**
 * A non Mutable object representing a pair <first, second>
 *
 * @author sujeet
 *
 * @param <T> Should be a primitive type (say, int or Integer)
 * @param <V> Should be a primitive type (say, int or Integer)
 */
public final class Pair<T, V> {
    /**
     * first
     */
    private final T first;
    /**
     * second
     */
    private final V second;

    /**
     *
     * @param first
     * @param second
     * @throws IllegalArgumentException if any of the inputs is null.
     */
    public Pair(T first, V second) {

        if (first == null || second == null) {
            throw new IllegalArgumentException(
                    "Both second and first must be non null");
        }

        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair other = (Pair)obj;
        return this.first.equals(other.first)
                && this.second.equals(other.second);
    }

    @Override
    public int hashCode() {
        String str = String.format("Pair(%s,%s)",
                this.first, this.second);
        return str.hashCode();
    }

    @Override
    public String toString() {
        return String.format("<%s, %s>", this.first, this.second);
    }

}
