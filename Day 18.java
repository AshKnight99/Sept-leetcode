/*
Expression Add Operators
Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.

 

Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0*0","0+0","0-0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 

Constraints:

1 <= num.length <= 10
num consists of only digits.
-231 <= target <= 231 - 1
*/
class Solution {
   private static final char[] OPERATORS = new char[]{'+', '-', '*'};

public List<String> addOperators(String num, int target) {
	List<String> ans = new ArrayList();
	bt(ans, new StringBuilder(), num, 0, 0, 0, target);
	return ans;
}

private void bt(List<String> ans, StringBuilder sb,
				String s, int index,
				long store, long output, int target) {
	if (index == s.length()) {
		if (output + store == target) {
			ans.add(sb.toString());
		}
		return;
	}
	int sblen = sb.length();
	// avoid leading `0`
	int len = s.charAt(index) == '0' ? index + 1 : s.length();
	// loop through all possible multi-digits numbers
	for (int i = index + 1; i <= len; i++) {
	    // use `long` to handle integer overflow; if `long` not allow, a simple if-statement to check the overflow condition
		long curNum = Long.valueOf(s.substring(index, i));
		// no operator should be added to the beginning of the expression, according to the problem desc.
		if (sblen == 0) {
			sb.append(curNum);
			bt(ans, sb, s, i, curNum, output, target);
			sb.setLength(sblen);
			continue;
		}
		for (int j = 0; j < OPERATORS.length; j++) {
			sb.append(OPERATORS[j]);
			sb.append(curNum);
			switch (OPERATORS[j]) {
				case '+':
					bt(ans, sb, s, i, curNum, output + store, target);
					break;
				case '-':
					bt(ans, sb, s, i, -curNum, output + store, target);
					break;
				case '*':
				    // only when it's multiplication, we can't add the `store` to `output` just yet
					bt(ans, sb, s, i, store * curNum, output, target);
					break;
			}
			sb.setLength(sblen);
		}
	}
}
}