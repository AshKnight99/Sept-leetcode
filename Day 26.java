/*
Transform to Chessboard
You are given an n x n binary grid board. In each move, you can swap any two rows with each other, or any two columns with each other.

Return the minimum number of moves to transform the board into a chessboard board. If the task is impossible, return -1.

A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.

 

Example 1:


Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
Output: 2
Explanation: One potential sequence of moves is shown.
The first move swaps the first and second column.
The second move swaps the second and third row.
Example 2:


Input: board = [[0,1],[1,0]]
Output: 0
Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
Example 3:


Input: board = [[1,0],[1,0]]
Output: -1
Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.
 

Constraints:

n == board.length
n == board[i].length
2 <= n <= 30
board[i][j] is either 0 or 1.
*/
class Solution {
    public int movesToChessboard(int[][] board) {
        int N = board.length, colMovesNeeded = 0, rowMovesNeeded = 0, noOfOnesInFirstRow = 0, noOfOnesInFirstCol = 0;
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(((board[0][0] ^ board[i][0]) ^ (board[i][j] ^ board[0][j])) == 1){
                    return -1;
                }
            }
        }
        
        for(int i = 0; i < N; i++){
            noOfOnesInFirstRow += board[0][i];
            noOfOnesInFirstCol += board[i][0];
            //find max swap -> for worst case;
            if(board[i][0] == i % 2){
                rowMovesNeeded++;
            }
            if(board[0][i] == i % 2){
                colMovesNeeded++;
            }
        }
        if(noOfOnesInFirstRow < N / 2 || noOfOnesInFirstRow > (N + 1) / 2){
            return -1;
        }
        if(noOfOnesInFirstCol < N / 2 || noOfOnesInFirstCol > (N + 1) / 2){
            return -1;
        }
        if (N % 2 == 1) {
            if (colMovesNeeded % 2 == 1) colMovesNeeded = N - colMovesNeeded;
            if (rowMovesNeeded % 2  == 1) rowMovesNeeded = N - rowMovesNeeded;
        }
        else {
            colMovesNeeded = Math.min(N - colMovesNeeded, colMovesNeeded);
            rowMovesNeeded = Math.min(N - rowMovesNeeded, rowMovesNeeded);
        }
        return (colMovesNeeded + rowMovesNeeded) / 2;
    }
}
