package boj_star;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2447 {
  static char[][] arr;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());

    arr = new char[n][n];

    star(0, 0, n, false);

    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        bw.write(arr[i][j]);
      }
      bw.write("\n");
    }
    bw.close();
    br.close();
  }

  private static void star(int x, int y, int n, boolean blank) {
    // 빈칸일 때
    if(blank) {
      for(int i=x; i<x+n; i++) {
        for(int j=y; j<y+n; j++) {
          arr[i][j] = ' ';
        }
      }
      return;
    }
    // 더이상 쪼갤 수 없을 때
    if(n == 1) {
      arr[x][y] = '*';
      return;
    }

    // size : 해당 블록의 한 칸을 담을 변수를 의미
    // count : 별 출력 누적 개수
    int size = n/3;
    int count = 0;
    for(int i=x; i<x+n; i+=size) {
      for(int j=y; j<y+n; j+=size) {
        count++;
        // 공백칸일 경우
        star(i, j, size, count == 5);
      }
    }
  }
}
