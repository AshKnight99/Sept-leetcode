/*
Distinct Subsequences
Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It is guaranteed the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
*/
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
		// Stores no of ways to obtain first j characters of t from first i charaters of s
        int[][] dp = new int[m][n]; 
        
		// Number of ways to obtain t[0], from first i characters of s.
		// which will be number of occurances of t[0], in first i characters of s.
        for(int i = 0; i < m; i++){
            if(s.charAt(i) == t.charAt(0)) dp[i][0] =1;
            if(i > 0) dp[i][0] += dp[i - 1][0];
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
			   // If number of characters in s is less than number of characters in t, then its not achievable.
                if(i < j) dp[i][j] = 0;
                else{
				   //if i th character of s is not equal to jth character of t, then we need to obtain first j characters of t from first i-1 characters of s
                    dp[i][j] = dp[i - 1][j];
                    //if i th character of s is equal to jth character of t, then we need to obtain first j-1 characters of t from first i-1 characters of s
					if(s.charAt(i) == t.charAt(j)) dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}