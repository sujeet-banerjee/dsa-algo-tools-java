package com.suz.bitwise;

import java.util.Scanner;

//Demonstrating the bitwise logical operators
class Geeks {
	public static void main(String[] args) {
//		testBItWiseBinaryOps();
		testBinaryShifts();
	}

	private static void testBItWiseBinaryOps() {
		String binary[] = { "0000", "0001", "0010", "0011", 
				"0100", "0101", "0110", "0111", 
				"1000", "1001", "1010",
				"1011", "1100", "1101", "1110", "1111" };

		// initializing the values of a and b
		int a = 3; // 0+2+1 or 0011 in binary
		int b = 6; // 4+2+0 or 0110 in binary

		// bitwise or
		int c = a | b;

		// bitwise and
		int d = a & b;

		// bitwise xor
		int e = a ^ b;

		// bitwise not
		int g = ~a & 0x0f;
		
		// This is same as Ex-OR (~)
		int f = (~a & b) | (a & ~b);
		

		System.out.println(" a= " + binary[a]);
		System.out.println(" b= " + binary[b]);
		System.out.println(" a|b= " + binary[c]);
		System.out.println(" a&b= " + binary[d]);
		System.out.println(" a^b= " + binary[e]);
		System.out.println("~a & b|a&~b= " + binary[f]);
		System.out.println("~a= " + binary[g]);
	}
	
	public static void testBinaryShifts() {
	
		// Raw tests
		int a = 16;
		int b = a >>> 1;
		System.out.println(String.format(
				"a=%d (%s) (%d) ; b=%d (%s) (%d)", 
				a, Integer.toBinaryString(a), Integer.toBinaryString(a).length(),
				b, Integer.toBinaryString(b), Integer.toBinaryString(b).length()
		));
		
		a = -16;
		b = a >>> 1;
		System.out.println(String.format(
				"a=%d (%s) (%d) ; b=%d (%s) (%d)", 
				a, Integer.toBinaryString(a), Integer.toBinaryString(a).length(),
				b, Integer.toBinaryString(b), Integer.toBinaryString(b).length()
		));
		
		a = -16;
		b = a >> 1;
		System.out.println(String.format(
				"a=%d (%s) (%d) ; b=%d (%s) (%d)", 
				a, Integer.toBinaryString(a), Integer.toBinaryString(a).length(),
				b, Integer.toBinaryString(b), Integer.toBinaryString(b).length()
		));
        
//		Scanner input = new Scanner(System.in);
//        System.out.print("Enter first number: ");
//        int num1 = input.nextInt();
//
//        System.out.print("Enter second number: ");
//        int num2 = input.nextInt();
//
//        System.out.println("Bitwise AND: " + (num1 & num2));
//        System.out.println("Bitwise OR: " + (num1 | num2));
//        System.out.println("Bitwise XOR: " + (num1 ^ num2));
//        System.out.println("Bitwise NOT: " + (~num1));
//        System.out.println("Bitwise Left Shift: " + (num1 << 2));
//        System.out.println("Bitwise Right Shift: " + (num1 >> 2));
//        System.out.println("Bitwise Unsigned Right Shift: " + (num1 >>> 2));
//
//        input.close();
	}
}
