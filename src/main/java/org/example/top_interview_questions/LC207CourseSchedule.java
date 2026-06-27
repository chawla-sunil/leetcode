package org.example.top_interview_questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC207CourseSchedule {
//    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
//    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
//    you must take course bi first if you want to take course ai.
//
//    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//    Return true if you can finish all courses. Otherwise, return false.
//
//
//
//    Example 1:
//    Input: numCourses = 2, prerequisites = [[1,0]]
//    Output: true
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0. So it is possible.
//
//
//    Example 2:
//    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//    Output: false
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//
//
//    Constraints:
//
//            1 <= numCourses <= 2000
//            0 <= prerequisites.length <= 5000
//    prerequisites[i].length == 2
//            0 <= ai, bi < numCourses
//    All the pairs prerequisites[i] are unique.



    // Topological sort => If there is an edge between node u and v (u -> v),
    // the u should appear before v in the linear order of graph representation.
    // A topological sort is a linear ordering of vertices in a Directed Acyclic Graph (DAG)
    // such that for every directed edge u → v, vertex u comes before v


    // literally a question of detect a cycle in a graph(directed Graph of-course)
    // We are using Kahn's Algorithm for this, which user BFS and
    // Kahn's Algorithm is used to find the Topological sort of graph.
    // KIT => Kahn, Indegree, Topological, using BFS
    // If topological sort is possible, then we can take all the courses.
    // Kahn's Algorithm does not have any visited array, instead it user indegree array
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(); // adjacencyList

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite: prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int course: graph.get(i)) {
                indegree[course]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;

            for (int nextCourse: graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // if there is a cycle, that means we can not finish all course.
        // if there is a cycle, that means we could not write Topologies sort,
        // that means, count is less than count Courses => Watch below video for more on Topology sort
        // Detect a Cycle in Directed Graph | Topological Sort | Kahn's Algorithm | BFS
        return count == numCourses;
    }
}
