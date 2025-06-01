import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 최대 블록 수 (실제론 필요 없음)
        int H = Integer.parseInt(st.nextToken()); // 목표 높이
        
        List<List<Integer>> blocks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> studentBlocks = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int height = Integer.parseInt(st.nextToken());
                if (height <= H) {
                    studentBlocks.add(height);
                }
            }
            blocks.add(studentBlocks);
        }

        int[][] dp = new int[N+1][H+1];
        dp[0][0] = 1; // 초기값: 아무도 선택 안 했고 높이 0

        for (int i = 0; i < N; i++) {
            for (int h = 0; h <= H; h++) {
                // 현재 높이에서 다음 학생을 고려
                if (dp[i][h] > 0) {
                    // 블록을 선택하지 않는 경우
                    dp[i+1][h] = (dp[i+1][h] + dp[i][h]) % MOD;

                    // 블록을 하나 고르는 경우
                    for (int b : blocks.get(i)) {
                        if (h + b <= H) {
                            dp[i+1][h + b] = (dp[i+1][h + b] + dp[i][h]) % MOD;
                        }
                    }
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}