package org.example.graph.shortestDistance;

public class FloydWarshallAlgorithm {
    // PART III: Find the shortest distance from all nodes to all nodes. (In the matrix)
    // Note: FloydWarshallAlgorithm works even when some edge weights are negative.
    // But graph should not negative cycles, which we can identify in this algorithm itself

    // Floyd-Warshall algorithm is used to find the shortest paths between all pairs of vertices in a weighted graph.
    // It works for both directed and undirected graphs, and it can handle negative edge weights (but not negative cycles).
    // The algorithm uses dynamic programming to iteratively update the shortest path estimates.


//    You are given a weighted directed graph, represented by an adjacency matrix, dist[][] of size n x n, where dist[i][j] represents the weight of the edge from node i to node j. If there is no direct edge, dist[i][j] is set to a large value (i.e., 108) to represent infinity.
//    The graph may contain negative edge weights, but it does not contain any negative weight cycles.
//
//    Your task is to find the shortest distance between every pair of nodes i and j in the graph.
//
//    Note: Modify the distances for every pair in place.
//
//    Examples :
//
//    Input: dist[][] = [[0,    4,    10^8, 5,    10^8],
//                       [10^8, 0,    1,    10^8, 6],
//                       [2,    10^8, 0,    3,    10^8],
//                       [10^8, 10^8, 1,    0,    2],
//                       [1,    10^8, 10^8, 4,    0]]

    // When we have given graph as adjacency matrix
    // When We want to do in-place
    public void floydWarshall(int[][] dist) {
        int n = dist.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        // The code is done till here.

        // Extra code if we want to check if there is any negative cycle in this graph.
        // dist[i][i] < 0 indicates a negative cycle
        // because dist[i][i] should be 0 in ideal case with no negative cycle because
        // the distance from i to i is 0.
        // Example:  Here once we do dist[1][1] = dist[1][2] + dist[2][1] = (-2) + (-1+2) = -1 which is less than 0
        // Weighted arrows (using / and \):
        //                (-2)
        //           1 --------> 2
        //            ↖         /
        //          (2) \      / (-1)
        //               \ 3 ↙
        // So if dist[i][i] < 0 that means there is a negative cycle
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative cycle detected in the graph.");
                return;
            }
        }
    }

    // When we have given graph as adjacency list
    // Then we will first have to convert it to adjacency matrix
    public int[][] floydWarshall(int V, int[][] graph) {
        // V = number of nodes = n
        int[][] dist = new int[V][V];

        // converting adjacency list to adjacency matrix
        // Initialize the distance matrix with the given graph weights
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distance from a vertex to itself is 0
                } else if (graph[i][j] != 0) {
                    dist[i][j] = graph[i][j]; // Use the weight of the edge if it exists
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // No edge between i and j => we can also do (int)(1e8) instead of Integer.MAX_VALUE
                }
            }
        }

        // Update the distance matrix using intermediate vertices
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Extra code if we want to check if there is any negative cycle in this graph.
        // dist[i][i] < 0 indicates a negative cycle
        // because dist[i][i] should be 0 in ideal case with no negative cycle because
        // the distance from i to i is 0.
        // Example:  Here once we do dist[1][1] = dist[1][2] + dist[2][1] = (-2) + (-1+2) = -1 which is less than 0
        // Weighted arrows (using / and \):
        //                (-2)
        //           1 --------> 2
        //            ↖         /
        //          (2) \      / (-1)
        //               \ 3 ↙
        // So if dist[i][i] < 0 that means there is a negative cycle
        for (int i = 0; i < V; i++) {
            if (dist[i][i] < 0) {
                System.out.println("Negative cycle detected in the graph.");
                return new int[0][0];
            }
        }

        return dist;
    }

}
