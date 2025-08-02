import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // dp(i) : i분일 때 최대 금액
        // -> 끝까지 가는걸 기록하지 못함
        // dp(n, i) : 도시 n개를 지날 때 + i분일 때 최대 금액
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int timeToWalk = Integer.parseInt(st.nextToken());
            int cashToWalk = Integer.parseInt(st.nextToken());
            int timeToCycle = Integer.parseInt(st.nextToken());
            int cashToCycle = Integer.parseInt(st.nextToken());
            
            // 기본 점화식
            // dp(i) = Math.max(dp(i), dp(i-timeToWalk)+cashToWalk), dp(i-timeToCycle)+cashToCycle)
            // -> n을 넣으면?
            if(i==1) { // 초기값: 그대로
				dp[i][timeToWalk]=cashToWalk;
				dp[i][timeToCycle]=Math.max(dp[i][timeToCycle], cashToCycle);
			}else {
				for(int j=0;j<=K;j++) {
					if(dp[i-1][j]==0) continue; // 이전도시에서 지나친게 없다면 패스(무조건 지나쳐야돼서)
					if(j + timeToWalk <= K) {   // 이번 도시에서 지나칠수있을때,이전 도시까지의 값과 비교하기
						dp[i][j+timeToWalk]=Math.max(dp[i][j+timeToWalk], dp[i-1][j]+cashToWalk);
					}
					if(j + timeToCycle <= K) {
						dp[i][j+timeToCycle]=Math.max(dp[i][j+timeToCycle], dp[i-1][j]+cashToCycle);
					}
				}
			}
        }
        int answer = 0;
        for(int i=0;i<=K;i++) {
			answer=Math.max(answer, dp[N][i]);
		}
        System.out.println(answer);
    }
}