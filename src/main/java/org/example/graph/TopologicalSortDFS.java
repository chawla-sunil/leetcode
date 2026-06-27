package org.example.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortDFS {
//    A topological sort is a linear ordering of the vertices in a Directed Acyclic Graph (DAG)
//    such that for every directed edge u → v, vertex u appears before v in the ordering.
//    A topological sort is only possible for Directed Acyclic Graphs (DAGs).

//    It is kind of similar to how we write the code for detecting a cyclen in directed graph.
//    We process all the way and put the end in the stack once all neighbor nodes are processed.

    public List<Integer> topologicalSort(int totalNodes, List<List<Integer>> graph) {
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[totalNodes];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < totalNodes; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    public void dfs(int node, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        for (int neighbor: graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, stack);
            }
        }

        stack.add(node); // stack.push(node);
    }

    public static void main(String[] args) {
        TopologicalSortDFS topologicalSortDFS = new TopologicalSortDFS();
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(List.of(1, 2));
        graph.add(List.of(3));
        graph.add(List.of(3));
        graph.add(List.of());

        List<Integer> result = topologicalSortDFS.topologicalSort(4, graph);
        System.out.println(result); // Output: [0, 2, 1, 3] or [0, 1, 2, 3] depending on the order of processing
    }
}
