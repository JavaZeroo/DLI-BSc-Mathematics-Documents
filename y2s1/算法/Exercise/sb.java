import java.util.Arrays;

public class sb {
    public static void main(String[] args){
        int[] a = {3, 1, 5, 4};
        mergesort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    static void mergesort(int[] a, int left, int right) {
		if (left >= right)
			return;
		int mid = (left + right) >> 1;
		mergesort(a, left, mid);
		mergesort(a, mid + 1, right);
		merge(a, left, mid, right);
	}

	static void merge(int[] a, int left, int mid, int right) {
		int Left = left;
		int Right = mid + 1;
		int n = 0;
		int[] Temp = new int[right - left + 1];

		while (Left <= mid && Right <= right) {
			if (a[Left] >= a[Right]) {
				Temp[n] = a[Left];
				Left++;
			} else {
				Temp[n] = a[Right];
				Right++;
			}
			n++;
		}
		if (left <= mid)
			System.arraycopy(a, Left, Temp, n, 1 + mid - Left);
		if (Right <= right)
			System.arraycopy(a, Right, Temp, n, 1 + right - Right);

		System.arraycopy(Temp, 0, a, 0 + left, right - left + 1);
	}

}
