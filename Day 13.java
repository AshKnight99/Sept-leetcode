/*
Maximum Number of Balloons
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

You can use each character in text at most once. Return the maximum number of instances that can be formed.

 

Example 1:



Input: text = "nlaebolko"
Output: 1
Example 2:



Input: text = "loonbalxballpoon"
Output: 2
Example 3:

Input: text = "leetcode"
Output: 0
 

Constraints:

1 <= text.length <= 104
text consists of lower case English letters only.
*/
class Solution {
    public int maxNumberOfBalloons(String text) {
        HashMap <Character, Integer> map = new HashMap<>();
        char arr[] = text.toCharArray();
        int len = arr.length;
        
        map.put('b', 0);
        map.put('a', 0);
        map.put('l', 0);
        map.put('o', 0);
        map.put('n', 0);
        for(int i = 0; i < len; i++)  {
            switch(arr[i]){
                case 'b':
                case 'a':
                case 'l':
                case 'o':
                case 'n':
                    map.put(arr[i], map.get(arr[i]) + 1);
                    break;
            }
           
        }
        map.put('l', map.get('l') / 2);
        map.put('o', map.get('o') / 2); 
        
        int ans = Integer.MAX_VALUE;
        for(int freq : map.values()) ans = Math.min(ans, freq);
        return ans;   
        
    }
}