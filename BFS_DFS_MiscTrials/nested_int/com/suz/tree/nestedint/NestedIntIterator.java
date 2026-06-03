package com.suz.tree.nestedint;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class NestedIntIterator {
	final NestedInt ni;
	final Stack<NestedInt> stk = new Stack<>();
	final Map<NestedInt, Integer> lookup = new HashMap<>();
	public NestedIntIterator(NestedInt ni) {
		this.ni = ni;
		this.lookup.put(ni, 0);
		this.stk.push(ni);
	}
	
	public boolean hasNext() {
		Iterator<NestedInt> itr = this.stk.iterator();
		int sum=0;
		//int debugCount = 0;
		while(itr.hasNext() && sum<=0) {
			//debugCount++;
			NestedInt next = itr.next();
			int offset = this.lookup.get(next);
			sum += (next.children.size() - offset -1);
		}
		//System.out.println("[DEBUG] debugcount = "+ debugCount);
		return sum>0;
	}
	
	public Integer next() {
		while(!this.stk.isEmpty()) {
			NestedInt top = this.stk.peek();
			int offset = lookup.get(top);
			
			if(offset < top.children.size()) {
				NestedInt next = top.children.get(offset);
				
				if(!next.isNested()) {
					lookup.put(top, offset+1);
					return next.val;
				} else {
					lookup.put(top, offset+1);
					this.stk.push(next);
					lookup.put(next, 0);
				}
			} else {
				this.stk.pop();
				this.lookup.remove(top);
			}
		}
		
		return null;
	}

}
