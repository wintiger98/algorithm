import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
// 97,840 kb 272 ms
public class S5656_벽돌깨기_김인엽 {

    static int K, N, M;
    static int[][] arr;
    static int answer;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {

            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            arr = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(arr, 0);
            bw.write("#" + test_case + " " + answer + "\n");
        }
        bw.close();
    }

    static void print(int[][] arr) {
        System.out.println();
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }

    static void dfs(int[][] arr, int brickCnt) {
        if (brickCnt == K) { // K개 다 했으면,
            answer = Math.min(answer, calcRemoved(arr));
            return;
        }

        for (int i = 0; i < M; i++) {
            dfs(bomb(arr, i), brickCnt + 1);
        }
    }

    private static int calcRemoved(int[][] arr) {
        int cnt = 0;
        for(int j=0; j<M; j++) {
            for(int i=N-1; i>=0; i--) {
                if(arr[i][j] == 0) break;
                cnt++;
            }
        }
        return cnt;
    }

    static int[][] bomb(int[][] arr, int w) {
        int[][] newArr = new int[N][M];
        int x = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newArr[i][j] = arr[i][j];
                if (x < 0 && j == w && arr[i][j] != 0) {
                    x = i;
                }
            }
        }
        if(x == -1) return newArr;
        int power = newArr[x][w];
        newArr[x][w] = 0;
        shoot(newArr, x, w, power);
        fallDown(newArr);
        return newArr;
    }

    private static void fallDown(int[][] arr) {
        for (int i = 0; i < M; i++) { // 왼쪽부터
            for (int j = N - 1; j >= 0; j--) { // 아래쪽부터 처리하기
                if (arr[j][i] != 0) {
                    continue; // 비어있지 않으면 끝
                }
                // 비어있으면 위를 찾기
                for (int k = j; k >= 0; k--) {
                    if (arr[k][i] == 0) {
                        continue; // 비어있으면 넘어가기
                    }
                    // 안 비어있으면, 그 값을 arr[j][i]에 넣고, arr[k][i] = 0으로 만들기
                    arr[j][i] = arr[k][i];
                    arr[k][i] = 0;
                    break;
                }
            }
        }
    }

    static void shoot(int[][] arr, int x, int y, int power) {
        if (power <= 1) {
            return;
        }

        for (int p = 1; p < power; p++) {
            for (int i = 0; i < dx.length; i++) {
                int cx = x + dx[i] * p;
                int cy = y + dy[i] * p;
                // 범위체크
                if (cx < 0 || cx >= N || cy < 0 || cy >= M) {
                    continue;
                }
                if (arr[cx][cy] == 0) {
                    continue;
                }
                int tmpPower = arr[cx][cy];
                arr[cx][cy] = 0;
                shoot(arr, cx, cy, tmpPower);
            }
        }
    }
}
