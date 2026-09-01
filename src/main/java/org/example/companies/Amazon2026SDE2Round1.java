package org.example.companies;

import java.util.List;
import java.util.PriorityQueue;

public class Amazon2026SDE2Round1 {
    // Amazon usually has multiple instances hosting a service. Each instance has its own logs, each line with a timestamp and the relevant text.
// We want to build a solution that allows a developer to read the logs of the entire service fleet, sorted by time.
// In other words, read the logs of all the hosts in the same page.
// You can assume you just need to write the core algorithm, there is no need of dealing with files,
// network calls or displaying the result on screen.
// You can decide the signature of the function, its input and output types.
//
// For example
// HOST-1 LOGS:
// 2025-01-01 - Service start-up.
// 2025-01-02 - Received request AAA.
// 2025-01-04 - Received request CCC.
// 2025-01-10 - Service shut-down.
//
// HOST-2 LOGS:
// 2025-01-01 - Service start-up.
// 2025-01-03 - Received request BBB.
// 2025-01-05 - Received request DDD.
// 2025-01-11 - Service shut-down.
//
// The function should return:
// 2025-01-01 - Service start-up.
// 2025-01-01 - Service start-up.
// 2025-01-02 - Received request AAA.
// 2025-01-03 - Received request BBB.
// 2025-01-04 - Received request CCC.
// 2025-01-05 - Received request DDD.
// 2025-01-10 - Service shut-down.
// 2025-01-11 - Service shut-down.


    class Amazon {

        private static class LogEntry {
            String log;
            String timestamp;
            int hostIndex;
            int logIndex;

            LogEntry(String log, String timestamp, int hostIndex, int logIndex) {
                this.log = log;
                this.timestamp = timestamp;
                this.hostIndex = hostIndex;
                this.logIndex = logIndex;
            }
        }

        public List<String> mergeLogs(List<List<String>> hostLogs) {
            List<String> result = new java.util.ArrayList<>();

            if (hostLogs == null || hostLogs.isEmpty()) {
                return result;
            }

            PriorityQueue<LogEntry> minHeap = new PriorityQueue<>((a, b) -> {
                int cmp = a.timestamp.compareTo(b.timestamp);
                if (cmp != 0) {
                    return cmp;
                }
                if (a.hostIndex != b.hostIndex) {
                    return Integer.compare(a.hostIndex, b.hostIndex);
                }
                return Integer.compare(a.logIndex, b.logIndex);
            });

            for (int host = 0; host < hostLogs.size(); host++) {
                List<String> logs = hostLogs.get(host);
                if (logs != null && !logs.isEmpty()) {
                    minHeap.offer(new LogEntry(logs.get(0), extractTimestamp(logs.get(0)), host, 0));
                }
            }

            while (!minHeap.isEmpty()) {
                LogEntry current = minHeap.poll();
                result.add(current.log);

                int nextIndex = current.logIndex + 1;
                List<String> currentHostLogs = hostLogs.get(current.hostIndex);

                if (nextIndex < currentHostLogs.size()) {
                    String nextLog = currentHostLogs.get(nextIndex);
                    minHeap.offer(new LogEntry(nextLog, extractTimestamp(nextLog), current.hostIndex, nextIndex));
                }
            }

            return result;
        }

        private String extractTimestamp(String logLine) {
            int separatorIndex = logLine.indexOf(" - ");
            if (separatorIndex == -1) {
                return logLine;
            }
            return logLine.substring(0, separatorIndex);
        }
    }

}
