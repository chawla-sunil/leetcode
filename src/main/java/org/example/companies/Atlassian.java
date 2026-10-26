package org.example.companies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Atlassian {
    public static void main(String[] args) {
        Atlassian atlassian = new Atlassian();

        MostPopular popularityTracker = atlassian.new SimpleMostPopularImpl();
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        assertMostPopular(popularityTracker, 7); // returns 7
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(8);
        assertMostPopular(popularityTracker, 8); // returns 8
        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(8);
        assertMostPopular(popularityTracker, 7); // returns 7
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(8);
        assertMostPopular(popularityTracker, -1); // returns -1



        popularityTracker = atlassian.new ExtendedMostPopularImpl();
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        assertMostPopular(popularityTracker, 8); // returns 8
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(7);
        assertMostPopular(popularityTracker, 7); // returns 7
        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(7);
        assertMostPopular(popularityTracker, 7); // returns 7 (most recently updated)
    }

    private static void assertMostPopular(MostPopular tracker, int expected) {
        int actual = tracker.mostPopular();
        if (actual != expected) {
            throw new AssertionError("Expected most popular ID " + expected + ", but got " + actual);
        }
    }

    // Return the most popular content ID from the given list of content IDs.

    // implement the MostPopular interface with methods to increase, decrease, and retrieve the most popular content ID.
    interface MostPopular {

        void increasePopularity(Integer contentId);

        Integer mostPopular();

        void decreasePopularity(Integer contentId);

    }

    private class SimpleMostPopularImpl implements MostPopular {
        Map<Integer, Integer> counts = new HashMap<>();
        TreeMap<Integer, TreeSet<Integer>> idsByCount = new TreeMap<>();

        @Override
        public void increasePopularity(Integer contentId) {
            changeCount(contentId, 1);

        }

        @Override
        public Integer mostPopular() {
            if (idsByCount.isEmpty()) {
                System.out.println(-1);
                return -1;
            }
            Integer popular = idsByCount.lastEntry().getValue().first();
            System.out.println(popular);
            return popular;
        }

        @Override
        public void decreasePopularity(Integer contentId) {
            if (counts.containsKey(contentId)) {
                changeCount(contentId, -1);
            }
        }

        public void changeCount(Integer id, int change) {
            int oldCount  = counts.getOrDefault(id, 0);

            if (oldCount > 0) {
                TreeSet<Integer> ids = idsByCount.get(oldCount);
                ids.remove(id);
                if (ids.isEmpty()) {
                    idsByCount.remove(oldCount);
                }
            }

            int newCount = oldCount + change;
            if (newCount == 0) {
                counts.remove(id);
            } else {
                counts.put(id, newCount);
                idsByCount.computeIfAbsent(newCount, k -> new TreeSet<>()).add(id);
            }
        }
    }



    // Current from the TreeSet, we are returning the first element, now change it and return the most popular content ID with the highest popularity and
    // If there are multiple content IDs with the same popularity, return the one that was updated most recently.

    private class ExtendedMostPopularImpl implements MostPopular {
        private static class State {
            int  id;
            int count;
            long updateAt;

            State(int id, int count, long updateAt) {
                this.id = id;
                this.count = count;
                this.updateAt = updateAt;
            }
        }

        Map<Integer, State> counts = new HashMap<>();
        TreeMap<Integer, TreeSet<State>> idsByCount = new TreeMap<>();
        long clock = 0;

        @Override
        public void increasePopularity(Integer contentId) {
            changeCount(contentId, 1);
        }

        @Override
        public Integer mostPopular() {
            if (idsByCount.isEmpty()) {
                System.out.println(-1);
                return -1;
            }
            Integer popular = idsByCount.lastEntry().getValue().last().id;
            System.out.println(popular);
            return popular;
        }

        @Override
        public void decreasePopularity(Integer contentId) {
            if (counts.containsKey(contentId)) {
                changeCount(contentId, -1);
            }
        }

        public void changeCount(Integer id, int change) {
            State old = counts.remove(id);
            int oldCount  = old == null ? 0 : old.count;

            if (old != null) {
                TreeSet<State> group = idsByCount.get(oldCount);
                group.remove(old);
                if (group.isEmpty()) {
                    idsByCount.remove(oldCount);
                }
            }

            int newCount = oldCount + change;
            if (newCount > 0) {
                State current = new State(id, newCount, ++clock);
                counts.put(id, current);
                idsByCount.computeIfAbsent(newCount,
                        count -> new TreeSet<>((a, b) -> Long.compare(a.updateAt, b.updateAt))).add(current);
            }
        }
    }
}
