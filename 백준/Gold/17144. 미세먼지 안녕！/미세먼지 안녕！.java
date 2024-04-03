import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    final static int AIR_PURIFIER = -1;
    static int N, M; // R, C
    static int[][] arr; // 방 정보
    static AirPurifier airPurifier; // 공청기

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // R
        M = Integer.parseInt(st.nextToken()); // C
        int T = Integer.parseInt(st.nextToken()); // 초
        arr = new int[N][M];

        int[] pair = new int[4];
        int pairIdx = 0;
        // 방 정보 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
//                if (arr[i][j] > 0) dusts.add(new Dust(i, j, arr[i][j])); // 먼지 추가
                if (arr[i][j] == -1) { // 공청기 좌표 저장
                    pair[pairIdx++] = i;
                    pair[pairIdx++] = j;
                }
            }
        }
        airPurifier = new AirPurifier(pair); // 공청기 생성

        // 매 초마다 로직 수행
        for (int t = 0; t < T; t++) {
//            System.out.println("현재 시각: " + t);
            // 1. 미세먼지 확산
            spreadAllDust();
//            print("미세먼지 확산 후");
            // 2. 공기청정기 작동
            airPurifier.run();
//            print("공기청정기 작동 후 ");
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            answer += Arrays.stream(arr[i]).sum();
        }
        System.out.println(answer + 2); // 공청기 -2를 다시 더해주기
    }

    public static void print(String s) {
        System.out.println(s);
        for(int[] a : arr) System.out.println(Arrays.toString(a));
        System.out.println();
    }

    public static void spreadAllDust() { // 모든 먼지 확산
        int[][] newArr = new int[N][M]; // 새삥

        for (int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(arr[i][j] <= 0) continue; // 먼지 아니면 넘기기
                int spreadAmount = arr[i][j] / 5; // 확산되는 양
                for (int di = 0; di < dx.length; di++) { // 4방향 탐색
                    int cx = i + dx[di];
                    int cy = j + dy[di];
                    if (cx < 0 || cx >= N || cy < 0 || cy >= M) continue; // 영역 쳌
                    if (arr[cx][cy] == AIR_PURIFIER) continue; // 공청기 패스
                    newArr[i][j] -= spreadAmount; // 원래 위치에서 확산된만큼 빼기
                    newArr[cx][cy] += spreadAmount; // 확산
                }
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                newArr[i][j] += arr[i][j];
            }
        }
        // 공청기 넣기
        newArr[airPurifier.upX][airPurifier.upY] = AIR_PURIFIER;
        newArr[airPurifier.downX][airPurifier.downY] = AIR_PURIFIER;
        arr = newArr; // 갈아끼우기
    }

    static class AirPurifier {
        int upX, upY;
        int downX, downY;
        // 상 -> 우 -> 하 -> 좌 (뒷순서부터 처리하기 위해 반대로)
        int[] upDx = {-1, 0, 1, 0};
        int[] upDy = {0, 1, 0, -1};
        // 하 -> 우 -> 상 -> 좌
        int[] downDx = {1, 0, -1, 0};
        int[] downDy = {0, 1, 0, -1};

        public AirPurifier(int[] pairs) {
            this.upX = pairs[0];
            this.upY = pairs[1];
            this.downX = pairs[2];
            this.downY = pairs[3];
        }

        public void upRun(int x, int y, int dirIdx) {
            int cx = x + upDx[dirIdx];
            int cy = y + upDy[dirIdx];
            if(cx == upX && cy == upY) return; // 한바퀴 돌았으면 끝
            if(cx < 0 || cx >= upX+1 || cy < 0 || cy >= M) { // 범위벗어나면 dirIdx 증가
                upRun(x, y, dirIdx+1);
                return;
            }
            if(arr[cx][cy] > 0) { // 미세먼지라면
                if (arr[x][y] != AIR_PURIFIER) {
                    arr[x][y] = arr[cx][cy]; // 거꾸로 순회중이니 (x,y) <- (cx,cy)
                }
                arr[cx][cy] = 0;
            }
            upRun(cx, cy, dirIdx);
        }

        public void downRun(int x, int y, int dirIdx) {
            int cx = x + downDx[dirIdx];
            int cy = y + downDy[dirIdx];
            if(cx == downX && cy == downY) return; // 한바퀴 돌았으면 끝
            if(cx < downX || cx >= N || cy < 0 || cy >= M) { // 범위벗어나면 dirIdx 증가
                downRun(x, y, dirIdx+1);
                return;
            }
            if(arr[cx][cy] > 0) { // 미세먼지라면
                if (arr[x][y] != AIR_PURIFIER) {
                    arr[x][y] = arr[cx][cy]; // 거꾸로 순회중이니 (x,y) <- (cx,cy)
                }
                arr[cx][cy] = 0;
            }
            downRun(cx, cy, dirIdx);
        }

        public void run() {
            upRun(upX, upY, 0);
            downRun(downX, downY, 0);
        }
    }
}