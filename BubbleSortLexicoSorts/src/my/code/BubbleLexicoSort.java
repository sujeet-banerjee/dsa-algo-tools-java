package my.code;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Use Bubble sort to find total swaps.
 * Swap in a way that, each time, the best 
 * lexicographic ordering is obtained with
 * least number of swaps. 
 */
public class BubbleLexicoSort {

	public static int findMinIndex(List<Integer> a, int begin, int size) {
        // TODO validate the inputs, ranges, etc
        if(begin <0 || begin > size-1) {
            throw new IllegalArgumentException("Invalid 'begin'");
        }
        //System.out.println(String.format("FM ==> bg=%d, end=%d", begin, size-1));
        
        int minIndex = begin;
        for(int i=begin; i<size; i++) {
            if(a.get(i) < a.get(minIndex)) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    public static void swap(List<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
    
    public static int bubbleUpMin(List<Integer> a, int left, int right) {
        if(left > right) {
            throw new IllegalArgumentException("Invalid bounds left, right.");
        }
        int numSwaps = 0;
        for(int i=right; i>left; i--) {
            if(a.get(i) < a.get(i-1)) {
                swap (a, i, i-1);
                numSwaps++;
            }
        }
        return numSwaps;
    }
    public static void main(String[] args) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(bufferedReader.readLine().trim());
//        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//            .map(Integer::parseInt)
//            .collect(toList());

        //doBubbleLexicoSort(Arrays.asList(new Integer[] {3,2,1}));
        doBubbleLexicoSort(Arrays.asList(new Integer[] {7,2,5,1,4,9}));
        
//        bufferedReader.close();
    }

	private static void doBubbleLexicoSort(List<Integer> a) {
		int n = a.size();
        int totalSwaps = 0;
        System.out.println("Input List: " + a);
        for(int i=0; i<n; i++) {
            int rightBound = findMinIndex(a, i, n);
            //System.out.println("left, right = "+ i + ", "+ rightBound);
            int numSwaps = bubbleUpMin(a, i, rightBound);
            
            // Optimize as, the array is sorted already.
            if(numSwaps == 0) {
            	break;
            }
            System.out.println("Swaps: " +numSwaps+  " ==> List: " + a);
            totalSwaps += numSwaps;
        }
        
        System.out.println(String.format("Array is sorted in %d swaps.", totalSwaps));
        System.out.println(String.format("First Element: %d", a.get(0)));
        System.out.println(String.format("Last Element: %d", a.get(n-1)));
	}
}
