
public class ArrayTests {
	
	class MyException extends Exception {
		
	}
	
	// contructors
	public ArrayTests() {
	}
	
	public void test() throws MyException {
		// reference
		int arr[];
		// create an object of Array of int.
		arr = new int[10];
		
		MyException ex = new MyException();
		throw ex;
		
		//System.out.println(arr.toString());
	}
	
	// String
	
	public void arrayOfString() {
		// reference
		String myStr[];

		myStr = new String[10];		
		
		myStr[0] = "Hi";
		myStr[1] = "Hello";
		
		System.out.println(myStr.toString());
	}
	
	public void arrayOfInteger() {
		// reference
		Integer myIntgs[];

		/*
		 * int ==> 4 bytes
		 * Object Integer ==> 12 bytes
		 */
		myIntgs = new Integer[10];
		// new Integer(10);
		// myIntgs = new Integer(10);
		
		myIntgs[0] = new Integer(10);
		myIntgs[1] = Integer.valueOf(12);
		myIntgs[2] = 14;
		
		//Integer intRef = 55;
		
		System.out.println(myIntgs.toString());
	}
	
	public static void main(String[] args) {
		ArrayTests someTests = new ArrayTests();
		someTests.test();
	}

}
