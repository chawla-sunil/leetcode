package org.example.companies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class LinkedIn {

    // Round 1 ====================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // People are sitting in a theater row and you're an usher (you tell people where to sit).
//
// Given a row of seats which are either occupied (1) or unoccupied (0) and the condition
// that new people being seated do not like to sit next to anyone else, implement the
// function that determines whether you can or cannot seat a number of people in the row.
//
//
// Input: [1,0,1,0,0,0,1,0,0]
// Can we seat # of people? -> Expected Result
//
//    3 => true
//    4 => false
//
// Input: [1,0,0,1,0,0,1,0,0]
//
//    1 => true
//    2 => false
//
// Input: [0]
//
//    1 => true

// [1,0,0,1,0,1,0,1,0,1]
// [1,0,1,0,1,0,0,1,0,1]

//            0 => continue, l = true;
//            1 = r = true, l false;
//            2 = l false, r true, l true seated = 1
//            3 = r false, l false
//            4 = r true, seated = 2, l true
//            5 = l false,
//            6 = continue, l true
//            7, l true, => l false
//            8 = l false r true seated 3


    public boolean solve1(int[] seats,
                          int people) {
        if (people <= 0) {
            return true;
        }
        if (seats == null || seats.length == 0) {
            return false;
        }

        int seated = 0;
        boolean prevStatus = false;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                prevStatus = true;
                continue;
            }

            boolean rightStatus = i == seats.length - 1 || seats[i + 1] == 0;

            if (!prevStatus && rightStatus) {
                seated++;
                prevStatus = true;

                if (seated >= people) {
                    return true;
                }
            } else {
                prevStatus = false;
            }
        }

        return false;
    }


    /**
     * Given a nested list of integers, returns the sum of all integers in the list weighted by their reversed level #.
     * For example, given the list {{1,1},2,{1,1}} the deepest level is 2. Thus the function should return 8 (four 1's
     * with weight 1, one 2 with weight 2)
     * Given the list {1,{4,{6,2}}} the function should return 19 (1 with weight 3, 4 with weight 2, 6 with weight 1, 2
     * with weight 1)
     *
     * It is the "reverse depth" of the item in the list: eg for the above item { 1, {4, { 6, 2 } } }
     *
     * 1 (reverse-depth 3) . = 1 * 3 = 3
     * \
     * { 4 } (reverse-depth 2) = 4 * 2 = 8
     * \
     * { 6, 2 } (reverse-depth 1) = 6 * 1 + 2 * 1 = 8
     *
     * = 3 + 8 + 8 = 19
     */


    public int reverseDepthSum(List<NestedInteger> input) {
        // implementation here
        int maxDepth = getDepth(input, 1);
        return solve2(input, 1, maxDepth);
    }

    public int solve2(List<NestedInteger> input,
                      int depth,
                      int maxDepth) {
        int sum = 0;

        for (NestedInteger item : input) {
            if (item.isInteger()) {
                int reverseDepth = maxDepth - depth + 1;
                sum += reverseDepth * item.getInteger();
            } else {
                sum += solve2(item.getList(), depth + 1, maxDepth);
            }
        }

        return sum;
    }

    public int getDepth(List<NestedInteger> input,
                        int depth) {
        int maxDepth = depth;

        for (NestedInteger item : input) {
            if (!item.isInteger()) {
                maxDepth = Math.max(maxDepth, getDepth(item.getList(), depth + 1));
            }
        }

        return maxDepth;
    }

    /**
     * This is the interface that represents nested lists.
     * You should not implement it, or speculate about its implementation.
     */
    public interface NestedInteger {

        /**
         * @return true if this NestedInteger holds a single integer, rather than a nested list
         */
        boolean isInteger();

        /**
         * @return the single integer that this NestedInteger holds, if it holds a single integer
         * Return null if this NestedInteger holds a nested list
         */
        Integer getInteger();

        /**
         * @return the nested list that this NestedInteger holds, if it holds a nested list
         * Return null if this NestedInteger holds a single integer
         */
        List<NestedInteger> getList();
    }






    // Round 2 ====================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**

     Build this job recommendation system for LinkedIn members. example in https://www.linkedin.com/jobs/ 's "Top job picks for you"

     Some basic requirements:

     Support LinkedIn millions of jobs post and recommend top 3 jobs within a reasonable latency

     Leverage members' profile information to recommend relevant job posts into the "/jobs" page

     We only recommend to people when they have met the "basic qualification" (e.g., skill, year of experience),
     e.g., don't recommend a VP position to a student, don't recommend a java engineer position to UI designer

     Need to support personalization recommendation based on user's profile.

     */
//
//    FR:
//    top 3
//    skills based, qualification
//    5M jobs
//    100M users
//    100 new job per day
//
//
//     NFR:
//    < 200ms
//    eventual consistance
//
//
//    Assumptions:
//    ready heavy system
//    profile change
//
//
//    Capacity Estimation:
//    100M DAU  20 % visit jobs => 20M job-query-view per day
//    Avg 2 load/user = 40M requests/day ~ 500RPS ~ 3kRMPS
//
//
//    API Design:
//    GET /jobs
//    Response: {userId, rId, job:[ jobId, title, company, location, score]}
//
//
//    Data Model:
//    SQL:
//    Job Posting, members
//
//    No-SQL:
//    Precomputed job/candidates (Casaandra), memberId-_ [jobId, score, resona, generatedTime]
//    Feedback
//
//    Flow:

    // Round 3 ====================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // Explain any of your projects, a new joinee has joined the team, how would you explain the project to him/her,
    // what are the key components, how they interact with each other.



    // Round 4 ====================================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//
//    Problem Statement:
//    Sara Connor's resurgence is short of supply of weapons;
//    the mission of the team is to retrieve supplies from the outposts in a compound.
//    The compound consists of $N$ outposts (2 <= N <= 10^5) and N-1 bidirectional roads between outposts.
//    Initially, the only outpost containing a supply is outpost 1, which acts as a supply depot.
//    Supplies will be shipped to certain outposts during the mission at random times.
//    Once an outpost receives its first shipment,
//    it becomes a supply depot and will continuously have supplies forever.During the mission,
//    Sara's team will be repeatedly parachuted to a particular outpost.
//    This team has to go to the closest supply depot to retrieve the supply and then be evacuated by helicopter safely.
//    As a commander of the team, Sara will handle the intelligence for the mission.
//    Sara will keep receiving intelligence events during the whole mission.
//
//    There are two types of intelligence events: shipment and parachute.
//    When a shipment is sent to an outpost, Sara needs to clearly mark the supply depot on the map.
//    When the team is parachuted to an outpost, Sara needs to tell the team the distance between the team's parachuted outpost to the closest supply depot.
//
//    Your task is to write a program which can help Sara to do her job.
//
//
//    Input Format
//    The first line contains two integers N and M (2 <= N <= 10^5, 1 <= M <= 10^5),
//    where N is the number of outposts and M is the number of intelligence events.
//    The next N-1 lines contain pairs of integers u and v separated by a space, representing a road between outpost u and outpost v.
//    The next M lines contain intelligence events. Each event is specified as a pair of integers t, o (1 <= t <= 2, 1 <= o <= N):
//    If t = 1, Sara needs to clearly mark location o as a supply depot on her map (no explicit output is required).
//    If t = 2, the team is parachuted to location o. Print the distance between o and the closest supply depot.
//    It is guaranteed that all intelligence is correct.
//
//    Output Format
//    For each intelligence event of type t = 2, print the shortest distance to the nearest supply depot on a new line.
//
//    Sample Case
//    Input
//    5 4
//    1 2
//    2 3
//    2 4
//    4 5
//    2 1
//    2 5
//    1 2
//    2 5
//    Output
//    0
//.   3
//    2
//
//    Explanation of Example
//    Event Type (t)        Outpost (o)         Explanation
//         2                   1            Team dropped at outpost 1; outpost 1 is already a supply depot, so distance is 0
//         2                   5            Team dropped at outpost 5; outpost 1 is the only supply depot, distance is 3 (5 -> 4 -> 2 -> 1)
//         1                   2            Outpost 2 becomes a supply depot; no output required
//         2                   5            Team dropped at outpost 5; outposts 1 and 2 are supply depots, closest distance is 2 (5 -> 4 -> 2)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // Read N - 1 tree edges
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Precalculate initial distances from Depot 1
        Queue<Integer> queue = new ArrayDeque<>();
        dist[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj.get(curr)) {
                if (dist[neighbor] == Integer.MAX_VALUE) {
                    dist[neighbor] = dist[curr] + 1;
                    queue.add(neighbor);
                }
            }
        }

        // Process M intelligence events
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int outpost = Integer.parseInt(st.nextToken());

            if (type == 1) {
                // Mark location as a supply depot and update distances
                if (dist[outpost] > 0) {
                    dist[outpost] = 0;
                    queue.add(outpost);

                    while (!queue.isEmpty()) {
                        int u = queue.poll();
                        for (int v : adj.get(u)) {
                            if (dist[v] > dist[u] + 1) {
                                dist[v] = dist[u] + 1;
                                queue.add(v);
                            }
                        }
                    }
                }
            } else if (type == 2) {
                // Query distance to closest depot
                sb.append(dist[outpost]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
