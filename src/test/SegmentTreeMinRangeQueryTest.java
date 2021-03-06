package test;

import org.junit.Test;
import solutions.SegmentTreeMinRangeQuery;

import java.util.Scanner;

public class SegmentTreeMinRangeQueryTest {

    private String input = "5 5\n" +
            "1 5 2 4 3\n" +
            "q 1 5\n" +
            "q 1 3\n" +
            "q 3 5\n" +
            "u 3 6\n" +
            "q 1 5";
    @Test
    public void testMinSegmentTree() {
        System.out.println("Testing Begin !!!");
        SegmentTreeMinRangeQuery obj = new SegmentTreeMinRangeQuery();
        //Scanner
        Scanner s = new Scanner(input);
        int n = s.nextInt();
        int q = s.nextInt();
        int [] array = new int[n];
        int [] tree = new int[n*2+2];
        for(int i=0; i<n; i++) {
            array[i] = s.nextInt();
        }

        obj.buildMinRangeSegmentTree(tree, array, 0, n-1, 0);

        for (int i=0; i<q; i++) {
            String c = s.next();
            int l = s.nextInt();
            int r = s.nextInt();
            int res;
            if (c.equals("q")) {
                res = obj.queryMinRangeSegmentTree(tree, array, 0, n-1, 0, l-1, r-1);
                System.out.println(res);
            } else {
                obj.updateMinRangeSegementTree(tree, array, 0, n-1, 0, l-1, r);
            }

        }

    }
}
