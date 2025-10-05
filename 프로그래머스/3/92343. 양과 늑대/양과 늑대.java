import java.util.*;

class Solution {
    final static int SHEEP = 0;
    final static int WOLF = 1;
    
    static int N; // 그래프 노드 개수
    static int[] info; // 노드 정보
    static List<Integer>[] graph; // 그래프
    static int answer; // 정답
    
    static void dfs(int node, List<Integer> toGo, int sheepCnt, int wolfCnt) {
        sheepCnt = info[node] == SHEEP ? sheepCnt + 1 : sheepCnt;
        wolfCnt = info[node] == WOLF ? wolfCnt + 1 : wolfCnt;
        
        if(sheepCnt <= wolfCnt) {
            return; // 양<=늑대면 끝
        }
        
        answer = Math.max(answer, sheepCnt); // answer 업데이트
    
        toGo.addAll(graph[node]);
        toGo.remove(Integer.valueOf(node));
        for(int next : toGo) {
            List<Integer> nextToGo = new ArrayList<>(toGo);
            dfs(next, nextToGo, sheepCnt, wolfCnt);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        this.info = info;
        graph = new ArrayList[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // edge 처리
        for(int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph[parent].add(child);
        }
        List<Integer> toGo = new ArrayList<>();
        toGo.add(0);
        dfs(0, toGo, 0, 0);
        
        return answer;
    }
}