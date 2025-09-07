class Solution {
    public void update(int[][] diff, int r1, int c1, int r2, int c2, int val) {
        diff[r1][c1] += val;
        diff[r1][c2+1] -= val;
        diff[r2+1][c1] -= val;
        diff[r2+1][c2+1] += val;
    }
    
    public int solution(int[][] board, int[][] skills) {
        int N = board.length;
        int M = board[0].length;
        
        int[][] diff = new int[N+1][M+1];
        
        for(int[] skill : skills) {
            int type = skill[0], r1 = skill[1], c1 = skill[2], r2 = skill[3], c2 = skill[4], degree = skill[5];
            int val = type == 1 ? degree *(-1) : degree;
            
            update(diff, r1, c1, r2, c2, val);
        }
        
        int answer = 0;
        int[][] grid = new int[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                grid[i][j] += diff[i][j];
                if(i > 0) grid[i][j] += grid[i-1][j];
                if(j > 0) grid[i][j] += grid[i][j-1];
                if(i > 0 && j > 0) grid[i][j] -= grid[i-1][j-1];
                if(grid[i][j] + board[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}