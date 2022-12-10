import java.util.Arrays;

public class findmax {
    public static void main(String[] args) {
        int[] x = new int[5];
        for (int i = 0; i < x.length; i++) {
            x[i] = (int) (Math.random() * 10);
        }

        System.out.println(Arrays.toString(x));
        System.out.println(max_iteration(x));
        System.out.println(max_recursive(x));
        System.out.println(sum_iteration(x));
        System.out.println(sum_recursive(x));

    }

    public static int max_iteration(int[] a){
        int max = a[0];
        for(int i = 1; i < a.length; i++)
            if(max < a[i])
                max = a[i];
        return max;
    }
    public static int max_recursive(int[] a){
        return max_recursive(a, 0);
    }
    public static int max_recursive(int[] a, int index){
        if(index == a.length - 1) return a[index];
        return Math.max(a[index], max_recursive(a, index + 1));
    }
    public static int sum_iteration(int[] a){
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum += a[i];
        }
        return sum;
    }

    public static int sum_recursive(int[] a){
        return sum_recursive(a, a.length - 1);
    }
    public static int sum_recursive(int[] a, int index){
        if(index == 0) return a[0];
        return a[index] + sum_recursive(a, index - 1);
    }
}
