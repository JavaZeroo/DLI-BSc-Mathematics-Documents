public class hw10_uid {
    public static void main(String[] args) {
        int[] arr = { -2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("MaxPofit: " + buyansell(arr));
        System.out.println("MaxSubarray: " + maxSubArrayDP(arr));
        long start = System.currentTimeMillis();
        System.out.println("\nWait...");
        System.out.println(" 30tht step with Recursive: " + StepRecs(30) + ". using "
                + (System.currentTimeMillis() - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(
                " 30th step with DP: " + StepDP(30) + ". using " + (System.currentTimeMillis() - start)
                        + "ms");
    }

    public static int buyansell(int[] arr) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int p : arr) {
            minPrice = Math.min(minPrice, p);
            maxProfit = Math.max(p - minPrice, maxProfit);
        }
        return maxProfit;
    }

    public static int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int SUM = nums[0];
        int OPT = nums[0];
        for (int i = 1; i < n; ++i) {
            SUM = Math.max(SUM + nums[i], nums[i]);
            OPT = Math.max(OPT, SUM);
        }
        return OPT;
    }

    public static int StepRecs(int n) {
        if (n == 0)
            return 1;
        else if (n < 0)
            return 0;

        else
            return StepRecs(n - 3) + StepRecs(n - 2)
                    + StepRecs(n - 1);
    }

    public static int StepDP(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        memo[2] = 2;

        for (int i = 3; i <= n; i++)
            memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
        return memo[n];
    }
}