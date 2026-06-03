package com.suz.tree.nestedint;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// [1,[2,3],4,[[5]]]
		NestedInt root = new NestedInt(new ArrayList<>());
		
		NestedInt ni1 = new NestedInt(1);
		root.children.add(ni1);
		
		NestedInt subNI = new NestedInt(new ArrayList<>());
		NestedInt ni2 = new NestedInt(2);
		NestedInt ni3 = new NestedInt(3);
		subNI.children.add(ni2);
		subNI.children.add(ni3);
		root.children.add(subNI);
		
		NestedInt ni4 = new NestedInt(4);
		root.children.add(ni4);
		
		NestedInt ni5 = new NestedInt(5);
		NestedInt nestedNi5 = new NestedInt(new ArrayList<>());
		NestedInt subNestedNi5 = new NestedInt(new ArrayList<>());
		subNestedNi5.children.add(ni5);
		nestedNi5.children.add(subNestedNi5);
		root.children.add(nestedNi5);
		
		System.out.println(root.toString());
		
		
		NestedIntIterator itr = new NestedIntIterator(root);
		for(int i=0; i<5; i++) {
			System.out.println("hasNext= "+ itr.hasNext());
			System.out.println("N= "+ itr.next());
		}
	}

}
