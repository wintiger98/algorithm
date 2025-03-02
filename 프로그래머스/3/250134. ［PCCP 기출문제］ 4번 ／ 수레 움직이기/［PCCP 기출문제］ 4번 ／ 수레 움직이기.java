import java.util.*;

class Solution {
    
    final static int BLANK = 0;
    final static int RED_START = 1;
    final static int BLUE_START = 2;
    final static int RED_END = 3;
    final static int BLUE_END = 4;
    final static int WALL = 5;
    
    static int[][] maze;
    
    static int N, M; // 메이즈 사이즈
    
    static int[] dx = {0, 1, 0, -1, 0};
    static int[] dy = {1, 0, -1, 0, 0};
    
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        this.maze = maze;
        this.N = maze.length;
        this.M = maze[0].length;

        int redX = 0, redY = 0, blueX = 0, blueY = 0;
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(maze[i][j] == RED_START) {
                    redX = i; redY = j;
                } else if(maze[i][j] == BLUE_START) {
                    blueX = i; blueY = j;
                } 
            }
        }
        
        bfs(redX, redY, blueX, blueY);
        
        return answer==Integer.MAX_VALUE?0:answer;
    }
    
    static void bfs(int redX, int redY, int blueX, int blueY) {
        Queue<Sooray> q = new ArrayDeque<>();
        q.add(new Sooray(redX, redY, blueX, blueY));
        
        while(!q.isEmpty()) {
            Sooray sooray = q.poll();
            
            if(answer <= sooray.turn) continue;
            if(maze[sooray.redX][sooray.redY] == RED_END &&
                maze[sooray.blueX][sooray.blueY] == BLUE_END) {
                answer = Math.min(answer, sooray.turn);
                continue;
            }
            
            for(int i=0; i<4; i++) {
                if(maze[sooray.redX][sooray.redY] == RED_END) i=4;
                int cRedX = sooray.redX + dx[i];
                int cRedY = sooray.redY + dy[i];

                if(!isValid(cRedX, cRedY)) continue; // 영역체크
                if(i < 4 && sooray.redVisited[cRedX][cRedY]) continue; // 방문체크
                
                for(int j=0; j<4; j++) {
                    if(maze[sooray.blueX][sooray.blueY] == BLUE_END) j=4;
                    int cBlueX = sooray.blueX + dx[j];
                    int cBlueY = sooray.blueY + dy[j];

                    if(!isValid(cBlueX, cBlueY)) continue; // 영역체크
                    if(j < 4 && sooray.blueVisited[cBlueX][cBlueY]) continue; // 방문체크
                    if(cBlueX == cRedX && cBlueY == cRedY) continue; // 같은 지역으로 가면 안됨
                    if(cBlueX == sooray.redX && cBlueY == sooray.redY &&
                        cRedX == sooray.blueX && cRedY == sooray.blueY) continue; // 위치 변환 불가
                    
                    Sooray newSooray = new Sooray(cRedX, cRedY, cBlueX, cBlueY);
                    newSooray.copyVisit(sooray);
                    newSooray.turn = sooray.turn + 1;
                    
                    q.add(newSooray);
                }
            }
        }
    }
    
    // 영역체크
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M && maze[x][y] != WALL;
    }
    
    // 수레이
    static class Sooray {
        int redX, redY; // 현재 위치
        int blueX, blueY;
        int turn;
        boolean[][] redVisited; // 방문여부
        boolean[][] blueVisited;
        
        public Sooray(int redX, int redY, int blueX, int blueY) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            
            this.redVisited = new boolean[N][M];
            redVisited[redX][redY] = true;
            
            this.blueVisited = new boolean[N][M];
            blueVisited[blueX][blueY] = true;
        }
        
        public void copyVisit(Sooray sooray) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(sooray.redVisited[i][j]) this.redVisited[i][j] = true;
                    if(sooray.blueVisited[i][j]) this.blueVisited[i][j] = true;
                }
            }
        }
    }
}