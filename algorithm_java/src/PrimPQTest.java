package _0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimPQTest {

  static class Vertex implements Comparable<Vertex> {
    int no, weight;

    public Vertex(int no, int weight) {
      super();
      this.no = no;
      this.weight = weight;
    }

    @Override
    public int compareTo(Vertex o) {
      return Integer.compare(this.weight, o.weight);
    }

  }

  public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("prim_input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int V = Integer.parseInt(br.readLine());
    int[][] adjMatrix = new int[V][V]; // 인접행렬 준비
    boolean[] visited = new boolean[V]; // 트리정점여부
    int[] minEdge = new int[V]; // 비트리정점 기준으로 트리정점들과 연결했을 경우 최소 간선 비용

    StringTokenizer st = null;
    for (int i = 0; i < V; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < V; j++) {
        adjMatrix[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    PriorityQueue<Vertex> pq = new PriorityQueue<>(); // -------------------------

    Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값 갱신위해 max로 초기화
    minEdge[0] = 0; // 임의의 시작점 0을 위해 처리
    pq.add(new Vertex(0, minEdge[0]));

    int result = 0; // 최소신장트리 비용
    int c = 0; // 트리에 포함된 정점 개수
    while (!pq.isEmpty()) {
      // step 1 : 비트리 정점 중 최소간선비용의 정점 찾기!!
      Vertex minVertex = pq.poll();
      if(visited[minVertex.no]) continue; // 이미 정점이 트리에 포함된 상태면 패스

      result += minVertex.weight; // 간선 비용 누적
      visited[minVertex.no] = true; // 트리 정점에 포함
      if(++c == V) break; // 원하는 만큼만

      // step 2 : 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선 비용 고려 -> 최적으로 업데이트
      for (int i = 0; i < V; i++) {
        if (!visited[i] && adjMatrix[minVertex.no][i] != 0 && adjMatrix[minVertex.no][i] < minEdge[i]) {
          minEdge[i] = adjMatrix[minVertex.no][i]; // 갱신
          pq.offer(new Vertex(i, minEdge[i]));
        }
      }

    }

    System.out.println(c == V ? result : -1);
  }

}
