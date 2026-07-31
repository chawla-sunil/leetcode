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
    // [  [[2,1], [2,5]],         => from node(index) 0 to node 2, weight(distance) is 1
    //    [[0,1], [2,2], [1, 7]],
    //    [[0,5], [1,2]]  ]

//    Input: V = 3, E = 3, Edges = [[0, 1, 5], [1, 2, 3], [0, 2, 1]]

    // method for when we only want the sum of MST edges
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

    public List<List<Integer>> primsAlgorithmForMinimumSpanningTreeMST2(List<List<List<Integer>>> graph) {
        // graph => {u, v, w} =>. u --(w) -->w   => node u to v with w weight
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
