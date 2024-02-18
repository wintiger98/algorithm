import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16236_아기상어_김인엽 {
  static class BabyShark {
    Position currentPosition; // 아기상어 위치
    int size = 2; // 아기상어 크기 (초기값 2)
    int eatCnt = 0; // 먹은 물고기

    void eat(int x, int y) { // 해당 위치 물고기 먹기
      remainFishes[arr[x][y]]--; // 해당 위치 물고기 크기에 대한 개수 감소
      this.eatCnt++; // 먹은 물고기 증가
      if(this.size == this.eatCnt) { // 만약 크기만큼 먹었으면
        this.size++; // 성장
        this.eatCnt = 0; // 먹은 물고기 초기화
      }
    }

    void moveTo(Position to) { // x, y로 이동
      // 가중치 없는 그래프 => bfs로 최단 경로 찾기


      this.currentPosition = to;
    }


  }

  static class Position {
    int x, y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int[][] arr; // 맵
  static int N; // 맵 크기
  static int[] dx = {-1,1,0,0}; // 상하좌우
  static int[] dy = {0,0,-1,1};
  static int[] remainFishes = new int[7]; // 남아있는 물고기 수(1~6까지만 사용. 각 크기에 대한 개수)
  static int canEatCnt; // 먹을 수 있는 애들 개수
  static int time; // 시간
  static BabyShark babyShark = new BabyShark(); // 아기상어
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    StringTokenizer st;
    for(int i=0; i<N; i++) { // 맵 채우기
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        int element = Integer.parseInt(st.nextToken());
        arr[i][j] = element;
        if(element == 9) { // 아기 상어면, 위치 업데이트
          babyShark.currentPosition = new Position(i, j);
        } else if(element > 0) { // 물고기가 있으면, 크기에 대한 물고기 수 증가
          if(element == 1) canEatCnt++; // 처음엔 1일 때만 먹을 수 있으니 증가
          remainFishes[element]++;
        }
      }
    }

    while(canEatCnt > 0) { // 먹을 수 있는 애가 있을 때가지 아기상어 이동
    }

    System.out.println(time);
  }
}
