/*
Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        
        if(matrix.length == 0){
            return list;
        }
        int startRow = 0;
        int endRow = matrix.length - 1;
        int startColumn = 0;
        int endColumn = matrix[0].length - 1;
        
        while(startRow <= endRow && startColumn <= endColumn){
            //right
            for(int c = startColumn; c <= endColumn; c++){
                list.add(matrix[startRow][c]);
            }
            startRow += 1;
            
            //down
            
            for(int r = startRow; r <= endRow; r++){
                list.add(matrix[r][endColumn]);
            }
            endColumn -= 1;
            
            //left
            if(startRow <= endRow){
                
                for(int c = endColumn; c >= startColumn; c--){
                list.add(matrix[endRow][c]);
            }
            endRow -= 1;
            }
            
            //up
            if(startColumn <= endColumn){
                
                for(int r = endRow; r >= startRow; r--){
                list.add(matrix[r][startColumn]);
            }
            startColumn += 1;
            }
            
        }
        
        return list;
        
    }
}