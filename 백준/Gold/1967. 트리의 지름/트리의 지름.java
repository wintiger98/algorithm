import java.util.*;
import java.io.*;

public class Main {
    static int answer;
    static List<Edge>[] graph;
    static boolean[] visited;

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[parent].add(new Edge(child, weight));
            graph[child].add(new Edge(parent, weight));
        }

        for(int i=1; i<=N; i++) {
            visited[i] = true;
            dfs(i, 0);
            visited[i] = false;
        }

        System.out.println(answer);
    }

    public static void dfs(int node, int sum) {
        if(sum > answer) answer = Math.max(answer, sum);

        for(Edge edge : graph[node]) {
            if(visited[edge.to]) continue;
            visited[edge.to] = true;
            dfs(edge.to, sum + edge.weight);
            visited[edge.to] = false;
        }
    }
}