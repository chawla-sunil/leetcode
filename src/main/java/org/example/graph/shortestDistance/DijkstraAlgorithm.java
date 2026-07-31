package org.example.graph.shortestDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    // PART I: Find shorted distance from source node to all node
    // Note: DijkstraAlgorithm only works when all edge weights are non-negative.

//    Given a weighted undirected graph and a source vertex src.
//    We need to find the shortest path distances from the source vertex to all other vertices in the graph.
//    Note: The given graph does not contain any negative edge.

    // Note: DijkstraAlgorithm only works when all edge weights are non-negative.
    // Explanation: Why it will not work on negative edge weights =>

    // Negative-weight example (your case):
    // 1 -> 2 -> 3 -> 1
    //
    // Weighted arrows (using / and \):
    //                (-2)
    //           1 --------> 2
    //            ↖         /
    //          (2) \      / (-1)
    //               \ 3 ↙
    //
    //
    // Edges are:
    // 1 --(-2)--> 2
    // 2 --(-1)--> 3
    // 3 --(2)--> 1
    //
    // Why Dijkstra fails here:
    // Dijkstra assumes: once a node is picked with the smallest current distance,
    // that distance is final. This is true only with non-negative edges.
    //
    // In this cycle, total weight = (-2) + (-1) + (2) = -1.
    // So each time you go around the cycle, path cost becomes smaller by 1.
    // That means there is no stable "shortest" distance (it can keep decreasing),
    // which breaks Dijkstra's core assumption.



    // graph is like this,  => [neighbor, weight]
    // [  [[2,1], [2,5]],        => from node(index) 0 to node 2, weight(distance) is 1
    //    [[0,1], [2,2], [1, 7]],
    //    [[0,5], [1,2]]  ]
    public List<Integer> dijkstraAlgorithm(List<List<List<Integer>>> graph, int src) {
        int n = graph.size();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]); // new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        minHeap.offer(new int[] {src, 0});
        dist[src] = 0;

        while (!minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int node = pair[0];
            int distance = pair[1];

            if (distance > dist[node]) {
                // this condition only works if all the distance are in positive number
                continue;
            }

            for (List<Integer> adj: graph.get(node)) {
                int neighbor = adj.get(0);
                int distanceBetweenNodeAndNeighbor = adj.get(1);

                if (distance + distanceBetweenNodeAndNeighbor < dist[neighbor]) {
                    dist[neighbor] = distance + distanceBetweenNodeAndNeighbor;
                    minHeap.offer(new int[] {neighbor, dist[neighbor]});
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int d: dist) {
            result.add(d);
        }
        return result;
    }
}
