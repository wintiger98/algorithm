import java.io.*;
import java.util.*;

public class Main {
    final static int WHITE = 1;
    final static int BLACK = 0;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(str.charAt(j)+"");
                arr[i][j] = num;
            }
        }

        // 0-1 BFS를 위한 Deque 사용
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] dist = new int[N][N];
        
        // 거리 배열 초기화
        for(int i=0; i<N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        // 시작점
        dist[0][0] = 0;
        deque.addFirst(new int[]{0, 0});

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0];
            int y = cur[1];

            // 4방향 탐색
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 영역 체크
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                
                int newDist;
                if(arr[nx][ny] == WHITE) {
                    // 흰 방이면 비용 증가 없음
                    newDist = dist[x][y];
                } else {
                    // 검은 방이면 비용 1 증가
                    newDist = dist[x][y] + 1;
                }
                
                // 더 짧은 경로를 찾았다면 업데이트
                if(newDist < dist[nx][ny]) {
                    dist[nx][ny] = newDist;
                    
                    if(arr[nx][ny] == WHITE) {
                        // 비용이 증가하지 않으므로 앞에 추가
                        deque.addFirst(new int[]{nx, ny});
                    } else {
                        // 비용이 증가하므로 뒤에 추가
                        deque.addLast(new int[]{nx, ny});
                    }
                }
            }
        }

        System.out.println(dist[N-1][N-1]);
    }
}