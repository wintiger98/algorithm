package dp;

import java.util.Scanner;
// 21716 KB, 232 ms
public class BOJ_1463 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] dp = new int[n+1];

    for(int i=2; i<n+1; i++) {
      dp[i] = dp[i-1] + 1;
      if(i % 3 == 0) {
        dp[i] = Math.min(dp[i/3]+1, dp[i]);
      }
      if (i % 2 == 0) {
        dp[i] = Math.min(dp[i/2]+1, dp[i]);
      }


    }
    System.out.println(dp[n]);
  }
}
