package org.example.top_interview_questions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LC433MinimumGeneticMutation {
//    A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
//
//    Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene
//    where one mutation is defined as one single character changed in the gene string.
//
//    For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
//    There is also a gene bank bank that records all the valid gene mutations.
//    A gene must be in bank to make it a valid gene string.
//
//    Given the two gene strings startGene and endGene and the gene bank bank,
//    return the minimum number of mutations needed to mutate from startGene to endGene.
//    If there is no such a mutation, return -1.
//
//    Note that the starting point is assumed to be valid, so it might not be included in the bank.
//
//
//
//    Example 1:
//    Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
//    Output: 1
//
//
//    Example 2:
//    Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
//    Output: 2
//
//
//    Constraints:
//    0 <= bank.length <= 10
//    startGene.length == endGene.length == bank[i].length == 8
//    startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String gene: bank) {
            set.add(gene);
        }

        if (!set.contains(endGene)) {
            return -1; // early return if gene is not present in the bank
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(startGene);
        visited.add(startGene);

        char[] bases = {'A', 'C', 'G', 'T'};

        int mutations = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // We are do level wise BFS traversal, because for 1 word, a character can change multiple places
            // and every char change is 1 mutation.
            while (levelSize-- > 0) {
                String currGene = queue.poll();
                if (currGene.equals(endGene)) {
                    return mutations;
                }

                char[] charArray = currGene.toCharArray();
                for (int i = 0; i < 8; i++) { // length of gene = 8;
                    char original = charArray[i];

                    for (int j = 0; j < 4; j++) { // length of bases {'A', 'C', 'G', 'T'}
                        if (original == bases[j]) { // char is primitive, it does not have any method, so use == to compare.
                            continue;
                        }

                        charArray[i] = bases[j];
                        String newGene = new String(charArray);
                        if (set.contains(newGene) && !visited.contains(newGene)) {
                            queue.add(newGene);
                            visited.add(newGene);
                        }
                    }

                    charArray[i] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}
