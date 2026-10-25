package org.example.companies;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class ForwardNetworks {

    // Round 1: Implement toJson(Object) method to convert a Java object to a pretty JSON string representation.
// ===========================================================================================================================================================
//    Instructions
//
//    Implement toJson(Object).
//
//    Click the green [ ▶ Run Code ] button to see the expected output for the defined test cases.
//
//    Be sure to support at least:
//            null → JSON null
//    Boolean → JSON true or false
//    Integer → JSON number
//    Double → JSON number
//    String → JSON string
//    Collection → JSON array
//    Map → JSON object
//
//    Each JSON array value and JSON object key should begin on a new line, indented one level deeper than the containing array/object. The closing bracket/brace should also be on its own line, indented like the line on which the array/object began.
//
//    The JSON grammar is diagrammed at json.org.


    /**
     * Formats a value (possibly {@code null}) as a pretty JSON string.
     *
     * <p>Each JSON array value should begin on a new line, indented two spaces
     * further than its container. The same goes for JSON object keys.
     *
     * <p>Supported types include {@link Boolean}, {@link Integer}, {@link Double},
     * {@link String}, {@link Collection}, and {@link Map}.
     */
    public static String toJson(Object value) {
        StringBuilder sb = new StringBuilder();
        solve(value, 0, sb);
        return sb.toString();
    }

    public static void solve(Object v, int level, StringBuilder sb) {
        if (v == null) {
            sb.append("null");
        } else if (v instanceof Boolean) {
            sb.append(v);
        } else if (v instanceof Integer) {
            sb.append(v);
        } else if (v instanceof Double) {
            double d = (Double) v;
            sb.append(Double.isFinite(d)
                      ? Double.toString(d)
                      : "null");
        } else if (v instanceof Number) {
            sb.append(v);
        } else if (v instanceof String) {
            stringHelper((String) v, sb);
        } else if (v instanceof Collection) {
            sb.append("[");
            Iterator it = ((Collection) v).iterator();
            while (it.hasNext()) {
                newLineHelper(level + 1, sb);
                solve(it.next(), level + 1, sb);
                if (it.hasNext()) {
                    sb.append(',');
                }
            }
            newLineHelper(level, sb);
            sb.append(']');
        } else if (v instanceof Map) {
            sb.append('{');
            Iterator<? extends Map.Entry<?, ?>> it = ((Map<?, ?>) v).entrySet()
                    .iterator();

            while (it.hasNext()) {
                Map.Entry<?, ?> e = it.next();
                newLineHelper(level + 1, sb);
                stringHelper(String.valueOf(e.getKey()), sb);
                sb.append(": ");
                solve(e.getValue(), level + 1, sb);
                if (it.hasNext()) {
                    sb.append(',');
                }
            }

            newLineHelper(level, sb);
            sb.append('}');
        }
    }


    public static void stringHelper(String s,
                                    StringBuilder sb) {
        sb.append('"');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                default:
                    sb.append(c);
            }
        }

        sb.append('"');
    }

    public static void newLineHelper(int level,
                                     StringBuilder sb) {
        sb.append('\n');
        for (int i = 0; i < level; i++) {
            sb.append("  "); // 2 space
        }
    }






    // Round 2:
    // ===========================================================================================================================================================
    //
    // Problem Statement
    // You are building an access-control system for a network management platform.
    //
    // Networks are organized in a hierarchy. Each network has a unique ID and an optional parent ID.
    // If the parent ID is null, the network is a root.
    // Users can be assigned roles at specific networks, and those roles propagate to descendants.
    //
    // Role propagation rules
    // A user’s effective role at a network is the higher-priority role between their direct assignment there and the
    // role inherited from its parent.
    // Priority is ADMIN > EDITOR > VIEWER.
    // If neither role exists, return "NONE".
    //
    // Implement RoleResolver
    // void addNetwork(String id, String parentId) — Register a network; parentId may be null.
    // void assignRole(String userId, String networkId, String role) — Assign a role to a user at a network.
    // String getEffectiveRole(String userId, String networkId) — Return the user’s effective role at that network.
    //
    // Input and output
    // You are given a list of (id, parentId) networks,
    // a list of (userId, networkId, role) assignments,
    // and a list of (userId, networkId) queries.
    // Return a list of effective-role strings in query order.
    //
    // Constraints: Up to $10^4$ networks,
    // $5 \times 10^4$ assignments,
    // and $5 \times 10^4$ queries.
    // IDs are strings;
    // roles are ADMIN, EDITOR, or VIEWER.
    // The hierarchy has no cycles.
    //
    // Example
    // Networks:
    // org1         -> null
    // net-east     -> org1
    // net-west     -> org1
    // ws-east-prod -> net-east
    // ws-east-dev  -> net-east
    //
    // Assignments:
    // (alice, org1, VIEWER)
    // (alice, net-east, EDITOR)
    // (bob, ws-east-prod, ADMIN)
    // (alice, ws-east-dev, ADMIN)
    //
    // Queries:
    // (alice, org1)
    // (alice, net-east)
    // (alice, ws-east-prod)
    // (alice, ws-east-dev)
    // (bob, ws-east-prod)
    // (bob, org1)
    // (bob, net-west)
    //
    // Output:
    // ["VIEWER", "EDITOR", "EDITOR", "ADMIN", "ADMIN", "NONE", "NONE"]

    static class RoleResolver {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Map<String, Integer>> assignments = new HashMap<>();
        private final Map<String, Map<String, Integer>> cache = new HashMap<>();
        // was asked to improve it further, so I used cache, did not implement, just gave the idea,
        // here I have implemented the cache in getEffectiveRole2 method
        private final String[] roles = {"NONE", "VIEWER", "EDITOR", "ADMIN"};

        public void addNetwork(String id, String parentId) {
            parent.put(id, parentId);
        }

        public void assignRole(String userId, String networkId, String role) {
            Map<String, Integer> userRoles =
                    assignments.computeIfAbsent(userId, key -> new HashMap<>());

            // Keep the strongest role if the same location is assigned twice.
            userRoles.merge(networkId, priority(role), Math::max);
        }

        public String getEffectiveRole(String userId, String networkId) {
            Map<String, Integer> userRoles = assignments.get(userId);
            int best = 0;

            for (String current = networkId;
                 current != null;
                 current = parent.get(current)) {
                if (userRoles != null) {
                    best = Math.max(best, userRoles.getOrDefault(current, 0));
                }
            }
            return roles[best];
        }

        private int priority(String role) {
            switch (role) {
                case "ADMIN":
                    return 3;
                case "EDITOR":
                    return 2;
                default:
                    return 1; // VIEWER
            }
        }

        // Implemented cache to improve the performance of getEffectiveRole method
        public String getEffectiveRole2(String userId, String networkId) {
            Map<String, Integer> userRoles = assignments.get(userId);
            if (userRoles == null) return "NONE";

            Map<String, Integer> userCache =
                    cache.computeIfAbsent(userId, key -> new HashMap<>());
            List<String> path = new ArrayList<>();
            String current = networkId;

            // Walk upward only until reaching a cached result or the root.
            while (current != null && !userCache.containsKey(current)) {
                path.add(current);
                current = parent.get(current);
            }

            int best = current == null ? 0 : userCache.get(current);

            // Fill answers from ancestor to descendant for future queries.
            for (int i = path.size() - 1; i >= 0; i--) {
                String network = path.get(i);
                best = Math.max(best, userRoles.getOrDefault(network, 0));
                userCache.put(network, best);
            }

            return roles[best];
        }
    }


    // Round 2A: HashMap key collision, AtomicInteger and LongAdder, Pagination query
    // ===========================================================================================================================================================

    //
    public final class UserKey {
        private String id;

        public UserKey(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof UserKey other && Objects.equals(id, other.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    // question here: How to fix it
    // answer: make id field final, so it cannot be changed after the object is created.
    // The issue arises because the hashCode of the UserKey object is based on its id field.
    // When you change the id after putting the object into the HashMap,
    // the hashCode changes, and the HashMap can no longer find the object in the same bucket.
    // To fix this, you should avoid mutating the fields that are used in equals and hashCode
    // after the object has been used as a key in a HashMap.
    // One way to fix this is to make the id field final,
    // so it cannot be changed after the object is created.
    private void test1() {
        UserKey key = new UserKey("A");
        Map<UserKey, String> map = new HashMap<>();
        map.put(key, "value");
        key.setId("B");
        System.out.println(map.get(key));
    }

    private void test2() {
        // diff between these 2
        AtomicInteger counter = new AtomicInteger();
        counter.incrementAndGet();

        LongAdder counter2 = new LongAdder();
        counter2.increment();
    }

    private void test3() {
        // Pagination query
        // SELECT * from events ORDER BY created_at LIMIT 50 OFFSET 1000000;
        // how to improve this query

        // Answer: This query is slow because OFFSET 1000000 forces the database to read and sort 1,000,050 rows,
        // discard the first 1,000,000, and return only the last 50.

        // If you are navigating page-by-page (e.g., infinite scrolling or "Next" button),
        // filter by the timestamp of the last item from the previous page instead of using OFFSET.

        // Updated Query: SELECT * FROM events
        //                WHERE created_at > '2026-03-29 10:15:00' -- value from the last row of the previous batch
        //                ORDER BY created_at ASC
        //                LIMIT 50;

        // Handling Duplicate Timestamps
        // If multiple events can have the exact same created_at, append a unique column (like id) to break ties:
        // Updated Query: SELECT * FROM events
        //                WHERE (created_at, id) > ('2026-03-29 10:15:00', 984521)
        //                ORDER BY created_at ASC, id ASC
        //                LIMIT 50;

        // Index Required:
        // CREATE INDEX idx_events_created_at_id ON events (created_at, id);
    }
}
