package com.suz.ds.tries;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Trie {
	private Trie[] kids = new Trie[26];
	private String eow;

	public Trie() {
		
	}
	
	public void insert(String str) {
		if(str == null || str.isEmpty()) {
			this.eow = "";
			return;
		}
		
		char[] chars = str.toCharArray();
		Trie current = this;
		for(char next: chars) {
			Trie kid = current.kids[next - 'a'];
			if(kid == null) {
				kid = current.kids[next - 'a'] = new Trie();
				//kid = this.kids[next - 'a'];
			}
			
			current = kid;
		}
		
		current.eow = str;
	}
	
	public String toString(String indent) {
		StringBuilder sb = new StringBuilder("[");
		AtomicInteger index = new AtomicInteger(0);
		Arrays.stream(this.kids).forEach( x -> {
			char ch = (char)((int)'a' + index.intValue());
			if(x !=null) {
				sb.append(indent+"\n");
				sb.append(indent+ch);
				sb.append(":");
				sb.append(x.toString(indent+"    "));
			}
			index.incrementAndGet();
		});
		if(this.eow!=null) {
			sb.append("\n"+indent+"EOW="+this.eow);
		}
		sb.append("\n"+indent+"]");
		
		return sb.toString();
	}
	
	public String toString() {
		return this.toString("");
	}
	
	public String toString2() {
		StringBuilder sb = new StringBuilder("[");
		AtomicInteger index = new AtomicInteger(0);
		Arrays.stream(this.kids).forEach( x -> {
			char ch = (char)((int)'a' + index.intValue());
			if(x !=null) {
				sb.append(", ");
				sb.append(ch);
				sb.append(":");
				sb.append(x.toString());
			}
			index.incrementAndGet();
		});
		sb.append("]");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Trie root = new Trie();
		root.insert("at");
		root.insert("attend");
		root.insert("attorney");
		root.insert("an");
		root.insert("ant");
		root.insert("antenna");
		root.insert("antelope");
		
		System.out.println("Root: "+ root);
	}

}
