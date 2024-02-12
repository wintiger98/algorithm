import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11404 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    int[][] graph = new int[N+1][N+1];
    int maxValue = 100_000*100;
    for(int i=1; i<N+1; i++) {
      for(int j=1; j<N+1; j++) {
        // 자기 자신으로 가는 비용은 0 / 그 외에는 제일 큰 값으로 초기화
        if(i != j) graph[i][j] = maxValue;
      }
    }

    StringTokenizer st;
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      // 이미 값이 존재할 수 있으니, 최소값 넣기
      graph[a][b] = Math.min(graph[a][b], c);
    }

    for(int k=1; k<N+1; k++) {
      for(int i=1; i<N+1; i++) {
        for(int j=1; j<N+1; j++) {
          graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
        }
      }
    }

    for(int i=1; i<N+1; i++) {
      for(int j=1; j<N+1; j++) {
        if(graph[i][j] == maxValue) bw.write(0 + " ");
        else bw.write(graph[i][j] + " ");
      }
      bw.write("\n");
    }
    bw.close();
  }
}
