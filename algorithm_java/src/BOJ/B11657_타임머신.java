import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B11657_타임머신 {

  static class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  static int N;
  static long[] distances;
  static boolean isMinusCircle;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    distances = new long[N + 1];
    List<Edge> edges = new ArrayList<>();

    // 그래프 & 최단 경로 테이블 초기화
    for (int i = 1; i < N + 1; i++) {
      distances[i] = Long.MAX_VALUE;
    }

    // 그래프 채우기
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      edges.add(new Edge(a, b, c));
    }

    distances[1] = 0;

    for(int i=1; i<=N; i++) {
      for(Edge edge: edges) {
        if(distances[edge.from] == Long.MAX_VALUE) continue;

        if(distances[edge.to] > distances[edge.from] + edge.weight) {
          distances[edge.to] = distances[edge.from] + edge.weight;

          if(i == N) {
            System.out.println(-1);
            System.exit(0);
          }
        }
      }
    }

    for (int i = 2; i < N + 1; i++) {
      if (distances[i] == Long.MAX_VALUE) {
        bw.write(-1 + "\n");
      } else {
        bw.write(distances[i] + "\n");
      }
    }
    bw.close();
  }

}
