public class HeapSort {
    private int[] heap;
    private int heapSize;

    public HeapSort() {
    }

    public HeapSort(int[] heap) {

    }

    private int left(int i) {
        return (2 * i + 1);
    }

    private int right(int i) {
        return (2 * i + 2);
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void buildHeap(int[] heap) {
        this.heapSize = heap.length;
        this.heap = heap;

        for (int i = parent(heapSize - 1); i >= 0; i--)
            minHeapify(i);
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int MIN = i;
        if (l < heapSize && heap[l] < heap[MIN])
            MIN = l;
        if (r < heapSize && heap[r] < heap[MIN])
            MIN = r;
        if (MIN == i)
            return;
        swap(heap, i, MIN);
        minHeapify(MIN);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void heapSort(int[] heap) {
        buildHeap(heap);
        for (int i = heapSize - 1; i >= 0; i--) {
            swap(heap, i, 0);
            heapSize--;
            minHeapify(0);
        }
    }
}
