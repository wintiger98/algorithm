import java.util.*;

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    static int N, M; // 크기
    static Map<Character, List<Position>> alp2Pos = new HashMap<>(); // 알파벳별 좌표
    static boolean[][] invalid; // true면 무효 / false면 유효
    static char[][] map; // 맵
    static List<Position> lastSet = new ArrayList<>();
    
    static class Position {
        int x, y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        
        int answer = 0;
        
        // 배열 초기화
        invalid = new boolean[N+2][M+2];    // 0과 N(M)+1 은 빈곳. 범위 설정 편하게 하려고
        map = new char[N+2][M+2];           // 마찬가지
        
        // 방문배열 끄트머리는 true 처리하기
        for(int i=0; i<N+2; i++) {
            for(int j=0; j<M+2; j++) {
                if(i == 0 || i == N+1) invalid[i][j] = true;
                else if(j == 0 || j == M+1) invalid[i][j] = true;
                else if(j > 0 && j < M+1) j = M; // 중간 스킵
            }
        }
        
        // 알파벳별 위치 저장하기
        for(int i=0; i<N; i++) {
            String st = storage[i];
            for(int j=0; j<M; j++) {
                char ch = st.charAt(j);
                
                map[i+1][j+1] = ch;
                
                List<Position> originPos = alp2Pos.getOrDefault(ch, new ArrayList<>());
                originPos.add(new Position(i+1, j+1));
                alp2Pos.put(ch, originPos);
            }
        }
        
        // request 순회하면서 지게차/크레인 하기
        for(String request : requests) {
            int size = request.length();
            char ch = request.charAt(0);
            
            if(size == 1) { // 만약 한글자면, 지게차
                jigang(ch);
            } 
            else { // 두글자면, 크레인
                craning(ch);
            }
        }
        
        answer = calcAnswer(); // 유효한거 세기
        
        return answer;
    }
    
    static int calcAnswer() {
        int cnt = 0;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(invalid[i][j]) continue;
                cnt++;
            }
        }
        return cnt;
    }
    
    // 지게차: 외부와 맡닿는 면적에 대해서만 촥
    static void jigang(char ch) {
        boolean[][] visited = new boolean[N+2][M+2];
        
        Queue<Position> q = new ArrayDeque<>();
        for(int i=0; i<N+2; i++) {
            for(int j=0; j<M+2; j++) {
                if(i == 0 || i == N+1 || j == 0 || j == M+1) {
                    visited[i][j] = true;
                    q.add(new Position(i, j));
                }
                else if(j > 0 && j < M+1) j = M; // 중간 스킵
            }
        }
        
        for(Position lst : lastSet) {
            visited[lst.x][lst.y] = true;
            q.add(new Position(lst.x, lst.y));
        }
        
        List<Position> toRemove = new ArrayList<>();
        while(!q.isEmpty()) {
            Position cur = q.poll();
            
            for(int i=0; i<4; i++) {
                int cx = cur.x + dx[i];
                int cy = cur.y + dy[i];
                // 영역체크
                if(cx < 0 || cx >= N+2 || cy < 0 || cy >= M+2) continue;
                if(visited[cx][cy]) continue; // 방문한 곳 패스
                // 만약 invalid true라면(이미 지갱한곳이면 다음꺼 체크 가능)
                if(invalid[cx][cy]) {
                    q.add(new Position(cx, cy));
                    visited[cx][cy] = true;
                } else { // false라면 멈추기. 그리고 만약 해당 위치에 타겟알파벳이 있다면 부수기
                    if(map[cx][cy] == ch) {
                        toRemove.add(new Position(cx,cy));
                    }
                }
            }
        }
        lastSet = toRemove;
        // 다 하고 나서 비활성화 시켜야댐
        for(Position remove : toRemove) {
            invalid[remove.x][remove.y] = true;
        }
    }
    
    // 해당 알파벳 싹다 샷다내리기
    static void craning(char ch) {
        List<Position> targets = alp2Pos.get(ch);
        if(targets == null) return;
        for(Position target : targets) {
            invalid[target.x][target.y] = true;
        }
    }
}