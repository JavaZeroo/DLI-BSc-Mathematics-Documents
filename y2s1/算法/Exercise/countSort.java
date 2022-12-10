import java.util.Arrays;

public class countSort {
    public static void main(String[] args) {
        // int[] x = new int[10];
        // for (int i = 0; i < x.length; i++) {
        //     x[i] = (int) (Math.random() * 20);
        // }
        // System.out.println(Arrays.toString(x));
        // int[] ret = countsort(x);
        // System.out.println(Arrays.toString(ret));
        char[] c = {'a', 'c', 'b', 'e', 'd'};
        System.out.println(Arrays.toString(c));
        countChar(c);
        System.out.println(Arrays.toString(c));
    }

    // private static int[] countsort(int[] a) {
    //     int max = max(a);
    //     int[] count = new int[max + 1];
    //     for(int i = 0; i < a.length; i++) count[a[i]]++;
    //     for(int i = 1; i < count.length; i++) 
    //     count[i] += count[i - 1];
    //     int[] ret = new int[a.length];
    //     for(int i = a.length - 1; i >= 0; i--){
    //         ret[count[a[i]] - 1] = a[i];
    //         count[a[i]]--;
    //     }
    //     return ret;
    // }

    private static void countChar(char[] a) {
        int[] temp = charRange(a);
        int min = temp[1];
        int range = temp[2];
        char[] count = new char[range];
        for(int i = 0; i< a.length; i++) 
        count[a[i] - min]++;
        int index = 0;
        for(int i = 0; i< a.length; i++) {
            while(count[i] != 0){
                a[index++] = (char)(min + i);
                count[i]--;
            }
        }
    }

    private static int[] charRange(char[] a){
        int max = a[0];
        int min = a[0];
        for(int i = 1;i < a.length - 1; i++){
           max = max < a[i] ? a[i] : max;
           min = min > a[i] ? a[i] : min;
        }
        int[] ret = {max, min, max - min + 1};
        return ret;
    }

    // private

    // private static int max(int[] a){
    //     int max = a[0];
    //     for(int i = 1; i < a.length; i++)
    //         if(a[i] > max)
    //             max = a[i];
    //     return max;
    // }
}
