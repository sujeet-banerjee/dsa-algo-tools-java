package com.suz.graph.trials.nestedInt;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class NestedIntBfsIterator implements Iterator<NestedInt> {
	
	private final NestedInt ni;
	// BFS ==> Queue
	private final List<NestedInt> queue = new ArrayList<>();

	public NestedIntBfsIterator(final NestedInt ni) {
		this.ni = ni;
		this.queue.add(0, ni);
	}
	
	public boolean checkSize() {
		return this.queue.size() > 0;
	}
	
	public boolean hasNext() {
		return checkSize();
	}
	
	public NestedInt next() {
		if(!checkSize()) {
			throw new IllegalStateException("No more elements! Call 'hasNext()'");
		}
		
		int size = this.queue.size();
		NestedInt next = this.queue.remove(size-1);
		if(!next.isNested()) {
			return next;
		}
		
		NestedInt[] children = next.getNested();
		for(NestedInt child : children) {
			this.queue.add(0, child);
		}
			
		return next();
	}

}
