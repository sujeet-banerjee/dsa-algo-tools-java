package com.suz.tree.nestedint;
import java.util.List;
import java.util.ArrayList;

// TODO make immutable
public class NestedInt {
	protected int val;
	protected List<NestedInt> children;

	public NestedInt(int val) {
		this.val = val;
	}
	
	public NestedInt(List<NestedInt> children) {
		this.children = children;
	}
	
	public boolean isNested() {
		return this.children != null && !this.children.isEmpty();
	}
	
	public String toString() {
		if(!isNested()) {
			return ""+this.val;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		
		boolean isFirst = true;
		for(NestedInt child: this.children) {
			if(!isFirst) {
				sb.append(','); 
			}
			isFirst = false;
			sb.append(child.toString());
		}
		
		sb.append(']');
		return sb.toString();
	}
	
//	public static NestedInt valueOf(String str) {
//		if(str==null || str.isBlank()) {
//			throw new IllegalArgumentException("Input string null!");
//		}
//		
//		char[] chars = str.toCharArray();
//		
//	}
}
