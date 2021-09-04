/*
Sum of Distances in Tree
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

 

Example 1:


Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:


Input: n = 1, edges = []
Output: [0]
Example 3:


Input: n = 2, edges = [[1,0]]
Output: [1,1]
 

Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree
*/
class Solution {
    class Node {
        int val;

        Node(int v) {
            val = v;
        }

        List<Node> childs = new ArrayList<>();
    }

    Map<Integer, Node> valToNode = new HashMap<>();
    int[] counts;
    int[] sums;
    int n;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 1)
            return new int[] { 0 };
        if (n == 2)
            return new int[] { 1, 1 };
        this.n = n;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int vFrom = edge[0];
            int vTo = edge[1];
            Node from = valToNode.get(vFrom);
            if (from == null) {
                from = new Node(vFrom);
                valToNode.put(vFrom, from);
            }
            Node to = valToNode.get(vTo);
            if (to == null) {
                to = new Node(vTo);
                valToNode.put(vTo, to);
            }
            from.childs.add(to);
            to.childs.add(from);
        }
        counts = new int[n];
        Arrays.fill(counts, 1);
        sums = new int[n];
        dfsOneRoot(valToNode.get(0), -1);
        dfsChangeRoot(valToNode.get(0), -1);
        return sums;
    }

    void dfsChangeRoot(Node node, int visitedNode) {
        for (Node child : node.childs) {
            if (child.val != visitedNode) {
                sums[child.val] = sums[node.val] + n - 2 * counts[child.val];
                dfsChangeRoot(child, node.val);
            }
        }
    }

    void dfsOneRoot(Node node, int visitedNode) {
        for (Node child : node.childs) {
            if (child.val != visitedNode) {
                dfsOneRoot(child, node.val);
                counts[node.val] += counts[child.val];
                sums[node.val] += sums[child.val] + counts[child.val];
            }
        }
    }
}