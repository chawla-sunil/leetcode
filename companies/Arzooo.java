
import java.util.LinkedList;
import java.util.Queue;

public class Arzooo {
//    question is find the shortest path
//    arr[][] = {
//        {'.','.','.'},
//        {'.','#','.'}
//        {'.','.','.'}
//    }


    public int min(char[][] mat, Point src, Point dest) {

        if (mat[src.x][src.y] != '.' || mat[dest.x][dest.y] != '.') {
            return -1;
        }

        int m = mat.length;
        int n = mat[0].length;
        boolean[][] visited = new boolean[mat.length][mat[0].length];

        visited[src.x][src.y] = true;

        Queue<Point> q = new LinkedList<>();
        Point s = new Point(0, 0, 0);
        q.add(s);

        while (!q.isEmpty()) {
            Point curr = q.peek();

            if (curr.x == dest.x && curr.y == dest.y) {
                return curr.d;
            }
            q.remove();

            int[] row1 = {-1, 0, 0, 1};
            int[] col1 = {0, -1, 1, 0};
            for (int i = 0; i < 4; i++) {
                int row = curr.x + row1[i];
                int col = curr.y + col1[i];

                if ((row >= 0 && row < m && col >= 0 && col < n) && mat[row][col] == '.' && !visited[row][col]) {
                    visited[row][col] = true;
                    Point node = new Point(row, col, curr.d + 1);
                    q.add(node);
                }
            }
        }

        return -1;
    }
}
class Point{
    int x;
    int y;
    int d;

    public Point(int row, int col, int d) {
        this.x = row;
        this.y = col;
        this.d = d;
    }
}
