/*
Intersection of Two Arrays II
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

 

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000
 

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
*/
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> a = new HashMap<>();
        HashMap<Integer, Integer> b = new HashMap<>();
        int i;
        ArrayList<Integer> arr = new ArrayList<>();
        for(i = 0; i < nums1.length; i++) {
            if(a.containsKey(nums1[i])) {
                int val = a.get(nums1[i]);
                a.replace(nums1[i], val + 1);
            } else {
                a.put(nums1[i], 1);
            }
        }
        for(i = 0; i < nums2.length; i++) {
            if(b.containsKey(nums2[i])) {
                int val = b.get(nums2[i]);
                b.replace(nums2[i], val + 1);
            } else {
                b.put(nums2[i], 1);
            }
        }
        int c = 0;
        for(Integer j: a.keySet()) {
            if(b.containsKey(j)) {
                int min = Math.min(b.get(j), a.get(j));
                addElement(min, j, arr);
            }
        }
        int[] output = new int[arr.size()];
        for(i = 0; i < arr.size(); i++) {
            output[i] = arr.get(i);
        }
        return output;
    }
    public static void addElement(int n, int ele, ArrayList<Integer> arr) {
        for(int i = 0; i < n; i++) {
            arr.add(ele);
        }
    }
}