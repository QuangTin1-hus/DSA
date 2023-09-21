package sortstrategy;

public class MergeSort implements AdvancedISort {
    @Override
    public void sort(int[] data, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(data, l, m);
            sort(data, m + 1, r);
            merge(data, l, m, r);
        }
    }

    public void merge(int[] data, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i = 0; i < n1; ++i)
            left[i] = data[low + 1];
        for (int j = 0; j < n2; ++j)
            right[j] = data[mid + 1 + j];
        int i = 0;
        int j = 0;
        int k = low;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                data[k] = left[i];
                i++;
            } else {
                data[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            data[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            data[k] = right[j];
            j++;
            k++;
        }
    }
}
