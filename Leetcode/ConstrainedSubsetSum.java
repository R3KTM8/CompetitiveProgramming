
import java.util.*;

public class ConstrainedSubsetSum {

	// https://leetcode.com/problems/constrained-subsequence-sum/
	
	public static void main(String[] args) {
		int[] a = new int[] {-1,-2,-3};
		System.out.println(constrainedSubsetSum(a,2));
	}

	public static int constrainedSubsetSum(int[] nums, int k) {
        int[] dp = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
        	dp[i] = nums[i];
        }
        PriorityQueue<int[]> a = new PriorityQueue<>(
        		new Comparator<int[]>() {
        			public int compare(int[] x, int[] y) {
        				return y[0]-x[0];	// sort by largest first
        			}
        });
        
        int[] temp = new int[] {dp[0], 0};
        a.add(temp);
        
        for (int i = 1; i < nums.length; i++) {
    		while (a.size() > 0) {
    			if (a.peek()[1]  < i-k) a.poll();
    			else break;
    		} 
    		dp[i] = Math.max(dp[i], nums[i] + a.peek()[0]);
    		int[] temp2 = new int[] {dp[i], i};
    		a.add(temp2);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
        	max = Math.max(max, dp[i]);
        	
        }
        return max; 
    }
	
}

/*
	TLE
	
	int[] dp = new int[nums.length];
	for (int i = 0; i < nums.length; i++) dp[i] = nums[i];
	for (int i = 1; i <= k; i++) {
		for (int j = 0; j < i; j++) {
			dp[i] = Math.max(dp[i], dp[j]+nums[i]);
		}
	}
	for (int i = k+1; i < nums.length; i++) {
		for (int j = i-k; j < i; j++) {
			dp[i] = Math.max(dp[i], dp[j]+nums[i]);
		}
		
	}
	int max = Integer.MIN_VALUE;
	for (int i = 0; i < dp.length; i++) {
		max = Math.max(max, dp[i]);
		
	}
	return max; 
*/
