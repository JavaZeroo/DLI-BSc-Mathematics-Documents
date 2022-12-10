public class dungeonGame {
    public static void main(String[] args) {
        int[][] dungeon = {{-2, 3, -3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(calculateMinimumHP(dungeon));
    }

    public static int calculateMinimumHP(int[][] dungeon){
        int row = dungeon.length - 1;
        int col = dungeon[0].length - 1;
        int[][] memo = new int[row + 1][col + 1];
        for(int r = row; r >= 0; r--){
            for(int c = col; c >= 0; c--){
                if(r == row && c == col) {
                    memo[row][col] = Math.max(1 - dungeon[row][col], 1);
                    continue;
                }
                int down = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
                if(r != row) right = memo[r + 1][c];
                if(c != col) down = memo[r][c + 1];
                memo[r][c] = Math.max(Math.min(down, right) - dungeon[r][c], 1);
            }
        }
        return memo[0][0];
    }
}
