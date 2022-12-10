public class maxSubArr {
    public static void main(String[] args) {
        int[] arr = {1, 5, -5, 10, -40, 0, 5, 8};
        int[] price = {5, 9, 20, 1, 19, 1, 4, 7};
        System.out.println(maxSubArr(arr));
        System.out.println(maxProfit(price));
    }

    public static int maxSubArr(int[] arr) {
        int maxSum = 0;
        int currentSum = 0;
        for(int i = 0; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]); 
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int maxProfit(int[] price){
        int maxProfit = 0;
        int min = price[0];
        for(int i = 0; i < price.length; i++) {
            min = Math.min(min, price[i]);
            maxProfit = Math.max(maxProfit, price[i] - min);
        }
        return maxProfit;
    }
}
