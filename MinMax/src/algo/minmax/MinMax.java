package algo.minmax;

import java.util.List;
import java.util.ArrayList;
import algo.minmax.Pair;
import java.lang.Comparable;

public class MinMax<T extends Comparable<T>> {
	
	// Returns a Pair, with first element being min, 
	// and the second being max.
	public Pair<T, T> findMinMax (List<T> list) {
		if(list == null) {
			throw new IllegalArgumentException("Input list cannot be null");
		} 
		
		if(list.size() < 2) {
			throw new IllegalArgumentException("At least two elements required in the input list");
		}
		
		Pair<T, T> ret = null;
		
		if(list.size() == 2) {
			ret = reorganizeThePair(list.get(0), list.get(1));
			return ret;
		}
		
		ret = reorganizeThePair(list.get(0), list.get(1));
		
		// need to handle if the size of the array is odd
		for(int i=2; i<list.size(); i += 2) {
			// Initialize
			T min = list.get(i);
			T max = i+1 < list.size() ? list.get(i+1) : min;
			
			if(min.compareTo(max) > 0) {
				// swap
				min = max;
				max = list.get(i);
			}
			
			if(min.compareTo(ret.first()) < 0) {
				ret.setFirst(min);
			}
			
			if(max.compareTo(ret.second()) > 0) {
				ret.setSecond(max);
			}
		}
		
		return ret;
	}
	
	
	public Pair<T, T> reorganizeThePair(T first, T second) {
		if(first.compareTo(second) < 0) {
			return new Pair(first, second);
		} else {
			return new Pair(second, first);
		}
	}
	
	
	public static void main(String args[]) {
		List<Integer> input = new ArrayList<>();
		input.add(8);
		input.add(13);
		input.add(5);
		input.add(1);
		input.add(21);
		
		Pair<Integer, Integer> res = new MinMax().findMinMax(input);
		
		System.out.println("Min = "+ res.first());
		System.out.println("Max = "+ res.second());
		
	}
}