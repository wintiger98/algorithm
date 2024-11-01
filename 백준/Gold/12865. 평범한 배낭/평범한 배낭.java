import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] things = new int[N+1][2];
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            things[i][0] = w;
            things[i][1] = v;
        }
        // 1 인덱스부터
        for(int i=1; i<=N; i++) {
            for(int j=1; j<K+1; j++) {
                if(things[i][0] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], things[i][1] + dp[i-1][j-things[i][0]]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}