package com.suz.graph.trials.nestedInt;

public class NestedInt {
	private NestedInt[] children;
	private int value;
	private final boolean isNested;
	public NestedInt(final boolean isNested, 
			final int value, 
			final NestedInt []children) {
		this.isNested = isNested;
		if(this.isNested) {
			if(children.length == 0) {
				throw new IllegalArgumentException(
						"Children with isNested==true cannot be empty!");
			}
			this.children = new NestedInt[children.length];
			System.arraycopy(children, 0, 
					this.children, 0, children.length);
		} else {
			this.value = value;
		}
	}
	
	public final int value() {
		return this.value;
	}
	
	public final NestedInt[] getNested() {
		// FIXME security: return a copy for immutability
		return this.children;
	}
	
	public final boolean isNested() {
		return this.isNested;
	}
}
