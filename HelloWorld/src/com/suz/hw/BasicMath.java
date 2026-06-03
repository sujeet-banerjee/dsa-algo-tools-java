package com.suz.hw;

public class BasicMath {

	public static long MAX_VOLUME = 1000000L;
	public static int MAX_SIZE = 10000;
	
	public BasicMath() {
	}
	
	public String solve(int w, int l, int h, int m) {
		
		boolean isLarge = isLarge(w, l, h);
		boolean isBig = isBig(m);
		
		System.out.println(String.format(
				" l=%d, w=%d, h=%d, m=%d| isLarge: %s | isBig: %s",
				l, w, h, m,
				isLarge, isBig
				));
		
		if(isLarge && isBig) {
			return "Both";
		} else if(isLarge) {
			return "Large";
		} else if(isBig) {
			return "Big";
		} 
		
		return "Neither";
	}
	
	public boolean isBig(int m) {
		// Silly mistake: missed >=
		return m >= 100;
	}
	
	public boolean isLarge(int w, int l, int h) {
		if(l >= MAX_SIZE || w >= MAX_SIZE || h >= MAX_SIZE) {
			return true;
		}
		
		// OR,
		// Silly COmpile error, because, I wrote 'b' instead of 'w' in hurry!
		long volume = (long) (l * w * h);
		return volume >= MAX_VOLUME;
	}
	
	public static void main(String args[]) {
		// Big
		System.out.println(
				new BasicMath().solve(100, 10, 100, 100));
		System.out.println(
				new BasicMath().solve(100, 100, 10, 1000));
		
		// Large
		System.out.println(
				new BasicMath().solve(10000, 10, 1, 10));
		System.out.println(
				new BasicMath().solve(1, 10000, 10, 10));
		System.out.println(
				new BasicMath().solve(10, 1, 10000, 10));
		
		// Large-2
		System.out.println(
				new BasicMath().solve(100000, 100, 100, 10));
		System.out.println(
				new BasicMath().solve(100000, 100000, 100, 10));
		System.out.println(
				new BasicMath().solve(1, 1, 1000000, 10));
		
		// Large-3
		System.out.println(
				new BasicMath().solve(100, 100, 100, 10));
		// Both
		System.out.println(
				new BasicMath().solve(100, 100, 100, 100));
		
		// Neither
		System.out.println(
				new BasicMath().solve(99, 100, 100, 99));
	}

}
