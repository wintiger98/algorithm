import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S5644_무선충전_김인엽 {

  final static int N = 11;
  static int[][] arr;
  static int A, M, answer;
  static int[] aPath, bPath;
  static BatteryCharger[] batteryChargers;
  // 이동 방향 : 이동X, 상, 우, 하, 좌 => 내 방향대로는 이동X, 좌, 하, 우, 상
  final static int[] dx = {0, 0, 1, 0, -1};
  final static int[] dy = {0, -1, 0, 1, 0};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    StringTokenizer st;
    for (int test_case = 1; test_case <= T; test_case++) {
      arr = new int[N][N]; // 지도의 가로세로는 10 + 1

      st = new StringTokenizer(br.readLine());
      M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken()); // BC의 개수
      batteryChargers = new BatteryCharger[A];

      // userA의 이동경로
      aPath = new int[M];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < M; i++) {
        aPath[i] = Integer.parseInt(st.nextToken());
      }

      // userB의 이동경로
      bPath = new int[M];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < M; i++) {
        bPath[i] = Integer.parseInt(st.nextToken());
      }

      int id = 0; // id 0부터 시작
      for (int i = 0; i < A; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // 좌표
        int y = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken()); // 충전 범위
        int p = Integer.parseInt(st.nextToken()); // 처리량
        BatteryCharger bc = new BatteryCharger(x, y, c, p, id++); // BC 생성
        batteryChargers[i] = bc;
        bc.makeArea();
      }
      connect(0, 1, 1, 10, 10, 0);

      System.out.println("#" + test_case + " " + answer);
    }
  }

  // 두 사람의 타겟 BC 찾아서 접속시켜주기
  public static void connect(int time, int x1, int y1, int x2, int y2, int chargeSum) {
    // 1. 두 사람의 영향권 리스트 찾기
    List<Integer> bcList4userA = new ArrayList<>();
    List<Integer> bcList4userB = new ArrayList<>();

    int userAFlag = arr[x1][y1];
    int userBFlag = arr[x2][y2];
    for (int i = 0; i < A; i++) {
      if ((userAFlag & (1 << i)) != 0) {
        bcList4userA.add(i);
      }
      if ((userBFlag & (1 << i)) != 0) {
        bcList4userB.add(i);
      }
    }
    if (time == M) {
      if (bcList4userA.isEmpty() && bcList4userB.isEmpty()) {

      } else if (bcList4userA.isEmpty()) { // 만약 A가 없었다면 b만 고고
        chargeSum += getMax(bcList4userB);
      } else if (bcList4userB.isEmpty()) { // 만약 B가 없다면 a만 고고
        chargeSum += getMax(bcList4userA);
      } else {
        chargeSum += getMax(bcList4userA) + getMax(bcList4userB);
      }
      answer = Math.max(answer, chargeSum);
      return;
    }

    // 이동
    int newX1 = x1 + dx[aPath[time]];
    int newX2 = x2 + dx[bPath[time]];
    int newY1 = y1 + dy[aPath[time]];
    int newY2 = y2 + dy[bPath[time]];

    if(isBack(newX1)) newX1 -= dx[aPath[time]];
    if(isBack(newX2)) newX2 -= dx[bPath[time]];
    if(isBack(newY1)) newY1 -= dy[aPath[time]];
    if(isBack(newY2)) newY2 -= dy[bPath[time]];

    // 만약 둘다 빵개라면 다음 시간으로 고고
    if (bcList4userA.isEmpty() && bcList4userB.isEmpty()) {
      connect(time + 1, newX1, newY1, newX2, newY2, chargeSum);
    } else if (bcList4userA.isEmpty()) { // 만약 A가 없었다면 b만 고고
      for (int bcB : bcList4userB) {
        connect(time + 1, newX1, newY1, newX2, newY2, chargeSum + batteryChargers[bcB].performance);
      }
    } else if (bcList4userB.isEmpty()) { // 만약 B가 없다면 a만 고고
      for (int bcA : bcList4userA) {
        connect(time + 1, newX1, newY1, newX2, newY2, chargeSum + batteryChargers[bcA].performance);
      }
    } else {
      for (int bcA : bcList4userA) {
        for (int bcB : bcList4userB) {
          if (bcA == bcB) { // 만약 같은걸 선택할거라면 절반씩 가져가기
            connect(time + 1, newX1, newY1, newX2, newY2, chargeSum + batteryChargers[bcA].performance);
          } else {
            connect(time + 1, newX1, newY1, newX2, newY2, chargeSum + batteryChargers[bcA].performance + batteryChargers[bcB].performance);
          }
        }
      }
    }

  }
  static int getMax(List<Integer> list) {
    int max = 0;
    for (int bc : list) {
      max = Math.max(max, batteryChargers[bc].performance);
    }
    return max;
  }
  static boolean isBack(int num) {
    return num<=0 || num >= N;
  }
  // 거리 계산(영역 전개하려고)
  static int calcDist(int x1, int y1, int x2, int y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  static class BatteryCharger {

    int id; // 아이디(식별자)
    int x, y; // 위치
    int chargeRange; // 충전 범위
    int performance; // 처리량
    int peopleCnt; // 여기에 접속한 사람수

    public BatteryCharger(int x, int y, int chargeRange, int performance, int id) {
      super();
      this.x = x;
      this.y = y;
      this.chargeRange = chargeRange;
      this.performance = performance;
      this.id = id;
    }

    // 영역전개: 거리가 chargeRange 이하인 친구들에게 영역전개 by bitmasking(최대 8이라서)
    void makeArea() {
      for (int i = x - chargeRange; i < x + chargeRange + 1; i++) {
        for (int j = y - chargeRange; j < y + chargeRange + 1; j++) {
          // 범위 벗어나는지 체크
          if (i <= 0 || i >= N || j <= 0 || j >= N) {
            continue;
          }

          // 만약 거리가 충전범위이내라면, arr에 표기(by bitmasking)
          if (calcDist(x, y, i, j) <= chargeRange) {
            arr[i][j] |= 1 << id;
          }
        }
      }
    }
  }
}
