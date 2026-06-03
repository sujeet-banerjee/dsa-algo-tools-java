/**
 * 
 */
package algo.rand;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 */
public class RandomNumberGenTests {
	public static void main(String args[]) {
		random1();
		System.out.println("----------------");
		random1();
		System.out.println("----------------");
		random2();
		System.out.println("----------------");
		random2();
	}

	private static void random1() {
		// Uses 48 bits
		Random r = new Random(/*System.currentTimeMillis()*/);
		System.out.println("Rand Int1: "+  r.nextInt(200));
		System.out.println("Rand Int1: "+  r.nextInt(200));
		System.out.println("Rand Int1: "+  r.nextGaussian());
	}
	
	private static void random2() {
		// Uses upto 128 bits
		Random r = new SecureRandom();
		System.out.println("Rand Int1: "+  r.nextInt(200));
		System.out.println("Rand Int1: "+  r.nextInt(200));
		System.out.println("Rand Int1: "+  r.nextGaussian());
	}
}
