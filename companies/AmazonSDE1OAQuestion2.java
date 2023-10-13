
import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;



class Result2 {

    /*
     * Complete the 'minimumTimeSpent' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY comedyReleaseTime
     *  2. INTEGER_ARRAY comedyDuration
     *  3. INTEGER_ARRAY dramaReleaseTime
     *  4. INTEGER_ARRAY dramaDuration
     */

    public static int minimumTimeSpent(List<Integer> comedyReleaseTime, List<Integer> comedyDuration, List<Integer> dramaReleaseTime, List<Integer> dramaDuration) {
        // Write your code here
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < comedyReleaseTime.size(); i++) {
            int t = comedyReleaseTime.get(i) + comedyDuration.get(i);

            for (int j = 0; j < dramaReleaseTime.size(); j++) {
                if (t < dramaReleaseTime.get(j)) {
                    min = Math.min(min, dramaReleaseTime.get(j) + dramaDuration.get(j));
                } else {
                    min = Math.min(min, t + dramaDuration.get(j));
                }
            }
        }

        for (int i = 0; i < dramaReleaseTime.size(); i++) {
            int t = dramaReleaseTime.get(i) + dramaDuration.get(i);

            for (int j = 0; j < comedyReleaseTime.size(); j++) {
                if (t < comedyReleaseTime.get(j)) {
                    min = Math.min(min, comedyReleaseTime.get(j) + comedyDuration.get(j));
                } else {
                    min = Math.min(min, t + comedyDuration.get(j));
                }
            }
        }

        return min;
    }

}

public class AmazonSDE1OAQuestion2 {
//    Amazon Prime Video has movies in the category'comedy' or 'drama'. Determine the earliest time youcan finish at least one movie from each category.The release schedule and durations of the moviesare provided.
//
//• You can start watching a movie at the time it isreleased or later.
//
//    If you begin a movie at time t, it ends at time t +
//
//    duration.
//
//• If a movie ends at time t + duration, the secondmovie can start at that time, t + duration, or later.• The movies can be watched in any order.
//
//    Example
//
//    comedyReleaseTime = [1, 4]comedyDuration = [3, 2]dramaRelease Time = [5, 2]dramaDuration : [2, 2]
//
//    Duration and release times are aligned by index.
//
//    Two of the best ways to finish watching one moviefrom each category at the earliest time are asfollows:
//
//            • Start watching comedy movie1 at time t = 1 and untilt = 1 + 3 = 4 Then, watch the drama movie2 fromtime t = 4 to t = 4 + 2 = 6 .
//
//• Start watching drama movie2 at time t = 2 and until t=2+2=4. Then, watch the comedy movie2 fromtime t = 4 to t = 4 + 2 = 6 .
//
//
//
//            The earliest finish time and also the answer is 6.
//
//    Examples that are sub-optimal include:
//
//            ⚫ Start watching a comedy movie2 at time t = 4 anduntil t = 4 + 2 = 6 Then, watch thedrama movie1 from time t = 6 to t = 6 + 2 = 8
//
//• Start watching a comedy movie 1 at time t = 1 anduntil t = 1 + 3 = 4 Then, watch thedrama movie 1 from time t = 5t * 0t = 5 + 2 = 7 .
//
//    Function Description
//
//    Complete the function minimum TimeSpent in theeditor below.
//
//    minimum TimeSpent has the following parameters:int comedyRelease Time[n]: release timesint comedyDuration[n]: durationsint dramaRelease Time[m]: release timesint dramaDuration[m]: durations
//
//            Returns
//
//    int: the earliest time you can finish watching twomovies


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int comedyReleaseTimeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> comedyReleaseTime = IntStream.range(0, comedyReleaseTimeCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int comedyDurationCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> comedyDuration = IntStream.range(0, comedyDurationCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int dramaReleaseTimeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> dramaReleaseTime = IntStream.range(0, dramaReleaseTimeCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int dramaDurationCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> dramaDuration = IntStream.range(0, dramaDurationCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result2.minimumTimeSpent(comedyReleaseTime, comedyDuration, dramaReleaseTime, dramaDuration);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

