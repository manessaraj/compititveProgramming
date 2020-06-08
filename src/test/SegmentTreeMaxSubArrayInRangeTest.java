package test;

import org.junit.Test;
import solutions.SegmentTreeMaxSubArrayInRange;
import solutions.SegmentTreeRangeSum;

import java.util.Scanner;

public class SegmentTreeMaxSubArrayInRangeTest {
    private String input = "3 1\n" +
            "-1 2 3\n" +
            "q 1 2\n";
    @Test
    public void testMaxSubArraySegmentTree() {
        System.out.println("Testing Begin !!!");
        SegmentTreeMaxSubArrayInRange obj = new SegmentTreeMaxSubArrayInRange();
        //Scanner
        Scanner s = new Scanner(input);
        int n = s.nextInt();
        int q = s.nextInt();
        int [] array = new int[n];
        SegmentTreeMaxSubArrayInRange.Node [] tree = new SegmentTreeMaxSubArrayInRange.Node[n*2+2];
        for(int i=0; i<n; i++) {
            array[i] = s.nextInt();
        }

        obj.buildMaxSubArrayRangeSegmentTree(tree, array, 0, n-1, 0);

        for (int i=0; i<q; i++) {
            String c = s.next();
            int l = s.nextInt();
            int r = s.nextInt();
            long res;
            if (c.equals("q")) {
                res = obj.queryMaxSubArrayRangeSegmentTree(tree, array, 0, n-1, 0, l-1, r-1).bestSum;
                System.out.println(res);
            } else {
                obj.updateMaxSubArrayInRangeSegementTree(tree, array, 0, n-1, 0, l-1, r);
            }

        }

    }
}
