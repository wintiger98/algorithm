import java.util.Scanner;

public class BOJ_10990 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    if(n == 1)
      System.out.println("*");
    else {
      System.out.println(" ".repeat(n-1) + "*");
      for(int i=2; i<=n; i++) {
        System.out.println(" ".repeat(n-i) + "*" + " ".repeat(2*(i-1)-1) + "*");
      }
    }
  }
}
