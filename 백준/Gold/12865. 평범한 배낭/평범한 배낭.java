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

        int[][] arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[i][0] = w;
            arr[i][1] = v;
        }

        int[] dp = new int[K + 1];
        for (int i = 1; i <= N; i++) {
            int weight = arr[i][0];
            int value = arr[i][1];
            for (int j = K; j >= weight; j--) {
                dp[j] = Math.max(dp[j],
                                 dp[j - weight] + value);
            }
        }

        System.out.println(dp[K]);
    }
}