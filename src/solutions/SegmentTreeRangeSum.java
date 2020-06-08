package solutions;

public class SegmentTreeRangeSum implements Solution{
    /*
     * This tree is min range tree with range limits being a and b.
     * There is way to convert this tree into more generic tree,
     * if we abstract the operation.
     * */
    public void buildRangeSumSegmentTree(int [] tree, int [] array, int a, int b, int node) {

        if (a == b) {
            /* This is leaf*/
            tree[node] = array[a];
        } else {
            int mid = (a + b) >> 1;
            buildRangeSumSegmentTree(tree, array, a, mid, node*2+1);
            buildRangeSumSegmentTree(tree, array, mid + 1, b, node*2 + 2);
            tree[node] = tree[node*2+1] + tree[node*2+2];
        }
        return;
    }

    private void propogateLaziness(int [] tree, int [] lazy, int node, int a, int b) {
        if (lazy[node] != 0){
            tree[node] += (b-a+1)*lazy[node];
            if (a != b) {
                lazy[node * 2 + 1] = lazy[node];
                lazy[node * 2 + 2] = lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public int queryRangeSumSegmentTree(int [] tree, int [] array, int a, int b, int node, int i, int j, int [] lazy) {
        if (a > b || a > j || b < i) {
            /* Out of Range */
            return 0;
        }

        propogateLaziness(tree, lazy, node, a, b);

        if ( a >= i && b <= j) {
            return tree[node];
        } else {
            int mid = (a + b) >> 1;
            int sumLeft = queryRangeSumSegmentTree(tree, array, a, mid, node*2+1, i, j, lazy);
            int sumRight = queryRangeSumSegmentTree(tree, array, mid+1, b, node*2 + 2, i, j, lazy);
            return sumLeft + sumRight;
        }
    }


    public void updateRangeSumSegementTree(int [] tree, int [] array, int a, int b, int node, int i, int j, int val, int [] lazy) {
        if ( a > b || a > i || b < i) {
            return;
        }

        propogateLaziness(tree, lazy, node, a, b);
        if ( a >= i && b <= j ) {
            tree[node] += (b-a +1)*val;
            if (a != b) {
                lazy[node*2+1] = val;
                lazy[node*2+2] = val;
            }
            return;
        } else {
            int mid = (a+b) >> 1; // Taking away one
            updateRangeSumSegementTree(tree, array, a, mid, node*2+1, i, j, val, lazy);
            updateRangeSumSegementTree(tree, array, mid+1, b, node*2+2, i, j, val, lazy);
            tree[node] = tree[node*2+1] +  tree[node*2+2];
        }
        return;
    }



    @Override
    public void run(String string) {

    }
}
