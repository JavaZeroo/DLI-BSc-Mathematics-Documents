import java.util.Arrays;

public class hw3_uid {
    public static void main(String[] args) {
        double[] array = new double[10];
        for(int i = 0; i < array.length; i++) {
            array[i] =(int)(Math.random()*10);
        }
        double[] array2 = new double[10];
        for(int i = 0; i < array2.length; i++) {
            array2[i] =(int)(Math.random()*10);
        }
        System.out.println("Before quickSort: " + Arrays.toString(array));
        quickSort(array, 0, array.length-1);
        System.out.println("After quickSort: " + Arrays.toString(array));

        System.out.println("Before heapSort: " + Arrays.toString(array2));
        MinHeap maxhp = new MinHeap();
        maxhp.heapsort(array2);
        System.out.println("After heapSort: " + Arrays.toString(array2));
    }

    

    public static void quickSort(double[] arr,int start,int end){
        if(start < end){
            double stand = arr[start];
            int low = start;
            int high = end;

            while (low<high){
                while (low < high && arr[high] >= stand){
                    high--;
                }
                arr[low] = arr[high];
                while (low < high && arr[low] <= stand){
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = stand;
            quickSort(arr,start,low);
            quickSort(arr,low+1,end);
        }
    }

}

class MinHeap {
    protected double A[];
    protected int heapsize;
    protected int parent(int i) {return (i - 1) / 2;}
    protected int left(int i) {return 2 * i + 1;}
    protected int right(int i) {return 2 * i + 2;}

    public MinHeap(){}
    public MinHeap(double A[]){
            buildMinHeap(A);
    }

    protected void minHeapify(int i){
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l <= heapsize - 1 && A[l] < A[i])
            smallest = l;
        if (r <= heapsize - 1 && A[r] < A[smallest])
            smallest = r;
        if (smallest != i) {
            double temp = A[i];
            // swap
            A[i] = A[smallest];
            A[smallest] = temp;
            this.minHeapify(smallest);
        }
    }

    public void buildMinHeap(double [] A){
        this.A = A;
        this.heapsize = A.length;
    
        for (int i = parent(heapsize - 1); i >= 0; i--)
            minHeapify(i);
    }

    public void heapsort(double [] A){
        buildMinHeap(A);
    
        for (int i = A.length - 1; i > 0; i--) {
            double temp = A[i];
            A[i] = A[0];
            A[0] = temp;
            heapsize--;
            minHeapify(0);
        }
    }
}