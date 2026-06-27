package org.example.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectedGraphCycleDetectionBFSTopologicalSortKahnsAlgorithm {
    // Implementation of Kahn's Algorithm for cycle detection and topological sorting in a directed graph
    // Refer to this for Kahn's Algorithm => TopologicalSortBFSKahnsAlgorithm.java

    public boolean isCyclic(int totalNodes, List<List<Integer>> graph) {
        int[] inDegree = new int[totalNodes];

        for (int i = 0; i < totalNodes; i++) {
            for (int node: graph.get(i)) {
                inDegree[node]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < totalNodes; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // This array topologicalSort is not needed for this question,
        // It is just to show, this is a topological sort problem.
        List<Integer> topologicalSort = new ArrayList<>();
        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            topologicalSort.add(node);
            count++;

            for (int neighbor: graph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // return topologicalSort.size() != totalNodes; // other way to do it, just to show, nothing else.
        return count != totalNodes; // If count is not equal to totalNodes, then there is a cycle, otherwise there is no cycle
    }

    public static void main(String[] args) {
        DirectedGraphCycleDetectionBFSTopologicalSortKahnsAlgorithm bfs = new DirectedGraphCycleDetectionBFSTopologicalSortKahnsAlgorithm();
        List<List<Integer>> graph = List.of(
                List.of(1, 2),
                List.of(2),
                List.of(0)
        );
        boolean hasCycle = bfs.isCyclic(3, graph);
        System.out.println("Graph has cycle: " + hasCycle);
    }
}
