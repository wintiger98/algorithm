import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500_테트로미노_김인엽 {
  static int[][] arr; // 맵
  static int N, M; // 가로 세로
  static int answer; // 정답

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    // 맵 입력 받기
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        checkTetromino(i, j);
      }
    }
  }

  private static void checkTetromino(int x, int y) {
    skyblue(x, y);
    yello(x, y);
    orange(x, y);
    green(x, y);
    purple(x, y);
  }

  static void skyblue(int x, int y) {
    dfsSkyblue(x, y, 0, 0, 0);
    dfsSkyblue(x, y, 0, 0, 0);
  }

  static void dfsSkyblue(int x, int y, int dirIdx, int cnt, int sum) {

  }

  static void yello(int x, int y) {

  }
  static void orange(int x, int y) {

  }
  static void green(int x, int y) {

  }
  static void purple(int x, int y) {

  }

  static class Tetromino {
    int[][] dx;
    int[][] dy;
  }
  static class Skyblue extends Tetromino {
    int[][] dx = {{0, 1}}; // 우 하
    int[][] dy = {{1, 0}};
  }
  static class Yellow extends Tetromino {
    // 우 하 좌
    int[][] dx = {{0, 1, 0}};
    int[][] dy = {{1, 0, -1}};
  }
  static class Orange extends Tetromino {
    // 우 x 3 -> 상/우
    // 하 x 3 -> 좌/우
    int[][] dx = {{0, }};
    int[][] dy;
  }
  static class Green extends Tetromino {
    int[][] dx;
    int[][] dy;
  }
  static class Purple extends Tetromino {
    int[][] dx;
    int[][] dy;
  }
}
