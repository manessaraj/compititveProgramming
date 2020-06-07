package solutions;

public class SegmentTreeMinRangeQuery implements Solution {

    /*
    * Pay Attention to limits of size etc. This is problematic.
    * You need to consider a number, it range and all operations on it and see what would be range of each result.
    * This where the catch is for partial acceptance of results.
    * */



    /*
    * This tree is min range tree with range limits being a and b.
    * There is way to convert this tree into more generic tree,
    * if we abstract the operation.
    * */
    public int buildMinRangeSegmentTree(int [] tree, int [] array, int a, int b, int node) {

        if (a == b) {
            /* This is leaf*/
            tree[node] = array[a];
        } else {
            int mid = (a + b) >> 1;
            int minLeft = buildMinRangeSegmentTree(tree, array, a, mid, node*2+1);
            int minRight = buildMinRangeSegmentTree(tree, array, mid + 1, b, node*2 + 2);
            tree[node] = Math.min(minLeft, minRight);
        }
        return tree[node];
    }

    public int queryMinRangeSegmentTree(int [] tree, int [] array, int a, int b, int node, int i, int j) {
        if (a > b || a > j || b < i) {
            /* Out of Range */
            return Integer.MAX_VALUE;
        } else if ( a >= i && b <= j) {
            return tree[node];
        } else {
            int mid = (a + b) >> 1;
            int minLeft = queryMinRangeSegmentTree(tree, array, a, mid, node*2+1, i, j);
            int minRight = queryMinRangeSegmentTree(tree, array, mid+1, b, node*2 + 2, i, j);
            return Math.min(minLeft, minRight);
        }
    }


    public void updateMinRangeSegementTree(int [] tree, int [] array, int a, int b, int node, int i, int val) {
        if ( a > b || a > i || b < i) {
            return;
        } else if ( a == b ) {
            tree[node] =  val;
        } else {
            int mid = (a+b) >> 1; // Taking away one
            updateMinRangeSegementTree(tree, array, a, mid, node*2+1, i, val);
            updateMinRangeSegementTree(tree, array, mid+1, b, node*2+2, i, val);
            tree[node] = Math.min(tree[node*2+1], node*2+2);
        }
        return;
    }



    @Override
    public void run(String string) {

    }
}
