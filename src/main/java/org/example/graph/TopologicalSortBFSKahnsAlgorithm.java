package org.example.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortBFSKahnsAlgorithm {

//    A topological sort is a linear ordering of the vertices in a Directed Acyclic Graph (DAG)
//    such that for every directed edge u → v, vertex u appears before v in the ordering.
//    A topological sort is only possible for Directed Acyclic Graphs (DAGs).

//    KIT => Kahn's Algorithm, Indegree, Topological sort.
//    It uses BFS. It does not use a visited array; instead, it uses an indegree array.

    public List<Integer> topologicalSort(int totalNodes, List<List<Integer>> graph) {
        List<Integer> res = new ArrayList<>();
        int[] indegree = new int[totalNodes];

        for (int i = 0; i < totalNodes; i++) {
            for (int node: graph.get(i)) {
                indegree[node]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < totalNodes; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);

            for (int neighbour: graph.get(node)) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.offer(neighbour);
                }
            }
        }

//        if (res.size() != totalNodes) {
//            System.out.println("Graph is not a DAG(Directed Acyclic Graph); topological sort not possible");
//        }

        return res;
    }

    public static void main(String[] args) {
        TopologicalSortBFSKahnsAlgorithm topologicalSortBFSKahnsAlgorithm = new TopologicalSortBFSKahnsAlgorithm();
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(List.of(1, 2));
        graph.add(List.of(3));
        graph.add(List.of(3));
        graph.add(List.of());
        // Valid topological orders for above example:
        // [0, 1, 2, 3]
        // [0, 2, 1, 3]
        List<Integer> res = topologicalSortBFSKahnsAlgorithm.topologicalSort(4, graph);
        System.out.println(res);
    }
}
