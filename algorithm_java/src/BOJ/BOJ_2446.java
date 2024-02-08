import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2446 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    for(int i=n; i>0; i--) {
      System.out.println(" ".repeat(n-i) + "*".repeat(2*i-1));
    }
    for(int i=1; i<n; i++) {
      System.out.println(" ".repeat(n-i-1) + "*".repeat(2*i+1));
    }
  }
}
