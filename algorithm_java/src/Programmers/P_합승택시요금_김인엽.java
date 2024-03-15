package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P_합승택시요금_김인엽 {
  static class Node {
    int to;
    int weight;
    Node next;

    public Node(int to, int weight, Node next) {
      this.to = to;
      this.weight = weight;
      this.next = next;
    }
  }
  static class Route implements Comparable<Route> {
    int dist;
    int node;

    public Route(int dist, int node) {
      this.dist = dist;
      this.node = node;
    }

    @Override
    public int compareTo(Route o) {
      return Integer.compare(this.dist, o.dist);
    }
  }
  static int answer = Integer.MAX_VALUE;
  static Node[] adjList; // 인접리스트
  public static int solution(int n, int s, int a, int b, int[][] fares) {
    adjList = new Node[n+1]; // 인접리스트
    // 인접리스트 만들기
    for(int[] fare : fares) {
      int from = fare[0];
      int to = fare[1];
      int weight = fare[2];
      // 양방향
      adjList[from] = new Node(to, weight, adjList[from]);
      adjList[to] = new Node(from, weight, adjList[to]);
    }
    dijkstra(n, a, b, s,0);

    return answer;
  }

  static void dijkstra(int n, int a, int b, int v, int lastDist) {
    int[] distances = new int[n+1]; // 최단 거리 모음
    Arrays.fill(distances, 20_000_000);

    PriorityQueue<Route> pq = new PriorityQueue<>();
    pq.offer(new Route(0, v));
    distances[v] = 0;

    while(!pq.isEmpty()) {
      Route route = pq.poll();
      if(distances[route.node] < route.dist) continue; // 이미 처리된 노드면 패스

      for(Node tmp = adjList[route.node]; tmp != null; tmp = tmp.next) {
        int cost = route.dist + tmp.weight;
        if(cost < distances[tmp.to]) {
          distances[tmp.to] = cost;
          pq.offer(new Route(cost, tmp.to));
        }
      }
    }
    int newAnswer = distances[a] + distances[b] + lastDist;
    if(newAnswer > answer) {
      return;
    };
    answer = newAnswer;

    if(v == a || v == b) {
      return;
    }
    // 현재 최단경로인 노드로 재귀
    int minNode = 0;
    int minDist = Integer.MAX_VALUE;
    for(int i=1; i<n+1; i++) {
      if(distances[i] == 0) continue;
      if(distances[i] < minDist) {
        minDist = distances[i];
        minNode = i;
      }
    }
    dijkstra(n, a, b, minNode, lastDist + minDist);
  }

  public static void main(String[] args) {
    int n = 6; int s = 4; int a = 6; int b = 2; int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
//    int n = 7; int s = 3; int a = 4; int b = 1; int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
//    int n = 6; int s = 4; int a = 5; int b = 6; int[][] fares = {{2,6,6}, {6,3,7}, {4,6,7}, {6,5,11}, {2,5,12}, {5,3,20}, {2,4,8}, {4,3,9}};
    System.out.println(solution(n, s, a, b, fares));
  }
}
