import java.util.Arrays;

public class insertSort {
    public static void main(String[] args) {
        int[] x = new int[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 20);
        }

        System.out.println(Arrays.toString(x));
        // merge_sort(x, 0, x.length - 1);
        InsertSort(x);
        System.out.println(Arrays.toString(x));
        
        // System.out.println(factorial(7));
    }

    static void InsertSort(int[] x) {
        for(int i = 1; i < x.length; i++) {
            for(int j = i; j > 0; j--) {
                if(x[j] > x[j - 1]) swap(x, j);
                else break;
            }
        }
    }

    static void swap(int[] x, int j) {
        int temp = x[j];
        x[j] = x[j - 1];
        x[j - 1] = temp;
    }

    static void BubbleSort(int[] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (x[i] < x[j]) {
                    int temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;
                }
            }
        }
    }

    static void merge_sort(int[] x, int l, int r) {
        if (l < r) {
            int m = (l + r) >> 1;
            merge_sort(x, l, m);
            merge_sort(x, m + 1, r);
            merge(x, l, m, r);
        }
    }

    static void merge(int[] x, int l, int m, int r) {
        int l_sum = m - l + 1;
        int r_sum = r - (m + 1) + 1;
        int[] L = new int[l_sum + 1], R = new int[r_sum + 1];
        System.arraycopy(x, l, L, 0, l_sum);
        System.arraycopy(x, m + 1, R, 0, r_sum);
        L[l_sum] = Integer.MAX_VALUE;
        R[r_sum] = Integer.MAX_VALUE;
        int i=0, j=0;
        for(int k = l; k <= r; k++) {
            if(L[i] < R[j]){
                x[k] = L[i];
                i++;
            }else{
                x[k] = R[j];
                j++;
            }
        }

    }
    static int factorial(int a){
        if(a < 0) return 0;
        else if(a == 0) return 1;
        else return a * factorial(a - 1);
    }

    
}