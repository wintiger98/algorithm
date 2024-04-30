import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    final static int INF = 20_000_000;
    static int N, E;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long[] distances1 = new long[N+1];
        long[] distancesN = new long[N+1];
        long[] distancesV = new long[N+1];
        // 1 에서 나머지
        dijkstra(distances1, 1);
        long one2V1 = distances1[v1];
        long one2V2 = distances1[v2];

        // N 에서 나머지
        dijkstra(distancesN, N);
        long N2V1 = distancesN[v1];
        long N2V2 = distancesN[v2];

        // v1 -> v2
        dijkstra(distancesV, v1);
        long v12v2 = distancesV[v2];

        long answer = Math.min(one2V1 + N2V2, one2V2 + N2V1) + v12v2;
        if(answer >= INF) System.out.println(-1);
        else System.out.println(answer);
    }

    static void dijkstra(long[] distance, int start) {
        // 최댓값으로 채우기
        Arrays.fill(distance, INF);
        // 시작점은 0으로
        distance[start] = 0;

        PriorityQueue<Route> pq = new PriorityQueue<>();
        pq.offer(new Route(start, 0));

        while(!pq.isEmpty()) {
            Route route = pq.poll();

            // 이미 완성된 친구면 패스
            if(distance[route.node] < route.dist) continue;

            for(Node node : graph[route.node]) {
                long cost = node.dist + route.dist;
                if(cost < distance[node.to]) {
                    distance[node.to] = cost;
                    pq.offer(new Route(node.to, cost));
                }
            }
        }
    }

    static class Route implements Comparable<Route> {
        int node;
        long dist;

        public Route(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Route o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static class Node {
        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}
