/*
Maximum Length of a Concatenated String with Unique Characters
Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
*/
class Solution {
    int max = 0;
    public int maxLength(List<String> arr) {
        solve(arr, "", 0, arr.size());
        return max;
    }
    public void solve(List<String> arr, String prev, int index, int n){
        for(int i = index; i < n; i++){
            if(isPossible(prev, arr.get(i))){
                max = Math.max(max, prev.length() + arr.get(i).length());
                solve(arr, prev + arr.get(i), i + 1, n);
            }
        }
    }
    public boolean isPossible(String s1,String s2){
        int num1 = 0, num2 = 0;
        for(char ch : s1.toCharArray()){
            if((num1 & (1 << (ch - 'a')) ) != 0)return false;
            num1 = num1 | (1 << (ch - 'a')); 
        }
        for(char ch : s2.toCharArray()){
            if((num2 & (1 << (ch - 'a')) ) != 0)return false;
            num2 = num2 | (1 << (ch - 'a')); 
        }
        return (num1 & num2) == 0;
    }
}