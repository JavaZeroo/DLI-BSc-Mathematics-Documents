import java.util.Arrays;

public class quicksort {

    public static void main(String[] args){
        int[] x = new int[10];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 20);
        }

        // int[] a = {3, 2, 4, 1, 0, 10};
        System.out.println(Arrays.toString(x));
        quickSort(x, 0, x.length - 1);
        System.out.println(Arrays.toString(x));
    }
    
    static void quickSort(int[] a , int l, int r){
        if(l < r){
            int right = r, left = l;
            int key = a[l];
            while(left < right){
                while(left < right && key <= a[right] ){
                    right--;
                }
                swap(a,left,right);
                while( left < right && key >= a[left]){
                    left++;
                }
                swap(a,left,right);
                // a[right] = a[left];
            }
            // a[left] = key;
            quickSort(a,l,left);
            quickSort(a,left + 1,r);
        }
    }

    static void swap (int[] a, int l, int r){
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }
}
