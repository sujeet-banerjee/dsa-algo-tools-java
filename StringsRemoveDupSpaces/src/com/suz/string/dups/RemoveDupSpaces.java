package com.suz.string.dups;

public class RemoveDupSpaces {
	
	public static void main(String[] args) {
		
		String tape = "   one  two   three    four     ";
		
		String str = removeAllSpaces(tape);
		System.out.println("Str:["+ str);
		
		str = removeAllButOneSpaces(tape, 2);
		System.out.println("Str2:["+ str);
	}
	
	/** 
	 * //Step1:
	 * Easiest (start with this)
	 * Then gradually add on more complicated cases
	 * @param tape
	 * @return
	 */
	public static String removeAllSpaces(String tape) {
		if(tape==null || tape.isEmpty()) {
			return "";
		}
		
		if(tape.length()==1) {
			return tape.charAt(0) != ' ' ? tape : "";
		}
		
		int size = tape.length();
		int index = 0;
		int falsePosition = index;
		char[] chars = tape.toCharArray();
		while(index < size && falsePosition < size) {
			if(chars[index] != ' ') {				
				chars[falsePosition++] = chars[index++];
				
			} else {
				System.out.println(String.format(
					"(f,i)=(%d, %d); REPL (%s, %s)", 
					falsePosition, index, 
					chars[falsePosition], chars[index]
				));
				index++;
			}
		}
		
		return String.valueOf(chars, 0, falsePosition);
	}
	
	/**
	 * //Step2:
	 * Allows delimiting spaces only up to 'numSpacesAllowed'
	 * FIXME bugs:
	 * Remove leading and trailing spaces
	 * 
	 * @param tape
	 * @param numSpacesAllowed
	 * @return
	 */
	public static String removeAllButOneSpaces(String tape,
			int numSpacesAllowed) {
		if(tape==null || tape.isEmpty()) {
			return "";
		}
		
		if(tape.length()==1) {
			return tape.charAt(0) != ' ' ? tape : "";
		}
		
		int size = tape.length();
		int index = 0;
		int falsePosition = index;
		char[] chars = tape.toCharArray();
		
		int spacesSoFar=0;
		while(index < size && falsePosition < size) {
			if(chars[index] != ' ') {				
				chars[falsePosition++] = chars[index++];
				spacesSoFar = 0;
			} else {
				spacesSoFar++;
				if(spacesSoFar <= numSpacesAllowed) {
					chars[falsePosition++] = chars[index++];
				} else {
					index++;
				}
			}
		}
		
		return String.valueOf(chars, 0, falsePosition);
	}
}
