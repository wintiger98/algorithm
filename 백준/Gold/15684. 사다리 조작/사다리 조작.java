import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로선 개수
        M = Integer.parseInt(st.nextToken()); // 가로선 개수
        H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선 가능 위치 개수

        ladder = new boolean[H+1][N+1]; // 사다리 (인덱스가 1부터 시작이라서)

        // 가로선 정보
        for (int i = 0; i < M; i++) {
            // b번 세로선과 b+1 세로선을 a번 점선 위치에서 연결함
            // 1 <= a , 1 <= b
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        dfs(0, 1, 1);
        System.out.println(answer > 3 ? -1 : answer);
    }

    public static void dfs(int count, int x, int y) {
        if(count >= answer) return;

        if(countDown() == N) {
            answer = Math.min(answer, count);
            return;
        }

        for (int i = x; i <= H; i++, y=1) {
            for (int j = y; j < N; j++) {
                if(!ladder[i][j] && !ladder[i][j-1] && !ladder[i][j+1]) {
                    ladder[i][j] = true;
                    dfs(count+1, i, j+2);
                    ladder[i][j] = false;
                }
            }
        }
    }

    public static int countDown() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (i == goDown(1, i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int goDown(int depth, int index) {
        if(depth > H) {
            return index;
        }
        if(index < N && ladder[depth][index]) {
            return goDown(depth+1, index+1);
        } else if(index > 1 && ladder[depth][index-1]) {
            return goDown(depth+1, index-1);
        } else{
            return goDown(depth+1, index);
        }
    }
}