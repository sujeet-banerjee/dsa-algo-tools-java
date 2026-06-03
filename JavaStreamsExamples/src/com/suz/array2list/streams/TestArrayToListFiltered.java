package com.suz.array2list.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestArrayToListFiltered {
	
	public static void main(String[] args) {
		String[] genes = new String[] {
			"AAA", "ACT", "GAC", "CAG", "TAG", "CAT", "CCC", "TTT"
		};
		
		toListExample(genes);
		
		toArrayStreamExample(genes);
	}

	private static void toArrayStreamExample(String[] genes) {
		List<String> ll = Arrays.stream(genes).filter(
				s -> isCAT(s)
				)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
		System.out.println("Via Arrays as Stream: "+ ll);
	}
	
	public static boolean isCAT(String g) {
		return g.equals("CAT");
	}

	private static void toListExample(String[] genes) {
		// Plain vanilla works
		// List<String> gList = Arrays.asList(genes);
		
		List<String> gList = Arrays.asList(genes).stream()
				.filter( s -> isCAT(s) )
				// This is Java's stupidity!
				//.toList() does not work!
				.collect(Collectors.toList());
		System.out.println("to LIst: "+ gList);
	}

}
