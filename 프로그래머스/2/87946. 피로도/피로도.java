class Solution {
    static int N;
    static boolean[] visited;
    static int answer;
    static int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        N = dungeons.length;
        visited = new boolean[N];
        
        dfs(k, 0);
        
        return answer;
    }
    
    public static void dfs(int tired, int count) {
        if(count > answer) {
            answer = count;
        }
        
        for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            if(tired >= dungeons[i][0]) {
                visited[i] = true;
                dfs(tired - dungeons[i][1], count+1);
                visited[i] = false;
            }
        }
    }
}