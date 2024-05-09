import java.io.*;
import java.util.*;



public class Main {
    static int N, M, ans;
    static int[][] board;

    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++){
            String s = br.readLine();
            for (int j = 0; j < M; j++){
                if (s.charAt(j) == '1')
                    board[i][j] = 1;
                ans = Math.max(ans, board[i][j]);
            }
        }

        for (int i = 1; i < N; i++){
            for (int j = 1; j < M; j++){
                int c = 0;
                if (board[i][j] > 0){
                    c = Math.min(board[i - 1][j], Math.min(board[i - 1][j - 1], board[i][j - 1]));
                    board[i][j] = c + 1;
                    ans = Math.max(ans, board[i][j]);
                }
            }
        }

        System.out.println(ans * ans);
    }
    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}