import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3282_01Knapsack_김인엽 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int test_case = 1; test_case <= T; test_case++) {
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      int[][] arr = new int[N + 1][2];
      int[][] dp = new int[N + 1][K + 1];

      // 입력 처리
      for (int i = 1; i <= N; i++) {
        st = new StringTokenizer(br.readLine());
        int weight = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        arr[i][0] = weight;
        arr[i][1] = value;
      }

      for (int i = 1; i < N + 1; i++) {
        // 무게가 0이면 0이니까 생략
        for (int j = 1; j < K + 1; j++) {
          if (arr[i][0] > j) { // 현재 물건이 못들어가는 상황이면 이전꺼 넣기
            dp[i][j] = dp[i - 1][j];
          } else { // 그 외에는 이전의 최대 vs 현재 꺼 + 이전의 지금 무게 - 현재 물건 무게 한거의 최대 가치
            dp[i][j] = Math.max(dp[i - 1][j], arr[i][1] + dp[i - 1][j - arr[i][0]]);
          }
        }
      }
      System.out.println("#" + test_case + " " + dp[N][K]);
    }
  }

}
