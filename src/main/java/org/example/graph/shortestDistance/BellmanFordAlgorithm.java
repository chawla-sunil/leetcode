package org.example.graph.shortestDistance;

import java.util.Arrays;

public class BellmanFordAlgorithm {
    // PART II: Find shorted distance from source node to all node
    // Note: BellmanFordAlgorithm works even when some edge weights are negative.
    // But graph should not have negative sum cycles, which we can identify in this algorithm itself

//    Given an weighted graph with V vertices numbered from 0 to V-1 and E edges,
//    represented by a 2d array edges[][], where edges[i] = [u, v, w]
//    represents a direct edge from node u to v having w edge weight.
//    You are also given a source vertex src.
//
//    Your task is to compute the shortest distances from the source to all other vertices.
//    If a vertex is unreachable from the source, its distance should be marked as 10^8. Additionally,
//    if the graph contains a negative weight cycle,
//    return [-1] to indicate that shortest paths cannot be reliably computed.
//
//    Examples:

//    Input: V = 5, edges[][] = [[1, 3, 2], [4, 3, -1], [2, 4, 1], [1, 2, 1], [0, 1, 5]], src = 0
//    Example graph:
//    0 --(5)--> 1 --(2)--> 3
//                \         ↖
//               (1)         \
//                 \          \(-1)
//                  v          \
//                  2 --(1)---> 4
//    Output: [0, 5, 6, 6, 7]
//    Explanation: Shortest Paths:
//    For 0 to 1 minimum distance will be 5. By following path 0 → 1
//    For 0 to 2 minimum distance will be 6. By following path 0 → 1  → 2
//    For 0 to 3 minimum distance will be 6. By following path 0 → 1  → 2 → 4 → 3
//    For 0 to 4 minimum distance will be 7. By following path 0 → 1  → 2 → 4


    // Relax every edge V-1 times: each pass tries to improve the known shortest path
    // using one more edge, so after enough passes we find the shortest path from src.
    // Why =>
    // In a graph with V vertices, the shortest path from source to any node can use at most V-1 edges.
    // If a path had more than V-1 edges, it would repeat a node and form a cycle.
    // If all edge weights are non-negative, repeating a cycle only makes it worse;
    // with negative weights, a negative cycle is a problem, but if there is no negative cycle,
    // the best path is always a simple path with at most V-1 edges.
    public int[] bellmanFord(int V, int[][] edges, int src) { // V = number of nodes(Vertices)
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE); // OR Arrays.fill(dist, (int)(1e8)); 10^8
        dist[src] = 0;

        for (int i = 0; i < V-1; i++) {
            for (int[] edge: edges) {
                // from node u to node v, the edge weight is w.
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                // we have found minimum distance even in nth(Vth) iteration also
                // that means there is a cycle in this group and the sum of weights of
                // these edges is less than 0, and everytime we go around the loop,
                // we will get less distance So we return -1;
                return new int[]{-1};
            }
        }
        return dist;
    }
}
