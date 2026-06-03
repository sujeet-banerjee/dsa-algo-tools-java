package com.suz.string.print;

/**
 * Kept doing blunders.
 * Need to:
 *  - keep cool
 *  - work all cases in the notebook - do not hurry!
 *  - add comments, so you don't confuse or back and forth
 */
public class PrintSpaceDelimitedStrings {
	public static void main(String[] args) {
		String str = "  You   are    so    great!  ";
		System.out.println("[");
		printString(str);
		System.out.println("]");
	}
	
	public static void printString(String str) {
		if(str==null || str.isEmpty()) {
			return;
		}
		
		int size = str.length();
		char[] chars = str.toCharArray();
		if(size==1) {
			if(chars[0] == ' ') {
				return;
			}
			
			System.out.println(str);
		}
		
		int start = 0;
		int cur = 1;
		
		while(start < size && cur < size) {
			if(chars[cur] == ' ') {
				// Hit SPACE!
				if(chars[cur-1] != ' ') {
					// print
					//System.out.println(">>>>> Printing:");
					System.out.println(
							String.valueOf(chars, start, cur-start
					));
					
				} 
			} else {
				// We hit CHAR!
				if(chars[cur-1] == ' ') {
					start = cur;
				}
			}
			
			cur++;
		}
	}
}
