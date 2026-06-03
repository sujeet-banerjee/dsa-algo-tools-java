package com.suz.gcd_lcm;

import java.util.ArrayList;

public class TransformToCoprime {

	public TransformToCoprime() {
	}

	public static int gcd(int x, int y) {
		if (y > x) {
			int temp = x;
			x = y;
			y = temp;
		}

//		System.out.println(String.format("GCD(%d, %d) => ", 
//				y, y));

		int divisor = y;
		int dividend = x;

		while (dividend % divisor != 0) {
//			System.out.println(String.format(
//					"dividend: x=%d; divisor: y=%d | quo=%d | rem=%d", 
//					dividend, divisor, 
//					(dividend/divisor), (dividend % divisor)));

			int rem = dividend % divisor;
			dividend = divisor;
			divisor = rem;

//			System.out.println(String.format(
//					"NEW ==> dividend: x=%d; divisor: y=%d | quo=%d | rem=%d", 
//					dividend, divisor, 
//					(dividend/divisor), (dividend % divisor)));
		}
		return divisor;
	}

	public static int lcm(int x, int y) {
		if (y > x) {
			int temp = x;
			x = y;
			y = temp;
		}

		return (x * y) / (gcd(x, y));
	}

	public int[] transform(int[] arr) {

		ArrayList<Integer> temp = new ArrayList<>();
		int j = 0;
		temp.add(arr[j]);
		for (int i = 1; i < arr.length; i++) {
			if (gcd(temp.get(j), arr[i]) > 1) {

				int tx = temp.remove(j);
				temp.add(lcm(tx, arr[i]));

			} else {
				temp.add(arr[i]);
				j++;
			}
		}

		int[] res = new int[temp.size()];

		for (int i = 0; i < temp.size(); i++) {
			res[i] = temp.get(i);
		}

		return res;
	}

	public static void main(String[] args) {

		System.out.println("======== GCD =========");
		System.out.println("Gcd=" + gcd(3, 2));
		System.out.println("Gcd=" + gcd(6, 4));
		System.out.println("Gcd=" + gcd(8, 6));
		System.out.println("Gcd=" + gcd(18, 6));
		System.out.println("Gcd=" + gcd(40, 6));
		System.out.println("");
		System.out.println("Gcd=" + gcd(2, 3));
		System.out.println("Gcd=" + gcd(4, 6));
		System.out.println("Gcd=" + gcd(6, 18));
		System.out.println("Gcd(6, 40) =" + gcd(6, 40));
		System.out.println("Gcd(6, 15) =" + gcd(6, 15));

//		System.out.println("======== LCM =========");
		System.out.println("Gcd=" + lcm(3, 2));
		System.out.println("Gcd=" + lcm(6, 4));
		System.out.println("Gcd=" + lcm(8, 6));
		System.out.println("Gcd=" + lcm(18, 6));
		System.out.println("Gcd=" + lcm(40, 6));
		System.out.println("");
		System.out.println("Gcd=" + lcm(2, 3));
		System.out.println("Gcd=" + lcm(4, 6));
		System.out.println("Gcd=" + lcm(6, 18));
		System.out.println("Gcd=" + lcm(6, 40));
		System.out.println("Gcd(40, 6) =" + gcd(40, 6));
		System.out.println("Gcd(15, 6) =" + gcd(15, 6));

		TransformToCoprime tx = new TransformToCoprime();
		int[] res = tx.transform(new int[] { 6, 4, 3, 2, 7, 3, 1});
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 0; i < res.length; i++) {
			arr.add(res[i]);
		}
		System.out.println("Result: " + arr.toString());
	}
}
