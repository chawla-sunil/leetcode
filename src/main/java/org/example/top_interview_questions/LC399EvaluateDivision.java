package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class LC399EvaluateDivision {
//    You are given an array of variable pairs equations and an array of real numbers values,
//    where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
//    Each Ai or Bi is a string that represents a single variable.
//
//    You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
//    where you must find the answer for Cj / Dj = ?.
//
//    Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//    Note: The input is always valid. You may assume that evaluating
//    the queries will not result in division by zero and that there is no contradiction.
//
//    Note: The variables that do not occur in the list of equations are undefined,
//    so the answer cannot be determined for them.
//
//
//
//    Example 1:
//    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
//    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
//    Explanation:
//    Given: a / b = 2.0, b / c = 3.0
//    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//            return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
//    note: x is undefined => -1.0
//
//
//    Example 2:
//    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//    Output: [3.75000,0.40000,5.00000,0.20000]
//
//
//    Example 3:
//    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
//    Output: [0.50000,2.00000,-1.00000,-1.00000]
//
//
//    Constraints:
//
//            1 <= equations.length <= 20
//    equations[i].length == 2
//            1 <= Ai.length, Bi.length <= 5
//    values.length == equations.length
//            0.0 < values[i] <= 20.0
//            1 <= queries.length <= 20
//    queries[i].length == 2
//            1 <= Cj.length, Dj.length <= 5
//    Ai, Bi, Cj, Dj consist of lower case English letters and digits.

    // Solution link : https://leetcode.com/problems/evaluate-division/solutions/3543256/image-explanation-easiest-concise-comple-okpu => this is recursion dfs
    // This another link is iterative bfs
    // => https://leetcode.com/problems/evaluate-division/solutions/3543150/pythonjavacsimple-solutioneasy-to-unders-7uwo

    // Here I have used iterative dfs
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                ans[i] = -1.0;
            } else {
                ans[i] = dfs(dividend, divisor, graph);
            }
        }

        return ans;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(dividend, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());

            graph.get(dividend).put(divisor, value);
            graph.get(divisor).put(dividend, 1.0 / value);
        }

        return graph;
    }

    private double dfs(String start, String end, Map<String, Map<String, Double>> graph) {
        Stack<Pair> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.add(new Pair(start, 1.0)); // node and it's value to reach this node from start.

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            String node = pair.node;
            double value = pair.value;

            if (node.equals(end)) {
                return value;
            }
            visited.add(node);

            for (Map.Entry<String, Double> entry: graph.get(node).entrySet()) {
                String newNode = entry.getKey();
                double newValue = entry.getValue();

                if (!visited.contains(newNode)) {
                    stack.add(new Pair(newNode, value * newValue));
                }
            }
        }

        return -1.0;
    }

    public class Pair {
        String node;
        double value;

        public Pair(String node, double value) {
            this.node = node;
            this.value = value;
        }
    }
}
