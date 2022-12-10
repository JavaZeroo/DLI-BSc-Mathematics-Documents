import java.util.Arrays;

public class hw2_uid {
    public static void main(String arg[]){
        int[] a = new int[10];
        for(int i = 0; i< a.length; i++){
            a[i] = (int)(Math.random() * 10);
        }
        System.out.println("Before Merge Sort:" + Arrays.toString(a));
        merge_sort(a, 0, a.length-1); // The rightmost of the array is a.length -1
        System.out.println("After Merge Sort:" + Arrays.toString(a));
        
        
        System.out.println("\nFactorial with iterative:");
        for(int i = 0; i  < 5 ; i++){
            System.out.printf("%d! = %d\n", i, factorial_iterative(i));
        }
        System.out.println("Factorial with recursive:");
        for(int i = 0; i  < 5 ; i++){
            System.out.printf("%d! = %d\n", i, factorial_recursive(i));
        }

        double[] b = new double[5];
        for(int i = 0; i< b.length; i++){
            b[i] = Math.random() * 10;
        }
        System.out.println("\nArrays b is:" + Arrays.toString(b));
        double[] temp = minmax(b, b.length);
        System.out.printf("Minium of b is: %f\nMaxium of b is: %f\n", temp[0], temp[1]);
    }

    /**
     *
     * @param a  Array to be sorted
     * @param l    Leftmost of the array
     * @param r    Rightmost of the array
     */
    public static void merge_sort(int[] a, int left, int right){
        
        if(left < right){
            int mid = (left + right) / 2; 
            merge_sort(a, left, mid);
            merge_sort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    public static void merge(int[] a, int left,  int mid, int right){
        int l_sum = mid - left + 1;
        int r_sum = right - mid;
        int[] L = new int[l_sum+1];
        int[] R = new int[r_sum+1];
        System.arraycopy(a, left, L, 0, l_sum);
        System.arraycopy(a, mid + 1, R, 0, r_sum);
        L[l_sum] = Integer.MAX_VALUE;
        R[r_sum] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = left; k <= right; k++){
            if (L[i]<=R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
        }
    }
    //Implement the factorial function in the iterative and recursive way, respectively.
    public static int factorial_iterative(int a){
        int ret = 1; 
        if(a == 0){
            return 1;
        }
        else if(a > 0){
            for (int i = a; i > 0; i--){
                ret *= i;
            }
            return ret;
        }
        else{
            return 0;
        }
    }
    
    public static int factorial_recursive(int a){
        if(a < 0) return 0;
        else if (a == 0)return 1;
        else return a * factorial_recursive(a - 1);
    }
    
    public static double[] minmax(double[] a, int n){
        if (n == 1){
            return new double[] {a[0], a[0]};
        }
        else{
            double[] mm = minmax(a, n - 1);
            double[] ret = new double[2];
            ret[0] = Math.min(a[n-1], mm[0]);
            ret[1] = Math.max(a[n-1], mm[1]);
            return ret;
        }
    }


}
