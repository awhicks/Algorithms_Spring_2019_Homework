// package unionFind;

import org.junit.Test;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class FCTestRunner {


    public int[][] denseMatrix(int n, int c) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i;
        shuffleArray(nums);

        Random rnd = ThreadLocalRandom.current();
        int[] firsts = new int[c];
        if (c > 0) {
            firsts[0] = 0;
            for (int i = 1; i < c; i++)
                if (n - c + i - firsts[i-1] > 0)
                    firsts[i] = firsts[i-1] + rnd.nextInt(n - c + i - firsts[i-1]) + 1;
                else
                    firsts[i] = firsts[i-1] + 1;
        }

        int[][] out = new int[n][n];
        for (int f = 0; f < c; f++) {
            int first = firsts[f];
            int last = (f + 1 < c) ? firsts[f+1] - 1 : n - 1;
            for (int i = first; i <= last; i++) {
                for (int j = first; j <= last; j++) {
                    out[nums[i]][nums[j]] = 1;
                    out[nums[j]][nums[i]] = 1;
                }
            }
        }
        turnOnDiag(out);
        return out;
    }

    public int[][] sparseMatrix(int n, int c) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i;
        shuffleArray(nums);

        Random rnd = ThreadLocalRandom.current();
        int[] firsts = new int[c];
        if (c > 0) {
            firsts[0] = 0;
            for (int i = 1; i < c; i++)
                if (n - c + i - firsts[i-1] > 0)
                    firsts[i] = firsts[i-1] + rnd.nextInt(n - c + i - firsts[i-1]) + 1;
                else
                    firsts[i] = firsts[i-1] + 1;
        }

        int[][] out = new int[n][n];
        for (int f = 0; f < c; f++) {
            int first = firsts[f];
            int last = (f + 1 < c) ? firsts[f+1] - 1 : n - 1;
            for (int i = first; i < last; i++) {
                out[nums[i]][nums[i+1]] = 1;
                out[nums[i+1]][nums[i]] = 1;
            }
        }
        turnOnDiag(out);
        return out;
    }

    public int[][] sparseCyclicMatrix(int n, int c) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i;
        shuffleArray(nums);

        Random rnd = ThreadLocalRandom.current();
        int[] firsts = new int[c];
        if (c > 0) {
            firsts[0] = 0;
            for (int i = 1; i < c; i++)
                if (n - c + i - firsts[i-1] > 0)
                    firsts[i] = firsts[i-1] + rnd.nextInt(n - c + i - firsts[i-1]) + 1;
                else
                    firsts[i] = firsts[i-1] + 1;
        }

        int[][] out = new int[n][n];
        for (int f = 0; f < c; f++) {
            int first = firsts[f];
            int last = (f + 1 < c) ? firsts[f+1] - 1 : n - 1;
            for (int i = first; i < last; i++) {
                out[nums[i]][nums[i+1]] = 1;
                out[nums[i+1]][nums[i]] = 1;
            }
            out[nums[first]][nums[last]] = 1;
            out[nums[last]][nums[first]] = 1;
        }
        turnOnDiag(out);
        return out;
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public void turnOnDiag(int[][] ar) {
        int n =  ar.length;
        for (int i = 0; i < n; i++)
            ar[i][i] = 1;
    }

    public String matrixToString(int[][] mat) {
        String out = "\n";
        for (int[] row : mat)
            out += Arrays.toString(row) + "\n";
        return out;
    }

    @Test(timeout = 10000)
    public void testFindCircleNumCorrectness() {
        for (int n = 1; n < 10; n++) {
            for (int c = 1; c <= n; c++) {
                int[][] m1 = denseMatrix(n, c);
                int[][] m2 = sparseMatrix(n, c);
                int[][] m3 = sparseCyclicMatrix(n, c);
                assertEquals(matrixToString(m1), c, new FriendCircles().findCircleNum(m1));
                assertEquals(matrixToString(m2), c, new FriendCircles().findCircleNum(m2));
                assertEquals(matrixToString(m3), c, new FriendCircles().findCircleNum(m3));
            }
        }
    }

    @Test(timeout = 20000)
    public void testFindCircleNumRuntime() {
        for (int n = 4000; n <= 4000; n++) {
            for (int c = 1; c <= n; c+= 3300) {
                System.out.println("Testing " + n + " " + c);
                int[][] m1 = denseMatrix(n, c);
                assertEquals(c, new FriendCircles().findCircleNum(denseMatrix(n, c)));
                assertEquals(c, new FriendCircles().findCircleNum(sparseMatrix(n, c)));
                assertEquals(c, new FriendCircles().findCircleNum(sparseCyclicMatrix(n, c)));
            }
        }
    }

    @Test(timeout=10000)
    public void testSimpleString() {
        int[][] arr = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1},
        };
        assertEquals(1, new FriendCircles().findCircleNum(arr));
    }


}
