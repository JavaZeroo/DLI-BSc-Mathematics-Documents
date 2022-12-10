import java.util.Arrays;

public class HeapSortMain {
    public static void main(String[] args) {
        int[] x = new int[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 20);
        }
        // int a[] ={7, 8, 2, 3, 6, 10, 11, 13, 7};
        HeapSort heapSort = new HeapSort();
        System.out.println(Arrays.toString(x));
        heapSort.heapSort(x);
        System.out.println(Arrays.toString(x));

    }
}
