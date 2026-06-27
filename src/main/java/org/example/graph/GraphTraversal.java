package org.example.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphTraversal {
    // Implementation of BFS and DFS traversal for a graph
    // This is a placeholder for the actual code

    // BFS iterative traversal logic
    public List<Integer> bfs(int startNode, List<List<Integer>> graph) {
        List<Integer> result = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];

        queue.add(startNode);
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor: graph.get(node)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return result;
    }

    // DFS iterative traversal logic
    public List<Integer> dfs(int startNode, List<List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.size()];

        stack.push(startNode);
        visited[startNode] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            result.add(node);

            for (int neighbor: graph.get(node)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return result;
    }

    // DFS recursive traversal logic
    public void dfsRecursive(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);

        for (int neighbor: graph.get(node)) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, graph, visited, result);
            }
        }
    }

    public static void main(String[] args) {
        // Example usage of the GraphTraversal class
        GraphTraversal traversal = new GraphTraversal();
        List<List<Integer>> graph = new ArrayList<>();

        // Initialize the graph (example)
        for (int i = 0; i < 5; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(1).add(4);

        // Perform BFS and DFS
        List<Integer> bfsResult = traversal.bfs(0, graph);
        System.out.println("BFS Traversal: " + bfsResult);

        List<Integer> dfsResult = traversal.dfs(0, graph);
        System.out.println("DFS Traversal: " + dfsResult);

        boolean[] visited = new boolean[graph.size()];
        List<Integer> dfsRecursiveResult = new ArrayList<>();
        traversal.dfsRecursive(0, graph, visited, dfsRecursiveResult);
        System.out.println("DFS Recursive Traversal: " + dfsRecursiveResult);
    }
}
