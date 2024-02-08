import java.util.Scanner;

public class BOJ_2523 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    for(int i=1; i<=n; i++) {
      System.out.println("*".repeat(i));
    }
    for(int i=n-1; i>0; i--) {
      System.out.println("*".repeat(i));
    }
  }
}
