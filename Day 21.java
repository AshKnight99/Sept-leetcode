/*
Max Consecutive Ones
Given a binary array nums, return the maximum number of consecutive 1's in the array.

 

Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
*/
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int isOne = 0;
        int max = 0;
        for(int n : nums){
            if(n == 1){
               isOne += 1;
               max = Math.max(max, isOne);
            }else{
                isOne = 0;
            }
        }
        return max;
    }
}