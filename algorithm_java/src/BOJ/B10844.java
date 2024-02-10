import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B10844 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    long[][] dp = new long[101][10];

    for(int i=1; i<10; i++) dp[1][i] = 1;

    for(int i=2; i<N+1; i++) {
      for(int j=0; j<10; j++) {
        if(j == 0) dp[i][j] = dp[i-1][1];
        else if(j == 9) dp[i][j] = dp[i-1][8];
        else dp[i][j] = dp[i-1][j-1] % 1000000000 + dp[i-1][j+1] % 1000000000;
      }
    }
    long answer = 0;
    for(int i=1; i<10; i++) {
      answer = answer % 1000000000 + dp[N][i]%1000000000;
    }
    System.out.println(answer);
  }
}
