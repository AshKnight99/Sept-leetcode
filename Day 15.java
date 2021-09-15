/*
Longest Turbulent Subarray
Given an integer array arr, return the length of a maximum size turbulent subarray of arr.

A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:

For i <= k < j:
arr[k] > arr[k + 1] when k is odd, and
arr[k] < arr[k + 1] when k is even.
Or, for i <= k < j:
arr[k] > arr[k + 1] when k is even, and
arr[k] < arr[k + 1] when k is odd.
 

Example 1:

Input: arr = [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
Example 2:

Input: arr = [4,8,12,16]
Output: 2
Example 3:

Input: arr = [100]
Output: 1
 

Constraints:

1 <= arr.length <= 4 * 104
0 <= arr[i] <= 109
*/
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int[] cache = new int[arr.length];
        for(int i = 0;i < arr.length - 1;i++) {
            cache[i] = arr[i] - arr[i + 1] > 0 ? 1 : arr[i] - arr[i + 1] == 0 ? 0 : -1;
        }
        int res = 1, ind = 0, n = arr.length, curr = cache[0], count = 1;
        for(int i = 1; i < n;i++) {
            if(cache[i] * curr == -1) {
                count++;
                res = Math.max(count,res);
            } else if(cache[i] * curr == 0) {
                if(curr != cache[i])
                res = Math.max(count + 1,res);
                else res = Math.max(count,res);
                count = 1;
            } 
            else {
                res = Math.max(count + 1,res);
                count = 1;
            }
            curr = cache[i];
        }
        
        return res;
    }
}