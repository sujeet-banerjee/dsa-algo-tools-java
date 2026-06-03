/**
 *
 */
package algo3;

import java.util.NoSuchElementException;

/**
 * @author sujeet
 *
 */
public class Deque<Item> implements Iterable<Item> {

    class Node<Item> {
        Item value;
        Node prev;
        Node next;

        void setNext(Node<Item> n) {
            this.next = n;
            if (n != null) {
                n.prev = this;
            }
        }

        void setPrev(Node<Item> p) {
            this.prev = p;
            if (p != null) {
                p.next = this;
            }
        }
    }

    private Node<Item> first;
    private Node<Item> last;
    private int length;

    // construct an empty deque
    public Deque() {

        /*
         * Conventions:
         * 1. If first == last but both non-null ==> only one element
         * 2. if first == last == null ==> no element or empty deque
         */
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.length;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "Item to be added cannot be null");
        }
        if (this.first == null) {
            if (this.last != null) {
                throw new IllegalStateException(
                        "Both first and last should be null together");
            }
            this.first = new Node<>();
            this.last = this.first;
            this.first.value = item;

        } else {
            Node<Item> oldFirst = this.first;
            this.first = new Node<>();
            this.first.value = item;
            oldFirst.setPrev(first);
        }

        this.length++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "Item to be added cannot be null");
        }
        if (this.first == null) {
            if (this.last != null) {
                throw new IllegalStateException(
                        "Both first and last should be null together");
            }
            this.first = new Node<>();
            this.last = this.first;
            this.first.value = item;

        } else {
            Node<Item> oldLast = this.last;
            this.last = new Node<>();
            this.last.value = item;
            oldLast.setNext(this.last);
        }

        this.length++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove from Empty");
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove from Empty");
        }
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}
