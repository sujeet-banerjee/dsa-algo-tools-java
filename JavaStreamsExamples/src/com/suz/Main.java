package com.suz;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int[] arrayInts = new int[] {
				1,2,3,4,5
		};
		
		int sum = Arrays.stream(arrayInts).filter(x -> {return x > 2;}).sum();
		// That is same as this:
		sum = Arrays.stream(arrayInts).filter(x -> (x > 2)).sum();
		System.out.println("SUM 3,4,5: "+ sum);
		
		
		int productAsReduction = Arrays.stream(arrayInts).
				// 1 is the identity for the product operation
				reduce(1, (x, p) -> (x*p) );
		System.out.println("PROD 1,2,3,4,5: "+ productAsReduction);
		
		int productAsMapReduce = Arrays.stream(arrayInts).
				map( x -> (x * 10)).
				// 1 is the identity for the product operation
				reduce(1, (x, p) -> (x*p) );
		System.out.println("PROD Map Reduce 1,2,3,4,5: "+ productAsMapReduce);
		
		List<int[]> intList = Arrays.asList(arrayInts);
		//intList.stream().map(x -> (x * 10)).reduce(1, (x, p) -> (x*p));
	}

}
