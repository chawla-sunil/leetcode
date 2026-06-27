package org.example.graph;

import java.util.List;

public class DirectedGraphCycleDetectionDFS {
    // We detect a cycle in a directed graph using DFS with two boolean arrays:
    //   - visited[node]: has this node ever been visited in any DFS call?
    //   - pathVisited[node]: is this node currently in the recursion stack
    //                        (i.e., in the current DFS path)?
    //
    // For a directed graph, a cycle exists if during DFS we find an edge to a node
    // that is already in the current recursion path (pathVisited == true). This
    // indicates a back edge and hence a cycle.
    public boolean isCyclic(int totalNodes, List<List<Integer>> graph) {
        boolean[] visited = new boolean[totalNodes];
        boolean[] pathVisited = new boolean[totalNodes]; // also called recursion stack

        for (int i = 0; i < totalNodes; i++) {
            if (!visited[i] && dfsCycleDetection(i, graph, visited, pathVisited)) {
                return true;
            }
        }

        return false;
    }

    public boolean dfsCycleDetection(int node, List<List<Integer>> graph, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                // neighbor is not visited yet
                if (dfsCycleDetection(neighbor, graph, visited, pathVisited)) {
                    return true;
                }
            } else if (pathVisited[neighbor]) {
                // neighbor has been visited previously.
                // and it has been visited in the current path
                return true;
            }
        }

        pathVisited[node] = false;
        return false;
    }

    public static void main(String[] args) {
        DirectedGraphCycleDetectionDFS dfs = new DirectedGraphCycleDetectionDFS();
        List<List<Integer>> graph = List.of(
                List.of(1, 2),
                List.of(2),
                List.of(0)
        );
        boolean hasCycle = dfs.isCyclic(3, graph);
        System.out.println("Graph has cycle: " + hasCycle);
    }
}
