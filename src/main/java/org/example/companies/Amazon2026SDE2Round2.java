package org.example.companies;

import java.util.Map;
import java.util.TreeMap;

public class Amazon2026SDE2Round2 {
//    Amazon places a soft limit on the number of promotions a selling partner can run simultaneously to prevent spam and misuse.
//    Each promotion has a defined start time and end time.
//    Task: Design and implement a function that takes a list of promotions and
//    returns the maximum number of promotions that are active at the same time.
//
//            Promotion {
//        id: ID
//        startTime: Date
//        endTime: Date
//    }
//
//    Example 1
//    Time: T1 - T2 - T3 - T4
//    A:    [--------)
//    B:          [--)
//    C:       [---)
//    D:    [-------)
//
//    Most running at once is 3, at T2
//    A starts on T1, ends before T4
//    B starts on T3, ends before T4
//    C starts on T2, ends before T3
//    D starts on T1, ends before T3
//
//    Example 2
//    Promo A:    |---------|    (1-10)
//    Promo B:        |--|       (7-8)
//    Promo C:      |--|         (5-7)
//    Promo D:         |--|      (8-9)
//    Promo E:       |--|        (6-8)
//    Promo F:          |--|     (9-11)
//
//    param = [[1,10],[7,8],[5,7],[8,9],[6,8],[9,11]]
//    Answer: 3


    /**
     * Uses half-open intervals: a promotion ending at time T is inactive before
     * another promotion starting at T is counted.
     *
     * Time: O(n log n), space: O(n).
     */
    public static int maxActivePromotions(int[][] promotions) {
        if (promotions == null || promotions.length == 0) {
            return 0;
        }

        TreeMap<Integer, Integer> timeline = new TreeMap<>();

        for (int[] promotion : promotions) {
            if (promotion == null || promotion.length != 2) {
                throw new IllegalArgumentException(
                        "Each promotion must contain exactly a start and end time");
            }
            if (promotion[0] >= promotion[1]) {
                throw new IllegalArgumentException(
                        "Promotion start time must be before its end time");
            }

            timeline.merge(promotion[0], 1, Integer::sum);
            timeline.merge(promotion[1], -1, Integer::sum);
        }

        int maxActive = 0;
        int activeCount = 0;

        for (Map.Entry<Integer, Integer> event : timeline.entrySet()) {
            activeCount += event.getValue();
            maxActive = Math.max(maxActive, activeCount);
        }

        return maxActive;
    }
}
