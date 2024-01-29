package boj_star;

import java.util.Scanner;

public class BOJ_10991 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    for(int i=1; i<=n; i++){
      int cnt = 0;
      System.out.print(" ".repeat(n-i)+"*");
      cnt++;
      while(cnt < i) {
        System.out.print(" " + "*");
        cnt++;
      }
      System.out.println();
    }
  }
}
