public class hw11_uid {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] dungeon = new int[][] {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[][] paths = new int[][] { { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 0 },{0, 0, 0, 0} };
        int[] price = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        System.out.println("Num of Unique Path: "+s.uniquePathsWithObstacles(paths));
        System.out.println("\nMinHP Knight Need: "+s.calculateMinimumHP(dungeon));
        for(int i = 1;i<=10; i++) {
            System.out.println(s.rodCutRecursively(price, i));
        }
        System.out.println("\nCutting Rod Using Recursively: "+s.rodCutRecursively(price, 5));
        System.out.println("Cutting Rod Using DP: "+s.rodCutDP(price, 5));
    }
}

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) break;
            memo[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) break;
            memo[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            if (i == m)
                break;
            for (int j = 1; j < n; j++) {
                if (j == n) break;
                if (obstacleGrid[i][j] == 0) memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length - 1;
        int col = dungeon[0].length - 1;
        int[][] memo = new int[row + 1][col + 1];
        for (int r = row; r >= 0; r--) {
            for (int c = col; c >= 0; c--) {
                if (r == row && c == col) {
                    memo[r][c] = dungeon[r][c] > 0 ? 1 : 1 - dungeon[r][c];
                    continue;
                }
                int down = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
                if (r != row) down = memo[r + 1][c];
                if (c != col) right = memo[r][c + 1];
                memo[r][c] = Math.max(Math.min(down, right) - dungeon[r][c], 1);
            }
        }
        return memo[0][0];
    }

    public int rodCutDP(int[] price, int n) {
        if (n == 0) return 0;
        int memo[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) 
                max_val = Math.max(max_val, price[j] + memo[i - j - 1]);
            memo[i] = max_val;
        }
        return memo[n];
    }

    public int rodCutRecursively(int[] price, int n) {
        if (n == 0) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int cost = price[i - 1] + rodCutRecursively(price, n - i);
            if (cost > maxValue) maxValue = cost;
        }
        return maxValue;
    }
}