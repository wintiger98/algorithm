import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static boolean[][] ladder;
    static int answer;
    static boolean isFinish;
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

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(0, 1);
            if(isFinish) break;
        }
        System.out.println(isFinish ? answer : -1);
    }

    public static void dfs(int count, int x) {
        if(isFinish) return;
        if(answer == count) {
            if(check()) isFinish = true;
            return;
        }

        for (int i = x; i < H+1; i++) {
            for (int j = 1; j < N; j++) {
                if(!ladder[i][j] && !ladder[i][j+1] && !ladder[i][j-1]) {
                    ladder[i][j] = true;
                    dfs(count + 1, i);
                    ladder[i][j] = false;
                }
            }

        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            int x = 1, y = i;
            for (int j = 0; j < H; j++) {
                if (ladder[x][y]) y++;
                else if (ladder[x][y-1]) y--;
                x++;
            }
            if (y != i) return false;
        }
        return true;
    }
}