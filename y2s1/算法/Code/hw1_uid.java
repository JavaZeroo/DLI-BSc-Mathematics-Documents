import java.util.Arrays;

public class hw1_uid {
    public static void main(String[] args) {
        int[] x = new int[10];
        for(int i = 0; i< x.length; i++){
            x[i] = (int)(Math.random() * 10);
        }
        System.out.println("BubbleSort");
        System.out.println("Before Sort" + Arrays.toString(x));
        new hw1_uid().BubbleSort(x);
        System.out.println("After Sort" + Arrays.toString(x));
        
        System.out.println("SelectionSort");
        int[] y = new int[10];
        for(int i = 0; i< y.length; i++){
            y[i] = (int)(Math.random() * 10);
        }
        System.out.println("Before Sort" + Arrays.toString(y));
        new hw1_uid().InsertionSort(y);
        System.out.println("After Sort" + Arrays.toString(y));
    }

    void BubbleSort(int a[]) {
        for (int i = 0; i < a.length - 1; ++i) {
            for (int j = 0; j < a.length-i-1; ++j) {
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }

    void InsertionSort(int[] a) {
        for(int i = 1; i < a.length; ++i){
            for(int j = i; j > 0; --j){
                if (a[j] < a[j - 1]) {
                    int temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } 
                else {
                    break;
                }
            }
        }
    }
}

