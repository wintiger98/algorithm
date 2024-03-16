import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B4485_녹색옷입은애가젤다지_김인엽 {
  static int[][] arr;
  static boolean[][] visited;
  static int N;
  static int answer;
  static int[] dx = {0,0,1,-1};
  static int[] dy = {1,-1,0,0};
  public static void dfs(int x, int y, int sum) {
    visited[x][y] = true;

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int testCase = 1;

    while(true){
      N = Integer.parseInt(br.readLine());
      if(N == 0) break;

      arr = new int[N][N];
      for(int i=0; i<N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      visited = new boolean[N][N];
      visited[0][0] = true;
      dfs(0, 0, 0);
      bw.write("Problem " + testCase++ + ": " + answer + "\n");

      bw.flush();
    }
    bw.close();
  }
}
