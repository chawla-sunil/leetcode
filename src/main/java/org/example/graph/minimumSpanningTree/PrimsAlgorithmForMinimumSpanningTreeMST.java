package org.example.graph.minimumSpanningTree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithmForMinimumSpanningTreeMST {
//    Spanning Tree: If a graph has n vertices,
//    any spanning tree for it will always have exactly n - 1 edges.
//    A spanning tree is a subset of a connected graph that includes all
//    its vertices using the minimum number of edges without forming any loops or cycles.

//    MST: Minimum Spanning Tree: is a spanning tree with weight less than or equal to the weight of every other spanning tree.
//    YouTube video for explanation: https://www.youtube.com/watch?v=ZSPjZuZWCME&list=PLgUwDviBIf0rGEWe64KWas0Nryn7SCRWw&index=25

//    Find the MST and its sum.

    // graph is like this,  => [neighbor, weight]
    // graph = [
    //     [[1, 1], [2, 5]],         // node 0: 0 -> 1 (1), 0 -> 2 (5) => => from node(index) 0 to node 2, weight(distance) is 5
    //     [[0, 1], [2, 2], [3, 4]], // node 1: 1 -> 0 (1), 1 -> 2 (2), 1 -> 3 (4)
    //     [[0, 5], [1, 2]]          // node 2: 2 -> 0 (5), 2 -> 1 (2)
    // ]

//    Input: V = 3, E = 3, Edges = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]

//    ------------------------------------------------------------------
//    Example graph (N = 5 nodes, M = 6 edges):
//    ------------------------------------------------------------------
//                    2              3
//              (0)--------(1)--------(2)
//               |        / |         /
//               |6     8/  |5      /7
//               |      /   |      /
//              (3)---     (4)----
//
//    Possible spanning trees (each has N-1 = 4 edges, all nodes reachable):
//
//    1) MST — total weight = 16   (edges: 0-1, 1-2, 0-3, 1-4)
//                    2              3
//              (0)--------(1)--------(2)
//               |          |
//               |6         |5
//               |          |
//              (3)        (4)
//
//    2) Spanning tree — total weight = 18   (edges: 0-1, 1-2, 1-3, 1-4)
//                    2              3
//              (0)--------(1)--------(2)
//                        / |
//                      8/  |5
//                      /   |
//                    (3)  (4)
//
//    3) Spanning tree — total weight = 20   (edges: 0-1, 1-2, 1-3, 2-4)
//                    2              3
//              (0)--------(1)--------(2)
//                        /            \
//                      8/              \7
//                      /                \
//                    (3)                (4)
//
//    Why (1) is the MST:
//      - It connects all 5 nodes using exactly N-1 = 4 edges (no cycle).
//      - Its total weight (16) is the smallest among all spanning trees.
//      - Any other spanning tree must swap one of {0-1(2), 1-2(3), 0-3(6),
//        1-4(5)} with a heavier edge (like 1-3 = 8 or 2-4 = 7), which
//        only increases the total. So 16 is the minimum possible.
//    ------------------------------------------------------------------

    // graph is like this,  => [neighbor, weight]
    // graph = [
    //     [[1, 1], [2, 5]],         // node 0: 0 -> 1 (1), 0 -> 2 (5) => => from node(index) 0 to node 2, weight(distance) is 5
    //     [[0, 1], [2, 2], [3, 4]], // node 1: 1 -> 0 (1), 1 -> 2 (2), 1 -> 3 (4)
    //     [[0, 5], [1, 2]]          // node 2: 2 -> 0 (5), 2 -> 1 (2)
    // ]


    // ------------------------------------------------------------------
    // Method 1: primsAlgorithmForMinimumSpanningTreeMST
    // ------------------------------------------------------------------
    // Purpose: Returns ONLY the total weight (sum) of the MST.
    //          Use this when you don't care WHICH edges form the MST,
    //          you just need its cost.
    //
    // Algorithm (Prim's, greedy):
    //   1. Start from any node (here node 0). Push {node, weight} = {0, 0}
    //      into a min-heap ordered by edge weight.
    //   2. Pop the smallest-weight entry from the heap.
    //   3. If that node is already visited -> skip it (avoids cycles).
    //   4. Otherwise mark it visited and add its weight to mstWeightSum.
    //   5. Push all its unvisited neighbors {nextNode, edgeWeight} to heap.
    //   6. Repeat until the heap is empty (all nodes are covered).
    //
    // Key idea: at every step we pick the cheapest edge that connects an
    //           already-included node to a not-yet-included node. That
    //           greedy choice is proven to build the MST.
    //
    // Data used:
    //   - visited[]      : marks nodes already added to MST
    //   - minHeap        : always gives the cheapest edge crossing MST
    //   - mstWeightSum   : running sum of chosen edge weights
    //
    // Time  : O(E log E)     Space : O(V + E)
    public int primsAlgorithmForMinimumSpanningTreeMST(List<List<List<Integer>>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[] {0, 0}); // {node, weight}
        int mstWeightSum = 0;

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[0];
            int weight = current[1];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            mstWeightSum += weight;

            for (List<Integer> neighbor : graph.get(node)) {
                int nextNode = neighbor.get(0);
                int edgeWeight = neighbor.get(1);

                if (!visited[nextNode]) {
                    minHeap.offer(new int[] {nextNode, edgeWeight});
                }
            }
        }

        return mstWeightSum;
    }

    // ------------------------------------------------------------------
    // Method 2: primsAlgorithmForMinimumSpanningTreeMST2
    // ------------------------------------------------------------------
    // Purpose: Returns the ACTUAL MST edges (as [parent, node] pairs) and
    //          also prints the total weight sum.
    //          Use this when you need to know WHICH edges form the MST
    //          (e.g. to reconstruct/print the tree, or to build a subgraph
    //          for further processing).
    //
    // Why a second method?
    //   - Method 1 only tracks the sum, so it cannot tell you which edges
    //     were picked. Method 2 additionally tracks the parent of every
    //     node so it can emit those (parent -> node) edges.
    //
    // Algorithm (Prim's with parent tracking):
    //   1. Push {node, weight, parent} = {0, 0, -1} into a min-heap.
    //      (parent = -1 marks the start node — it has no parent.)
    //   2. Pop the smallest-weight entry.
    //   3. If node is already visited -> skip (cycle prevention).
    //   4. Otherwise mark it visited. If parent != -1, record the edge
    //      [parent, node] into the MST list. Add its weight to sum.
    //   5. Push all unvisited neighbors as {v, w, node} — the current
    //      node becomes their candidate parent.
    //   6. Repeat until the heap is empty.
    //
    // Difference from Method 1:
    //   - Heap entry has an extra field: parent.
    //   - When a node is finalized, we record the edge (parent -> node).
    //   - Returns the list of MST edges instead of only the sum.
    //
    // Time  : O(E log E)     Space : O(V + E)
    public List<List<Integer>> primsAlgorithmForMinimumSpanningTreeMST2(List<List<List<Integer>>> graph) {
        int n = graph.size();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // sorting based on the weight, we want minimum weight on top
        boolean[] visited = new boolean[n];
        // minHeap Store => {node, weightFromNodeToParent, parent}
        minHeap.offer(new int[]{0, 0, -1}); // parent is -1, because this is start
        // visited[0] = true;

        List<List<Integer>> mst = new ArrayList<>();
        int sum = 0;

        while (!minHeap.isEmpty()) {
            int[] tuple = minHeap.poll();
            int node = tuple[0];
            int weight = tuple[1];
            int parent = tuple[2];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            if (parent != -1) {
                mst.add(List.of(parent, node));
            }
            sum += weight;

            for (List<Integer> neighbor: graph.get(node)) {
                int v = neighbor.get(0);
                int w = neighbor.get(1);
                if (!visited[v]) {
                    minHeap.add(new int[]{v, w, node});
                }
            }
        }

        System.out.println("Sum of weights in MST: " + sum);
        // mst is the minimum spanning graph, and sum is the sum of mst graph edges
        return mst;
    }
}
