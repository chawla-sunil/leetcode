package org.example.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndirectedGraphCycleDetectionDFS {
    // We perform a DFS over the graph (represented as an adjacency list), keeping track
    // of the current node and the node from which we arrived at it (its parent in the DFS tree).
    //
    // For every neighbor of the current node, we check:
    //   - If the neighbor has not been visited, we recursively DFS into that neighbor
    //     with the current node as its parent.
    //   - If the neighbor has already been visited and is NOT the parent of the
    //     current node, then we have found a back edge and hence a cycle in the
    //     undirected graph.
    //
    // This is analogous to the BFS version, but uses recursion (DFS) instead of a queue.

    public boolean isCyclic(int totalNodes, List<List<Integer>> graph) {
        boolean[] visited = new boolean[totalNodes];

        for (int i = 0; i < totalNodes; i++) {
            if (!visited[i] && dfsCycleDetection(i, graph, visited)) { // dfsCycleDetection3(i, -1, graph, visited)
                return true;
            }
        }

        return false;
    }

    public boolean dfsCycleDetection(int startNode, List<List<Integer>> graph, boolean[] visited) {
//        Use Deque instead of Stack (modern Java style):
//        Deque<Pair> stack = new ArrayDeque<>();
//        stack.push(new Pair(startNode, -1));
//        Pair pair = stack.pop();
//        Stack is synchronized and considered legacy; ArrayDeque is faster and recommended for stack-like use.

        Stack<Pair> stack = new Stack<>();
        stack.add(new Pair(startNode, -1)); // -1 as parentNode for startNode.
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int node = pair.node;
            int parentNode = pair.parentNode;

            for (int neighbor: graph.get(node)) {
                if (!visited[neighbor]) {
                    stack.add(new Pair(neighbor, node));
                    visited[neighbor]  = true;
                } else if (neighbor != parentNode) {
                    return true; // Found a back edge, hence a cycle exists.
                }
            }
        }

        return false; // No cycle found in this connected component.
    }

    public boolean dfsCycleDetection2(int startNode, List<List<Integer>> graph, boolean[] visited) {
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{startNode, -1}); // -1 as parentNode for startNode.
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int node = pair[0];
            int parentNode = pair[1];

            for (int neighbor: graph.get(node)) {
                if (!visited[neighbor]) {
                    stack.add(new int[]{neighbor, node});
                    visited[neighbor] = true;
                } else if (neighbor != parentNode) {
                    return true;  // If the neighbor is visited and is not the parent, we found a cycle
                }
            }
        }

        return false; // No cycle found in this connected component.
    }

    public boolean dfsCycleDetection3(int currentNode, int parentNode, List<List<Integer>> graph, boolean[] visited) {
        visited[currentNode] = true;

        for (int neighbor: graph.get(currentNode)) {
            if (!visited[neighbor]) {
                if (dfsCycleDetection3(neighbor, currentNode, graph, visited)) {
                    return true; // If a cycle is found in the recursive call, propagate it up
                }
            } else if (neighbor != parentNode) {
                return true; // If the neighbor is visited and is not the parent, we found a cycle
            }
        }

        return false;
    }

    private static class Pair {
        int node;
        int parentNode;
        public Pair(int node, int parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }

    public static void main(String[] args) {
        UndirectedGraphCycleDetectionDFS u = new UndirectedGraphCycleDetectionDFS();
        List<List<Integer>> graph = new ArrayList<>();
        // create a test graph and test out function.
        graph.add(List.of(1, 2));
        graph.add(List.of(0, 2));
        graph.add(List.of(0, 1, 3));
        graph.add(List.of(2));
        System.out.println(u.isCyclic(4, graph)); // Should print true, as there is a cycle 0-1-2-0
    }
}
