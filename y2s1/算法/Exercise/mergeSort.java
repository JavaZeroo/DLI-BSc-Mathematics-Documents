import java.util.Arrays;

public class mergeSort {
    public static void main(String[] args) {
        char[] c = {'a', 'c', 'b', 'e', 'd'};
        System.out.println(Arrays.toString(c));
        sort(c, 0, c.length - 1);
        System.out.println(Arrays.toString(c));
    }

    private static void sort(char[] c, int l, int r){
        if(l>=r)return;
        int mid = (l + r) >> 1;
        sort(c, l, mid);
        sort(c, mid+1, r);
        merge(c, l, mid, r);
    }

    private static void merge(char[] c, int l, int mid, int r){
        int leftSum = mid - l + 1;
        int rightSum = r - mid;
        char[] left = new char[leftSum + 1];
        char[] right = new char[rightSum + 1];
        System.arraycopy(c, l, left, 0, leftSum);
        System.arraycopy(c, mid + 1, right, 0, rightSum);
        left[leftSum] = Character.MAX_VALUE;
        right[rightSum] = Character.MAX_VALUE;

        int iL=0, iR=0;
        for(int i = l; i <= r; i++){
            if(left[iL] < right[iR]){
                c[i] = left[iL];
                iL++;
            }else{
                c[i] = right[iR];
                iR++;
            }
        }
    }
}