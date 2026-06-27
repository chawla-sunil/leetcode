package org.example.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UndirectedGraphCycleDetectionBFS {
    // We perform a BFS over the graph (represented as an adjacency list), keeping track
    // of each node along with its parent in the BFS tree. For every neighbor of the
    // current node, we check:
    //   - If the neighbor has not been visited, we mark it visited and enqueue it
    //     with the current node as its parent.
    //   - If the neighbor has already been visited and is NOT the parent of the
    //     current node, then we have found a back edge and hence a cycle in the
    //     undirected graph.

    // During BFS, we store each node together with the node from which we discovered it
    // (its parent in the BFS tree). If we encounter a neighbor that is already visited
    // and it is not the parent, that means this neighbor was reached earlier via a
    // different path, indicating a cycle in the undirected graph.

    public boolean isCyclic(int totalNodes, List<List<Integer>> graph) {
        boolean[] visited = new boolean[totalNodes];

        // The graph might be disconnected, so we start a DFS from every unvisited node
        for (int i = 0; i < totalNodes; i++) {
            if (!visited[i] && bfsCycleDetection(i, graph, visited)) {
                return true;
            }
        }
        return false;
    }

    public boolean bfsCycleDetection(int startNode, List<List<Integer>> graph, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(startNode, -1)); // -1 as parentNode for startNode.
        visited[startNode] = true; // best practice => mark visited on enqueue

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int node = pair.node;
            int parentNode = pair.parentNode;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    queue.offer(new Pair(neighbor, node));
                    visited[neighbor] = true;
                } else if (neighbor != parentNode) {
                    return true; // found a cycle
                }
            }
        }
        return false;
    }

    public boolean bfsCycleDetection2(int startNode, List<List<Integer>> graph, boolean[] visited) {
        // Queue to store pairs of (currentNode, parentNode)
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startNode, -1}); // Start with the startNode and no parent
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int parentNode = current[1];

            for (int neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, currentNode});
                } else if (neighbor != parentNode) {
                    // If the neighbor is visited and is not the parent, we found a cycle
                    return true;
                }
            }
        }
        return false;
    }

    private class Pair {
        int node;
        int parentNode;
        Pair(int node, int parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }

    public static void main(String[] args) {
        UndirectedGraphCycleDetectionBFS u = new UndirectedGraphCycleDetectionBFS();
        List<List<Integer>> graph = new ArrayList<>();
        // create a test graph and test out function.
        graph.add(List.of(1, 2));
        graph.add(List.of(0, 2));
        graph.add(List.of(0, 1, 3));
        graph.add(List.of(2));
        System.out.println(u.isCyclic(4, graph)); // Should print true, as there is a cycle 0-1-2-0
    }
}
