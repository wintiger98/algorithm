import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 160536	708
public class B20056_마법사상어와파이어볼_김인엽 {

  // 위부터 시계방향
  static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
  static int N; // 맵 크기
  static FireBallMoongchi[][] arr; // 맵: 파이어볼 뭉치 갖고있음
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    arr = new FireBallMoongchi[N+1][N+1]; // 맵(각각은 파이어볼 뭉치가 존재가능)
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        arr[i][j] = new FireBallMoongchi(new Pair(i, j));
      }
    }

    // 파이어볼 저장
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int mass = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());
      int dir = Integer.parseInt(st.nextToken());
      arr[x][y].fireballList.add(new FireBall(x, y, mass, speed, dir));
    }

    for (int i = 0; i < K; i++) {
      moveFireball(); // 명령하기
      merge(); // 합치기
    }

    System.out.println(remainMassSum());
  }

  public static int remainMassSum() {
    int result = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        for(FireBall fireball: arr[i][j].fireballList) {
          result += fireball.mass;
        }
      }
    }
    return result;
  }

  // 합치는거
  public static void merge() {
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (arr[i][j].fireballList.size() < 2) {
          continue;
        }
        // 두 개 이상의 파이어볼이 있다면, 합치고 나누기
        arr[i][j].mergeAndDivide();
      }
    }
  }

  // 명령하는거
  public static void moveFireball() {
    // 모든 파이어볼 이동
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        List<FireBall> tmpList = new ArrayList<>(arr[i][j].fireballList);
        for (FireBall fireBall : tmpList) {
          fireBall.move();
        }
      }
    }
  }

  static class Pair {

    int x, y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * 파이어볼 저장 클래스
   *
   * @value x, y: 좌표
   * @value mass : 질량
   * @value speed : 속도
   * @value dir : 방향
   */
  static class FireBall {

    Pair pair;
    int mass;
    int speed;
    int dir;

    public FireBall(int x, int y, int mass, int speed, int dir) {
      this.pair = new Pair(x, y);
      this.mass = mass;
      this.speed = speed;
      this.dir = dir;
    }

    void move() { // 이동하는 함수
      int power = speed;
      // 기존에 있던 곳에서 나가기
      arr[this.pair.x][this.pair.y].fireballList.remove(this);
      while (power > 0) { // 주어진 스피드만큼 이동
        this.pair.x += dx[dir];
        this.pair.y += dy[dir];
        power--;
        if (this.pair.x <= 0 || this.pair.x > N || this.pair.y <= 0
            || this.pair.y > N) { // 문제엔 안적혀있지만 혹시 모르니 맵을 나가려고하면 멈추기
          this.pair.x -= dx[dir];
          this.pair.y -= dy[dir];
          break;
        }
      }
      // 새 위치에 추가
      arr[this.pair.x][this.pair.y].fireballList.add(this);
    }
  }

  static class FireBallMoongchi {
    Pair pair;
    List<FireBall> fireballList = new ArrayList<>(); // 파이어볼들

    public FireBallMoongchi(Pair pair) {
      this.pair = pair;
    }

    void mergeAndDivide() {
      int massSum = 0;
      int speedSum = 0;
      int evenCnt = 0, oddCnt = 0; // 짝수, 홀수 카운트
      int size = fireballList.size();
      for (FireBall fireBall : fireballList) {
        massSum += fireBall.mass;
        speedSum += fireBall.speed;
        if(fireBall.dir % 2 == 0) evenCnt++;
        else oddCnt++;
      }

      fireballList.clear(); // 초기화

      if (massSum < 5) { // 질량이 0인 파이어볼 소멸 : 모든 질량 합이 5보다 낮으면 사라짐
        return;
      }

      // 1. 질량 정하기
      int mass = massSum / 5; // 새 질량
      // 2. 속도 구하기
      int speed = speedSum / size;

      // 3. 방향 구하기
      int[] dirs = (evenCnt == 0 || oddCnt == 0) ? new int[] {0,2,4,6} : new int[] {1,3,5,7};

      // 네 파이어볼로 나누기
      for (int dir : dirs) {
        fireballList.add(new FireBall(pair.x, pair.y, mass, speed, dir));
      }
    }
  }
}
