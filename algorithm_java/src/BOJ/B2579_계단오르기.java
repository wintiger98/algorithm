import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2579_계단오르기 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N+1];
    for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(br.readLine());

    int[] dp = new int[301];
    if(N == 1) {
      System.out.println(arr[1]);
      return;
    }
    dp[1] = arr[1];
    dp[2] = dp[1] + arr[2];
    for(int i=3; i<N+1; i++) {
      dp[i] = Math.max(dp[i-3] + arr[i-1], dp[i-2]) + arr[i];
    }
    System.out.println(dp[N]);
  }
}
