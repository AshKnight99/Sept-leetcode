/*
Largest Plus Sign
You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.

Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.

An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.

 

Example 1:


Input: n = 5, mines = [[4,2]]
Output: 2
Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
Example 2:


Input: n = 1, mines = [[0,0]]
Output: 0
Explanation: There is no plus sign, so return 0.
 

Constraints:

1 <= n <= 500
1 <= mines.length <= 5000
0 <= xi, yi < n
All the pairs (xi, yi) are unique.
*/
class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
     int [][] arr = new int[n][n];
        int max = 0;
        
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = 1;
            }
        }
        
        for(int[] i : mines)
        {
            arr[i[0]][i[1]] = 0;
        }
        
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                if(arr[i][j] != 0)
                {
                    int cur = 1;
                    int x = i;
                    int y = j;
                    while(x - cur >= 0 && x + cur < arr.length && y - cur >= 0 && y + cur <= arr[i].length - 1)
                    {
                        if(arr[x + cur][y] == 1 && arr[x - cur][y] == 1 && arr[x][y + cur] == 1 && arr[x][y - cur] == 1)
                            cur++;
                        else
                            break;
                    }
                    max = Math.max(cur, max);
                }
            }
        }
        return max;
    }
}