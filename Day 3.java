/*
Erect the Fence
You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.

You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.

Return the coordinates of trees that are exactly located on the fence perimeter.

 

Example 1:


Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
Example 2:


Input: points = [[1,2],[2,2],[4,2]]
Output: [[4,2],[2,2],[1,2]]
 

Constraints:

1 <= points.length <= 3000
points[i].length == 2
0 <= xi, yi <= 100
All the given points are unique.
*/
class Solution {
       public int orientation(int p[],int q[],int r[]){
        return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
    }

    public int distance(int p[],int q[]){
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    public int[] bottomLeft(int[][] trees) {

        int[] bottomleft = trees[0];
        for(int[] p : trees){

            if(p[1] < bottomleft[1])
                bottomleft = p;
        }
        return bottomleft;
    }

    public int[][] outerTrees(int[][] trees){

        if(trees.length <= 1)
            return trees;
        int[] bm = bottomLeft(trees);
        Arrays.sort(trees, new Comparator<int[]>(){

            public int compare(int p[], int q[]){
                double diff = orientation(bm, p, q) - orientation(bm, q, p);
                if(diff == 0)
                    return distance(bm, p) - distance(bm, q);
                else
                    return diff > 0 ? 1 : -1;
            }
        });

        int i = trees.length - 1;
        while(i >= 0 && orientation(bm, trees[trees.length - 1], trees[i]) == 0)
            i--;
        
        for(int l = i + 1, h = trees.length - 1; l < h; l++, h--){
            int temp[] = trees[l];
            trees[l] = trees[h];
            trees[h] = temp;
        }
        
        Stack<int[]> stack = new Stack< > ();
        stack.push(trees[0]);
        stack.push(trees[1]);
        
        for (int j = 2; j < trees.length; j++) {
            int[] top = stack.pop();
            while (orientation(stack.peek(), top, trees[j]) > 0)
                top = stack.pop();
            stack.push(top);
            stack.push(trees[j]);
        }

        return stack.toArray(new int[stack.size()][]);
    }
}