import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14499_주사위굴리기_김인엽 {
  static int N, M;
  static int[][] arr;
  // 동서북남(인덱스0은 무시)
  static int[] dx = { 0, 0, 0, -1, 1 };
  static int[] dy = { 0, 1, -1, 0, 0 };
  // 주사위 위치
  static int x, y;
  // 주사위의 세로 부분
  static int[] sero = {0,0,0,0}; // 젤 앞이 아랫부분
  // 주사위 가로 부분
  static int[] garo = {0,0,0,0}; // 젤 앞이 아랫부분

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    arr = new int[N][M];

    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int tmp = Integer.parseInt(st.nextToken());
        arr[i][j] = tmp;
        // 주사위 위치 설정
        if(tmp == 0) {
          x = i;
          y = j;
        }
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < K; i++) {
      int order = Integer.parseInt(st.nextToken());
      move(order);
    }
  }

  private static void move(int order) {
    // 주사위 이동
    x += dx[order];
    y += dy[order];
    // 벗어나면 무시
    if(x < 0 || x >= N || y < 0 || y >= M) {
      x -= dx[order];
      y -= dy[order];
      return;
    }

    switch (order) {
      case 1: // 동

        break;
      case 2: // 서

        break;
      case 3: // 북

        break;
      case 4: // 남

        break;
    }

    // 이동한 칸의 수 확인
    // 0이면, 바닥면 수 복사
    if(arr[x][y] == 0) {
//			arr[x][y] = 바닥면;
    } else { // 0이 아니면 칸의 수를 바닥면에 복사 -> 0으로 바꾸기
//			바닥면 = arr[x][y];

      arr[x][y] = 0;
    }
  }

}