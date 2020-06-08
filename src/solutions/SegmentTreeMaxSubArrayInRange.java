package solutions;

public class SegmentTreeMaxSubArrayInRange implements Solution{

    public class Node {
        long totalSum;
        public long bestSum;
        long prefixSum;
        long suffixSum;

        Node(int val, int sum) {
            this.prefixSum = val;
            this.suffixSum = val;
            this.bestSum = val;
            this.totalSum = sum;
        }

        Node(){}
    }

    private final Node ZERO_NODE = new Node();
    private final Node MIN_NODE = new Node(Integer.MIN_VALUE, 0);

    public void buildMaxSubArrayRangeSegmentTree(Node [] tree, int [] array, int a, int b, int node) {

        if (a == b) {
            /* This is leaf*/
            tree[node] = new Node();
            tree[node].totalSum = array[a];
            tree[node].bestSum = array[a];
            tree[node].prefixSum = array[a];
            tree[node].suffixSum = array[a];
        } else {
            int mid = (a + b) >> 1;
            buildMaxSubArrayRangeSegmentTree(tree, array, a, mid, node * 2 + 1);
            buildMaxSubArrayRangeSegmentTree(tree, array, mid + 1, b, node * 2 + 2);
            tree[node] = new Node();
            Node left = tree[node*2+1];
            Node right = tree[node*2+2];
            if (left == null) {
                left = ZERO_NODE;
            }
            if (right == null) {
                right = ZERO_NODE;
            }
            tree[node].totalSum = left.totalSum + right.totalSum;
            tree[node].suffixSum = Math.max(right.suffixSum, right.totalSum + left.suffixSum);
            tree[node].prefixSum = Math.max(left.prefixSum, left.totalSum + right.prefixSum);
            tree[node].bestSum = Math.max(Math.max(left.bestSum, right.bestSum), left.suffixSum + right.prefixSum);
        }
    }

    public Node queryMaxSubArrayRangeSegmentTree(Node [] tree, int [] array, int a, int b, int node, int i, int j) {
        if (a > b || a > j || b < i) {
            /* Out of Range */
            return MIN_NODE;
        } else if ( a >= i && b <= j) {
            return tree[node];
        } else {
            int mid = (a + b) >> 1;
            Node bestLeft = queryMaxSubArrayRangeSegmentTree(tree, array, a, mid, node*2+1, i, j);
            Node bestRight = queryMaxSubArrayRangeSegmentTree(tree, array, mid+1, b, node*2 + 2, i, j);
            Node t = new Node();
            t.bestSum =  Math.max(Math.max(bestLeft.bestSum, bestRight.bestSum), bestLeft.suffixSum + bestRight.prefixSum);
            t.totalSum = bestLeft.totalSum + bestRight.totalSum;
            t.suffixSum = Math.max(bestRight.suffixSum, bestRight.totalSum + bestLeft.suffixSum);
            t.prefixSum = Math.max(bestLeft.prefixSum, bestLeft.totalSum + bestRight.prefixSum);
            return t;
        }
    }


    public void updateMaxSubArrayInRangeSegementTree(Node [] tree, int [] array, int a, int b, int node, int i, int val) {
        if ( a > b || a > i || b < i) {
            return;
        } else if ( a == b ) {
            tree[node].totalSum = val;
            tree[node].bestSum = val;
            tree[node].prefixSum = val;
            tree[node].suffixSum = val;
        } else {
            int mid = (a+b) >> 1; // Taking away one
            updateMaxSubArrayInRangeSegementTree(tree, array, a, mid, node*2+1, i, val);
            updateMaxSubArrayInRangeSegementTree(tree, array, mid+1, b, node*2+2, i, val);
            Node left = tree[node*2+1];
            Node right = tree[node*2+2];
            tree[node].totalSum = left.totalSum + right.totalSum;
            tree[node].suffixSum = Math.max(right.suffixSum, right.totalSum + left.suffixSum);
            tree[node].prefixSum = Math.max(left.prefixSum, left.totalSum + right.prefixSum);
            tree[node].bestSum = Math.max(Math.max(left.bestSum, right.bestSum), left.suffixSum + right.prefixSum);
        }
        return;
    }



    @Override
    public void run(String string) {

    }

}
