package org.example.companies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Atlassian {
//    Agent Vote
//
//    Question
//    Design a Customer Support Ticket System that allows customers to rate support agents on a scale of 1 to 5.
//
//    You need to implement functionality to:
//
//    Accept a rating for an agent.
//    Calculate the average score for each agent.
//    Return a list of agents sorted by their average score in descending order.
//            Requirements
//    Input: A stream of (agent_id, score) pairs.
//    Output: A sorted list of agent IDs.
//    Tie-breaking: If two agents have the same average score, define a strategy to handle the tie
//    Example
//    Input:
//
//    rate("A", 5)
//    rate("B", 3)
//    rate("A", 3)
//    Calculations:
//
//    Agent A: (5 + 3) / 2 = 4.0
//    Agent B: 3.0 / 1 = 3.0
//    Output: ["A", "B"]
//
//    Part 2 — Weighted Voting System
//    Design a specialized Voting Data Structure for a ranking system.
//
//    Unlike the simple average score, this system uses a weighted ranking mechanism.
//
//    Rules
//    Each vote provides a ranked list of candidates (e.g., 1st, 2nd, 3rd choice).
//    Points are awarded based on position:
//            1st place: 3 points
//2nd place: 2 points
//3rd place: 1 point
//    The system should support adding votes and calculating the final ranking based on total points.
//            Task
//    Implement a class that supports:
//
//    addVote(List<String> candidates): Records a vote where candidates are ordered by preference.
//            getRankings(): Returns the list of candidates sorted by total points.
//            Example
//    Input:
//
//    Vote 1: [A, B, C]  -> A=3, B=2, C=1
//    Vote 2: [B, A, C]  -> B=3, A=2, C=1
//    Totals:
//
//    A: 3 + 2 = 5
//    B: 2 + 3 = 5
//    C: 1 + 1 = 2
//    Output: [A, B, C] (Assuming tie-break by name or other rule)
//
//    Constraints
//    Scores are integers between 1 and 5.
//    Timestamps are integers.
//    Prices are numbers.

    public static class AgentRatingSystem {
        private final Map<String, RatingSummary> ratings = new HashMap<>();

        public void rate(String agentId, int score) {
            validateId(agentId, "Agent ID");
            if (score < 1 || score > 5) {
                throw new IllegalArgumentException("Score must be between 1 and 5");
            }

            ratings.computeIfAbsent(agentId, ignored -> new RatingSummary()).add(score);
        }

        public double getAverageScore(String agentId) {
            validateId(agentId, "Agent ID");
            RatingSummary summary = ratings.get(agentId);
            if (summary == null) {
                throw new IllegalArgumentException("No ratings found for agent: " + agentId);
            }
            return summary.average();
        }

        public Map<String, Double> getAverageScores() {
            Map<String, Double> averages = new LinkedHashMap<>();
            for (String agentId : getRankings()) {
                averages.put(agentId, ratings.get(agentId).average());
            }
            return averages;
        }

        public List<String> getRankings() {
            List<String> agents = new ArrayList<>(ratings.keySet());
            agents.sort((first, second) -> {
                int byAverage = Double.compare(
                        ratings.get(second).average(),
                        ratings.get(first).average()
                );
                return byAverage != 0 ? byAverage : first.compareTo(second);
            });
            return agents;
        }

        private static class RatingSummary {
            private long total;
            private long count;

            private void add(int score) {
                total += score;
                count++;
            }

            private double average() {
                return (double) total / count;
            }
        }
    }

    public static class WeightedVotingSystem {
        private static final int HIGHEST_SCORE = 3;

        private final Map<String, Long> scores = new HashMap<>();

        public void addVote(List<String> candidates) {
            if (candidates == null || candidates.isEmpty()) {
                throw new IllegalArgumentException("A vote must contain at least one candidate");
            }

            Set<String> uniqueCandidates = new HashSet<>();
            for (String candidate : candidates) {
                validateId(candidate, "Candidate");
                if (!uniqueCandidates.add(candidate)) {
                    throw new IllegalArgumentException(
                            "A candidate cannot appear more than once in a vote: " + candidate
                    );
                }
            }

            for (int position = 0; position < candidates.size(); position++) {
                long points = Math.max(HIGHEST_SCORE - position, 0);
                scores.merge(candidates.get(position), points, Long::sum);
            }
        }

        public List<String> getRankings() {
            List<String> candidates = new ArrayList<>(scores.keySet());
            candidates.sort((first, second) -> {
                int byScore = Long.compare(scores.get(second), scores.get(first));
                return byScore != 0 ? byScore : first.compareTo(second);
            });
            return candidates;
        }

        public long getScore(String candidate) {
            validateId(candidate, "Candidate");
            return scores.getOrDefault(candidate, 0L);
        }
    }

    private static void validateId(String id, String fieldName) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }
}
