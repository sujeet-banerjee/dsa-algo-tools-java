package my.code.arrays;

import java.util.Arrays;

public class MaxPenalty {
	
	public static int calculateMaxPanalty(int[] nums) {
		if(nums == null) {
			throw new IllegalArgumentException("");
		}
		int size = nums.length;
		
		if(size==1) {
			return nums[0];
		} 
		
		if(size==2) {
			return nums[0] + nums[1];
		}
		
		int penalty = 0;
		Arrays.sort(nums);
		int lastElm = nums[size-1];
		for(int i=size-2; i>=0; i--) {
			lastElm += nums[i];
			System.out.println("Elm: "+ nums[i]);
			System.out.println("Last-Elm: "+ lastElm);
			penalty += lastElm;
		}
		
		return penalty;
	}
	
	public static void main(String[] args) {
		int res = calculateMaxPanalty(new int[] {
			1,2,4,3	
		});
		
		System.out.println("Penalty = "+ res);
		
		res = calculateMaxPanalty(new int[] {
				2, 3, 9, 8, 4	
			});
			
		System.out.println("Penalty = "+ res);
	}

}
