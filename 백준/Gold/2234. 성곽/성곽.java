import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] visited;

    // 서 북 동 남
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};

    static int maxRoom = 0;
    static int answer3 = 0;
    static Map<Integer, Integer> idxToNulby = new HashMap<>();
    static List<Integer>[] adjList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }
        List<int[]> startPoints = new ArrayList<>();
        startPoints.add(null);
        int roomCnt = 0;
        int visitedIdx = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] > 0) continue;
                bfs(i,j,visitedIdx++);
                startPoints.add(new int[]{i, j});
                roomCnt++;
            }
        }

        adjList = new ArrayList[visitedIdx];
        for(int i=0; i<visitedIdx; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=1; i<startPoints.size(); i++) { 
            int[] point = startPoints.get(i);
            bfs2(point[0], point[1], i);
        }
        
        for(int i = 1; i<adjList.length; i++) {
            List<Integer> cur = adjList[i];
            for(int num : cur) {
                answer3 = Math.max(answer3, idxToNulby.get(i) + idxToNulby.get(num));
            }
        }
        
        System.out.println(roomCnt);
        System.out.println(maxRoom);
        System.out.println(answer3);
    }

    static void bfs2(int x, int y, int key) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] tmpVisited = new boolean[N][M];
        int origin = visited[x][y];
        tmpVisited[x][y] = true;
        q.add(new int[]{x, y});
        
        List<Integer> adj = new ArrayList<>();
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i=0; i<4; i++) {
                int cx = cur[0] + dx[i];
                int cy = cur[1] + dy[i];
                // 영역체크
                if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue;
                // 지나온 자리 체크
                if(tmpVisited[cx][cy]) continue;
                // 옆에 있는 영역 체크
                if(visited[cx][cy] != origin) {
                    adj.add(visited[cx][cy]);
                } else { // 같은 영역이면 쭉
                    tmpVisited[cx][cy] = true;
                    q.add(new int[]{cx,cy});
                }
            }
        }

        adjList[key] = adj;
    }

    static void bfs(int x, int y, int visitedIdx) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[x][y] = visitedIdx;
        q.add(new int[]{x, y});
        int nulby = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            // arr 값
            int cantGo = arr[cur[0]][cur[1]];
            for(int i=0; i<4; i++) {
                // 벽이 있으면 패스
                if((cantGo & (1 << i)) != 0) continue;
                int cx = cur[0] + dx[i];
                int cy = cur[1] + dy[i];
                // 지나온 자리 체크
                if(visited[cx][cy] > 0) continue;
                nulby++;
                visited[cx][cy] = visitedIdx;
                q.add(new int[]{cx,cy});
            }
        }
        idxToNulby.put(visitedIdx, nulby);
        maxRoom = Math.max(maxRoom, nulby);
    }
}