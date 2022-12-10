import java.util.Arrays;
import java.util.concurrent.locks.StampedLock;

public class sort {
    public static void main(String[] args) {
        int[] x = new int[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 20);
        }
        char[] c = { 'b', 'f', 'q', 'a', 'c', 'k', 'z' };
        System.out.println(Arrays.toString(x));
        // InsertSort(x);
        // mergeSort(c);
        // heapSort(x);
        quickSort(x);
        System.out.println(Arrays.toString(x));

    }

    public static void InsertSort(int[] a) {
        for(int i = 1; i < a.length; i++) {
            for(int j = i; j>0; j--) {
                if(a[j] < a[j - 1]) swap(a, j);
                else break;
            }
        }
    }

    public static void swap(int[] a, int i) {
        int temp = a[i];
        a[i] = a[i - 1];
        a[i - 1] = temp;
    }

    public static void mergeSort(char[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    private static void mergeSort(char[] a, int l, int r) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m + 1, r);
        merge(a, l, m, r);
    }

    private static void merge(char[] a, int l, int m, int r) {
        int l_sum = m - l + 1;
        int r_sum = r - m;
        char[] L = new char[l_sum + 1];
        char[] R = new char[r_sum + 1];
        L[l_sum] = Character.MAX_VALUE;
        R[r_sum] = Character.MAX_VALUE;
        System.arraycopy(a, l, L, 0, l_sum);
        System.arraycopy(a, m + 1, R, 0, r_sum);
        int j = 0, k = 0;
        for (int i = l; i <= r; i++) {
            if (L[j] < R[k])
                a[i] = L[j++];
            else
                a[i] = R[k++];
        }
    }
    public static int left(int i){
        return 2 * i + 1;
    }
    public static int right(int i){
        return 2 * i + 2;
    }
    public static int parent(int i){
        return (i - 1) / 2;
    }
    public static void heapSort(int[] a){
        buildHeap(a);
        System.out.println(Arrays.toString(a));
        heapSort(a, a.length);
    }
    public static void heapSort(int[] a, int heapSize){
        if(heapSize == 0) return;
        swap(a, 0, heapSize - 1);
        minHeapify(a, --heapSize, 0);
        heapSort(a, heapSize);
    }
    public static void buildHeap(int[] a){
        for(int i = parent(a.length - 1); i >= 0; i--){
            minHeapify(a, a.length, i);
        }
    }
    public static void minHeapify(int[] a, int heapSize, int i){
        int l = left(i), r = right(i);
        int min = i;
        if(l < heapSize && a[min] > a[l])
            min = l;
        if(r < heapSize && a[min] > a[r])
            min = r;
        if(min == i)
            return;
        swap(a, i, min);
        minHeapify(a, heapSize, min);
    }

    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void quickSort(int[] a){
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int start, int end){
        if(start >= end) return;
        int l = start, r= end;
        int key = a[start];
        while(l < r){
            while(l < r && a[r] >= key){
                r--;
            }
            a[l] = a[r];
            while(l < r && a[l] <= key){
                l++;
            }
            a[r] = a[l];
        }
        a[l] = key;
        quickSort(a, start, l);
        quickSort(a, l + 1, end);
    }
}
