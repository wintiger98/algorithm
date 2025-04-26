import java.util.*;

class Solution {
    static boolean[] visited;
    static int[][] computers;
    static int n;
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        visited = new boolean[n];
        
        int answer = 0;
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            bfs(i);
            answer++;
        }
        
        return answer;
    }
    
    static void bfs(int index) {
        Queue<Integer> q = new ArrayDeque<>();
        visited[index] = true;
        q.add(index);
        
        while(!q.isEmpty()) {
            int node = q.poll();
            
            for(int i=0; i<n; i++) {
                if(visited[i]) continue;
                if(computers[node][i] == 0) continue;
            
                q.add(i);
                visited[i] = true;
            }
        }
    }
}