package org.example;

import java.util.List;

public class BacktrackingFormat {
    // This is a generic backtracking template that can be adapted to various backtracking problems.


    public void backtrack(int start,
                          List<Integer> path,
                          int targetLength) {
        // Base case: if the path meets the goal, return the solution
        if (path.size() == targetLength) {
            // Process the path
            return;
        }

        int[] options = {1, 2, 3, 4, 5}; // Example options to choose from
        // Try all possible choices
        for (int i = start; i < options.length; i++) {
            // Make the choice
            path.add(options[i]);

            // Recurse with the updated path
            backtrack(i + 1, path, targetLength);

            // Backtrack: undo the choice
            path.remove(path.size() - 1);
        }
    }

}
