/*
Partition to K Equal Sum Subsets
Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false
 

Constraints:

1 <= k <= nums.length <= 16
1 <= nums[i] <= 104
The frequency of each element is in the range [1, 4].
   Hide Hint #1  
We can figure out what target each subset must sum to. Then, let's recursively search, where at each call to our function, we choose which of k subsets the next value will join.
*/
public boolean canPartitionKSubsets(int[] nums, int k) {
	var sum = IntStream.of(nums).sum();
	if (sum % k != 0)
		return false;
	return canPartitionKSubsets(nums, k, 0, 0, sum / k, new boolean[nums.length]);
}

private boolean canPartitionKSubsets(int[] nums, int k, int start, int currentBucketSum, int targetBucketSum, boolean[] used) {
	if (k == 1)
		return true;
	if (currentBucketSum > targetBucketSum)
		return false;
	if (currentBucketSum == targetBucketSum)
		return canPartitionKSubsets(nums, k - 1, 0, 0, targetBucketSum, used);
	// for each choice
	for (var i = start; i < nums.length; i++) {
		if (used[i])
			continue;
		// choose
		used[i] = true;
		// explore
		if (canPartitionKSubsets(nums, k, i + 1, currentBucketSum + nums[i], targetBucketSum, used))
			return true;
		// un-choose
		used[i] = false;
	}
	return false;
}