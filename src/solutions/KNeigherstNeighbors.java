package solutions;

import java.util.Random;

public class KNeigherstNeighbors implements Solution {
    public void swap(int [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return;
    }

    public void randomizePartition(int []distance, int [] original, int l, int r) {
        int range = r-l+1;
        if (range == 0) {
            return;
        }
        Random random = new Random();
        int pivot = random.nextInt(range) + l;
        swap(distance, r, pivot);
        swap(original, r, pivot);
    }

    public int partition(int [] distances, int [] original, int l, int r) {
        randomizePartition(distances, original, l, r);
        int x = distances[r];
        int i = l-1;
        int j;
        for (j=l; j<r; j++) {
            if (distances[j] < x) {
                i += 1;
                swap(distances, i, j);
                swap(original, i, j);
            }
        }

        swap(distances, i+1, j);
        swap(distances, i+1, j);
        return i+1;
    }

    public void kSort(int [] distances, int [] original, int l, int r, int k) {
        int p = partition(distances, original, l, r);
        if (p == k) {
            return;
        } else if (p < k){
            kSort(distances, original, p+1, r, k);
        } else {
            kSort(distances, original, l, p, k);
        }
        return;
    }


    public int[][] kClosest(int[][] points, int K) {

        int [] distances = new int[points.length];
        int [] positions = new int[points.length];
        for (int i=0; i<points.length; i++) {
            distances[i] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
            positions[i] = i;
        }

        kSort(distances, positions, 0, distances.length-1, K);

        int [][] output = new int[K][2];
        int idx = 0;
        for (int j=0; j<K; j++) {
            int index = positions[j];
            output[idx][0] = points[index][0];
            output[idx][1] = points[index][1];
            idx += 1;
        }
        return output;

    }

    @Override
    public void run(String string) {
        
    }
}



