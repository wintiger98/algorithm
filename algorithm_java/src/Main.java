import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
// swea 1288번 문제
public class Main {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int T;
    T=sc.nextInt();

    for(int test_case = 1; test_case <= T; test_case++)
    {
      int n = sc.nextInt();
      int answer = 1;
      int[] visited = {0,0,0,0,0,0,0,0,0,0};

      while(Arrays.stream(visited).sum() < 10)
      {
        int tmp_n = n * answer;
        while(tmp_n>0)
        {
          visited[tmp_n % 10] = 1;
          tmp_n /= 10;
        }
        answer++;
      }
      System.out.println("#"+test_case+" "+(n*(answer-1)));
    }
  }
}
