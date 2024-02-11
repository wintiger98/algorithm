import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
// 	121756	1628
public class B1753 {

  static class GraphNode {
    Map<Integer, Integer> edges = new TreeMap<>();
  }
  static int V, E;
  static GraphNode[] graph;
  static int[] distances;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    V = Integer.parseInt(st.nextToken()); // 노드의 개수
    E = Integer.parseInt(st.nextToken()); // 간선의 개수

    graph = new GraphNode[V+1]; // 1번부터 인덱싱을 위해 V+1로 초기화
    distances = new int[V+1]; // 최단 경로 거리 기록
    visited = new boolean[V+1]; // 방문 여부 기록

    for(int i=1; i<V+1; i++) {
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
      // 만약 해당 to가 있다면
      if(graph[u].edges.containsKey(v)) {
        // 더 작은 가중치 값 넣기
        graph[u].edges.put(v, Math.min(graph[u].edges.get(v), w));
      }
      // 없다면 넣기
      else {
        graph[u].edges.put(v, w);
      }

    }
    dijkstra(K);

    for (int i=1; i<distances.length; i++) {
      if(distances[i] < Integer.MAX_VALUE) bw.write(""+distances[i]);
      else bw.write("INF");
      bw.write("\n");
    }
    bw.close();
  }

  private static void dijkstra(int start) {
    visited[start] = true;
    distances[start] = 0;

    // 시작 노드와 연결된 친구들 기록
    for(Integer to: graph[start].edges.keySet()) {
      distances[to] = graph[start].edges.get(to);
    }

    // 시작 노드를 제외한 모든 노드를 돌며 최단 경로 기록 완성하기
    for(int i=0; i<V-1; i++) {
      // 현재 가장 경로가 짧은 노드 찾기
      int minNode = getShortestNode();
      if(minNode == 0) break;
      visited[minNode] = true; // 해당 노드 방문 처리
      // 해당 노드와 연결된 노드에 대한 최단 경로 확인
      for(Integer to: graph[minNode].edges.keySet()) {
        if(visited[to]) continue; // 이미 방문한 곳은 패스

        // 만약 새로운 경로가 기존 경로보다 짧으면, 변경
        distances[to] = Math.min(distances[to], distances[minNode] + graph[minNode].edges.get(to));
      }
    }

  }

  private static int getShortestNode() {       // 현재 가장 경로가 짧은 노드 찾기
    int minNode = 0;
    int minValue = Integer.MAX_VALUE;
    for(int i=1; i<distances.length; i++) {
      if(visited[i]) continue;
      if(distances[i] < minValue) {
        minNode = i;
        minValue = distances[i];
      }
    }
    return minNode;
  }
}
