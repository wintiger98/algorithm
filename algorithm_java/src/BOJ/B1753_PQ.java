import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 	108900	768
public class B1753_PQ {

  static class Edge {

    int to;
    int weight;

    public Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  static class GraphNode {

    List<Edge> edges = new ArrayList<>();
  }

  static class Route implements Comparable<Route>{
    int distance;
    int node;

    public Route(int distance, int node) {
      this.distance = distance;
      this.node = node;
    }

    @Override
    public int compareTo(Route o) {
      return this.distance - o.distance;
    }
  }

  static int V, E;
  static GraphNode[] graph;
  static int[] distances;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken()); // 노드의 개수
    E = Integer.parseInt(st.nextToken()); // 간선의 개수

    graph = new GraphNode[V + 1]; // 1번부터 인덱싱을 위해 V+1로 초기화
    distances = new int[V + 1]; // 최단 경로 거리 기록

    for (int i = 1; i < V + 1; i++) {
      distances[i] = Integer.MAX_VALUE; // 최대값으로 초기화
      graph[i] = new GraphNode(); // 그래프 초기화
    }

    int K = Integer.parseInt(br.readLine()); // 시작 노드 ( 1<= <=V)

    // graph 초기화
    for (int i = 0; i < E; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken()); // from
      int v = Integer.parseInt(st.nextToken()); // to
      int w = Integer.parseInt(st.nextToken()); // 가중치
      graph[u].edges.add(new Edge(v, w));
    }
    dijkstra(K);

    for (int i = 1; i < distances.length; i++) {
      if (distances[i] < Integer.MAX_VALUE) {
        bw.write("" + distances[i]);
      } else {
        bw.write("INF");
      }
      bw.write("\n");
    }
    bw.close();
  }

  private static void dijkstra(int start) {
    distances[start] = 0;
    PriorityQueue<Route> pq = new PriorityQueue<>();
    pq.add(new Route(0, start));

    while(!pq.isEmpty()) {
      Route r = pq.poll();
      if(distances[r.node] < r.distance) continue; // 이미 처리된 적 있는 노드라면 무시
      // 인접 노드 확인
      for(Edge edge : graph[r.node].edges) {
        int cost = r.distance + edge.weight;
        // 현재 노드를 거쳐서, 다른 노드로 이동하는게 더 짧을 경우
        if(cost < distances[edge.to]) {
          distances[edge.to] = cost;
          pq.add(new Route(cost, edge.to));
        }
      }
    }
  }
}
