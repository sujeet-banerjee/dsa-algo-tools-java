package com.suz.graph.trials.nestedInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Unlike the previous one, it does NOT need an additional book-keeping
 * for keeping track of the current elm in a given nested-level.
 * 
 * Use a stack (as before), as you pop:
 *   - if it's a primitive return the value
 *   - else fetch all its children, reverse it, 
 *     and then push into the stack (call next recursively)
 */
public class NestedIntDfsIterator  implements Iterator<NestedInt> {
	private final NestedInt ni;
	
	/*
	 * DFS ===> Stack
	 */
	private final List<NestedInt> stack;

	public NestedIntDfsIterator(final NestedInt ni){
		this.ni = ni;
		this.stack = new ArrayList<>();
		this.stack.add(ni);
	}
	
	public boolean hasNext() {
		return this.checkSize();
	}
	
	public boolean checkSize() {
		return this.stack.size() > 0;
	}
	
	public NestedInt next() throws IllegalStateException {
		if(!this.checkSize()) {
			throw new IllegalStateException(
					"No more elements. Please call checkSize(...)");
		}
		
		int size = this.stack.size();
		NestedInt next = this.stack.remove(size-1);
		if(!next.isNested()) {
			return next;
		}
		
		NestedInt[] children = next.getNested();
		// Reverse-Push into the stack 
		// (i.e. reverse the children, and push normally)
		for(int i=children.length-1; i>=0; i--) {
			this.stack.add(children[i]);
		}
		
		return next();
	}
}
