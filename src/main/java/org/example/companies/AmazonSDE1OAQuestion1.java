package org.example.companies;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;


class Result {

    /*
     * Complete the 'findMinimumNumberOfPages' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY pages
     *  2. INTEGER days
     */

    public static int findMinimumNumberOfPages(List<Integer> pages, int days) {
        // Write your code here
        int maxPages = pages.stream().mapToInt(Integer::intValue).max().orElse(0);
        int result = -1;
        for (int x = 1; x <= maxPages; x++) {
            if (canFinishReading(pages, days, x)) {
                result = x;
                break;
            }
        }
        return result;
    }
    public static boolean canFinishReading(List<Integer> pages, int days, int x) {
        int totalDays = 0;

        for (int pageCount: pages) {
            int dasyNeeded = pageCount / x;

            if (pageCount % x != 0) {
                dasyNeeded += 1;
            }
            totalDays += dasyNeeded;
        }
        return totalDays <= days;
    }

}
public class AmazonSDE1OAQuestion1 {
//    A student is preparing for a test from Amazon. Academy for a scholarship.
//    The student is required to completely read n chapters for the test where the Ah chapter has pages[i] number of pages. The chapters are read in increasing order of the index. Each day the student can either read till the end of a chapter or at most x pages, whichever is minimum. The number of pages remaining to read decreases by x in the later case.

//    For example, if pages = [5, 3, 4] and x = 4,

//        • Day 1: The student reads 4 pages of the first chapter -pages remaining = [1, 3, 4]

//            • Day 2: The student can only read till the end of the first chapter-pages remaining [0, 3, 4]
//
//• Day 3: The student can read either till the end of the chapter or x = 4 pages, since 3 < 4, the student reads till the end of the chapter 2 - pages remaining = [0, 0, 4]
//
//            • Day 4: The student reads all the 4 pages of the last chapter - pages remaining - [0, 0, 0]
//
//    The test will be given in days number of days from now. Find the minimum number of pages, x, which the student should read each day to finish all pages of all chapters within days number of days. If it is not possible to finish these chapters in days number of days, return -1.
//
//
//    Note: In one day, the student cannot read pages of more than one chapter. Thus, if a chapter finishes, the next chapter starts only on the next day even if the number of pages read is less than x.
//
//            Example
//
//    There are n = 3 chapters, pages = [2, 4, 3], and days = 4.
//
//
//
//    Thus, in 4 days, the student can read all pages of all chapters, and finish. If x is less than 3, it is impossible to read all chapters in 4 days. Thus, the minimum number of pages read each day is 3.
//
//    Function Description
//
//    Complete the function
//
//    findMinimum NumberOfPages in the editor below.
//
//    findMinimum NumberOfPages has the following parameters:
//
//    int pages[n]: the number of pages in each chapter
//
//    int days. the maximum number of days
//
//            Returns
//
//    int: the minimum number of pages to be read each day, or -1 if it is not possible to finish





    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int pagesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> pages = IntStream.range(0, pagesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int days = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.findMinimumNumberOfPages(pages, days);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
