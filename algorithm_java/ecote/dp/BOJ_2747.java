package dp;

import java.util.Scanner;
// 17740 KB, 204 ms
public class BOJ_2747 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] dp = new int[n+1];
    dp[1] = 1;
    if(n == 1) {
      System.out.println(dp[n]);
      return;
    }
    for(int i=2; i<n+1; i++) {
      dp[i] = dp[i-1] + dp[i-2];
    }
    System.out.println(dp[n]);
  }
}
