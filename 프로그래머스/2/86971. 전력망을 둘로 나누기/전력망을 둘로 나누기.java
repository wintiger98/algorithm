import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int[][] wires;
    static int n;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        this.wires = wires;
        this.n = n;
        
        for(int i=0; i<wires.length; i++) {
            makeGraph(i);
            bfs();
        }
        
        return answer;
    }
    
    static void makeGraph(int toCut) {
        graph = new ArrayList[n+1];
        
        for(int i=1; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<wires.length; i++) {
            if(i == toCut) continue;
            graph[wires[i][0]].add(wires[i][1]);
            graph[wires[i][1]].add(wires[i][0]);
        }
    }
    
    static void bfs() {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        // 무조건 1번 노드부터 세자. 나머진 n - 개수
        visited[1] = true;
        q.add(1);
        
        int cnt = 1;
        while(!q.isEmpty()) {
            int node = q.poll();
            
            for(int next : graph[node]) {
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
                cnt++;
            }
        }
        
        answer = Math.min(answer, Math.abs(cnt - (n - cnt)));
    }
}