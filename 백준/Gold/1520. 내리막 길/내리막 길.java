import java.util.*;
import java.io.*;

/**
 * 1520 내리막 길
 * 왼쪽 위 -> 오른쪽 아래
 * 항상 높이가 더 낮은 지점으로 이동 = 내리막길
 * 내리막길로만 이동하는 경로의 개수
 * N, M <= 500
 * 높이 <= 10_000
 * 방법1. 완전탐색
 * 방법2. 각 칸마다 가능한 방향 세고 곱하기
 */
public class Main {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int[][] arr;
    static int[][] dp; // 메모이제이션(i,j에서 목적지까지 가는 방법 수)

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][M];
        dp = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int height = Integer.parseInt(st.nextToken());
                arr[i][j] = height;
            }
            Arrays.fill(dp[i], -1); // -1로 초기화(계산 전)
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        if(x == N-1 && y == M-1) {
            return 1;
        }

        // 이미 계산된 거면 저장된값 내놓기
        if(dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0; // 0 초기화
        for(int i=0; i<4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];
            // 범위체크
            if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue;
            // 내리막길 체크
            if(arr[x][y] <= arr[cx][cy]) continue;

            dp[x][y] += dfs(cx,cy); // 다음 위치에서 목적지까지 가는 경로 수를 현재 위치 누적
        }
        return dp[x][y];
    }
}