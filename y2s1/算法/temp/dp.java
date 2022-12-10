public class dp {
    public static void main(String[] args) {
        // int[][] dungeon = new int[][] {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        // System.out.println(minHealth(dungeon));
        // int[][] obstacleGrid = { { 0, 0 }, { 1, 1 }, { 0, 0 } };
        // System.out.println(uniquePathsWithObstacles(obstacleGrid));
        int[] price = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
        System.out.println(cut(price, 5));
    }

    public static int minHealth(int[][] dungeon) {
        int row = dungeon.length - 1;
        int col = dungeon[0].length - 1;
        int[][] memo = new int[row + 1][col + 1];
        for (int r = row; r >= 0; r--) {
            for (int c = col; c >= 0; c--) {
                if (r == row && c == col) {
                    memo[r][c] = Math.max(1 - dungeon[r][c], 1);
                    continue;
                }
                int right = Integer.MAX_VALUE, down = Integer.MAX_VALUE;
                if (r != row)
                    down = memo[r + 1][c];
                if (c != col)
                    right = memo[r][c + 1];
                memo[r][c] = Math.max(Math.min(right, down) - dungeon[r][c], 1);
            }
        }
        return memo[0][0];
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] memo = new int[row][col];
        for (int i = 0; i < col; i++) {
            if (obstacleGrid[0][i] == 1)
                break;
            memo[0][i] = 1;
        }
        for (int i = 0; i < row; i++){
            if (obstacleGrid[i][0] == 1) break;
            memo[i][0] = 1;
        }
        for (int r = 1; r < row; r++)
            for (int c = 1; c < col; c++)
                if (obstacleGrid[r][c] == 0)
                    memo[r][c] = memo[r][c - 1] + memo[r - 1][c];
        return memo[row - 1][col - 1];
    }

    public static int rodCut(int[] price, int n){
        int[] memo = new int[n + 1];
        for(int i = 1; i<= n; i++){
            int max = -1;
            for(int j = 0; j <= i; j++){
                max = Math.max(max, price[j] + memo[i - 1 - j]);
            }
            memo[i] = max;
        }
        return memo[n];
    }

    public static int cut(int[] price, int n){
        if(n == 0) return 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++){
            max = Math.max(max, price[i] + cut(price, n - 1 - i));
        }
        return max;
    }
}
