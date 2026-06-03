package com.suz.diagprint;


public class DiagPrintMeta {
	/*
	 * LEARNINGS:
	 * (for correct code signals)
	 * - Always make the pgm modular, define methods with name indicating purpose.
	 * - Always choose long variable names that indicate the purpose.
	 * - Avoid back and forth (if live interview), move ahead for now, 
	 *   but come back to fix it later.
	 *   
	 * BLUNDERS:
	 * - pay attention to the direction of i and/or j indices.
	 * - pay attention: ensure you are not dup printing a diagonal
	 * 
	 */
	
	public static int[][] matrix = new int[][] {
		{ 3,  4,  5,  6,  7,  8},
		{13, 14, 15, 16, 17, 18},
		{23, 24, 25, 26, 27, 28},
		{33, 34, 35, 36, 37, 38},
	};
	
	public static void main(String[] args) {
		printMatrixDiagonally(matrix);
	}
	
	public static void printMatrixDiagonally(int[][] m) {
		if(m == null) {
			return;
		}
		
		int numRows = m.length;
		if(numRows == 0) {
			return;
		}
		
		int numCols = m[0].length;
		if(numCols == 0) {
			return;
		}
		
		System.out.println("Printing matrix " + numRows + ", "+ numCols);
		
		for(int j=0; j<numCols; j++) {
			printDiag(m, numRows, numCols, 0, j);
		}
		
		int lastCol = numCols-1;
		for(int i=1; i<numRows; i++) {
			printDiag(m, numRows, numCols, i, lastCol);
		}
	}
	
	public static void printDiag(int[][] m, int numRows,
			int numCols, int row, int col) {
		int i=row;
		int j=col;
		
		while(i<numRows && j>=0) {
			System.out.print(String.format("%d, ",
					m[i++][j--]));
		}
		System.out.println();
	}

}
