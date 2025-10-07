import java.util.*;

// 10만...
// DP
// dp(i): i번째 일때, 최대 숫자 합
// 14  -> 14
// 14 6 -> 14(14 vs 6)
// 14 6 5 -> 14 + 5(6 vs 14 + 5)
// 14 6 5 11 -> 14 + 11(dp(i-1) vs dp(i-2) + arr(i))
// dp[2][i] 로 두고, 
//   [0]: 마지막꺼 사용 안 함(첫번째꺼 사용)
//   [1]: 마지막꺼를 사용한다고 쳤을때(처음꺼 뜯겨지는 상황=처음꺼 사용 안 함)

class Solution {
    public int solution(int sticker[]) {
        int N = sticker.length;
        if(N == 1) return sticker[0]; // N = 1일 때,
        if(N == 2) return Math.max(sticker[0], sticker[1]);
        int[][] dp = new int[2][100_001];
        
        dp[0][0] = sticker[0];
        dp[1][0] = 0;
        
        dp[0][1] = Math.max(dp[0][0], sticker[1]);
        dp[1][1] = sticker[1];
        
        dp[0][2] = Math.max(sticker[0] + sticker[2], sticker[1]);
        dp[1][2] = Math.max(sticker[1], sticker[2]);
        
        for(int i=3; i<N; i++) {
            if(i < N-1) { // 마지막꺼 사용하면, useLast = true
                dp[0][i] = Math.max(dp[0][i-1], dp[0][i-2]+sticker[i]);
            }
            
            dp[1][i] = Math.max(dp[1][i-1], dp[1][i-2]+sticker[i]);
        }
        return Math.max(dp[0][N-2], dp[1][N-1]);
    }
}