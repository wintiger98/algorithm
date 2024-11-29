import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] arr;
    static int answer;

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        int flag = 0;
        flag |= (1 << (arr[0][0] - 'A'));
        dfs(0, 0, flag, 1);

        System.out.println(answer);
    }

    public static void dfs(int x, int y, int flag, int cnt) {
        answer = Math.max(answer, cnt);

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue;

            if((flag & (1 << (arr[cx][cy] - 'A'))) != 0) continue;

            dfs(cx ,cy, flag | (1 << arr[cx][cy] - 'A'), cnt+1);
        }
    }
}