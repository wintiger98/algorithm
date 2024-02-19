import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2140_지뢰찾기_김인엽 {
  static int N;
  static int[][] arr; // 한칸이 뜻하는 지뢰 최대가 3개니 5부터 사용. 5: 지뢰. 6 : 지뢰X
  static int answer;
  static int[] dx2FindUp = {-1,-1,-1};
  static int[] dy2FindUp = {-1,0,1};

  static int[] dx2FindLeft = {-1,0,1};
  static int[] dy2FindLeft = {-1,-1,-1};

  static int[] dx2FindDown = {1,1,1};
  static int[] dy2FindDown = {-1,0,1};

  static int[] dx2FindRight = {-1,0,1};
  static int[] dy2FindRight = {1,1,1,};

  final static int MINE = 5;
  final static int NOMINE = 6;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    StringTokenizer st;
    for(int i=0; i<N; i++) {
      String[] strs = br.readLine().split("");
      for(int j=0; j<N; j++) {
        String tmp = strs[j];
        // #면 -1로 바꾸기
        if(tmp.equals("#")) {
          arr[i][j] = -1;
        } else {
          arr[i][j] = Integer.parseInt(tmp);
        }
      }
    }
    // 만약 4보다 크다면, 더 안쪽이 존재 -> 최소개수가 보장되어있음.
    if(N>4) {
      answer += (N-4)*(N-4);
    }

    // 네 꼭짓점 확인
    checkVertexes();

    // 나머지 확인
    for(int i=1; i<N-1; i++) {
      for(int j=1; j<N-1; j++) {
        // 안쪽 한 겹만 확인
        if(i == 1 || j == 1 || i == N-2 || j == N-2) {
          if(arr[i][j] == 5 || arr[i][j] == 6) continue; // 이미 확정된 곳은 패스
          findMine(i, j);
        }
      }
    }

    System.out.println();
    for (int[] a : arr) {
      System.out.println(Arrays.toString(a));
    }
    System.out.println();

    System.out.println(answer);
  }

  private static void findMine(int x, int y) {
    int[] dx, dy;
    boolean isUpDown = false; // -> up/down 은 왼쪽, 오른쪽 확인 / left/right는 위/아래 확인
    if(x == 1){
      dx = dx2FindUp;
      dy = dy2FindUp;
      isUpDown = true;
    } else if(x == N-2) {
      dx = dx2FindDown;
      dy = dy2FindDown;
      isUpDown = true;
    } else if(y == 1) {
      dx = dx2FindLeft;
      dy = dy2FindLeft;
    } else {
      dx = dx2FindRight;
      dy = dy2FindRight;
    }
    // 각 방향에 맞게 탐색
    for(int i=0; i<dx.length; i++) {
      int cx = x + dx[i];
      int cy = y + dy[i];
      switch(arr[cx][cy]) {
        case 0:
          noMine(x, y);
          break;
        case 1:
          if(isUpDown) {
            if(arr[x][y-1] == NOMINE){
              yesMine(x,y);
            }else {
              noMine(x, y);
            }
          } else{
            if(arr[x-1][y] == NOMINE){
              yesMine(x,y);
            }else {
              noMine(x, y);
            }
          }
          break;
        case 2:
        case 3:
          yesMine(x, y);
          break;
      }
    }
  }

  private static void checkVertexes() {
    // 꼭짓점은 모두 0, 1 둘 중에 하나만 가능
    switch(arr[0][0]) {
      // 지뢰X
      case 0:
        noMine(1, 1);
        break;
      // 지뢰O
      case 1:
        yesMine(1, 1);
        break;
    }

    switch(arr[0][N-1]) {
      // 지뢰X
      case 0:
        noMine(1, N-2);
        break;
      // 지뢰O
      case 1:
        yesMine(1, N-2);
        break;
    }

    switch(arr[N-1][0]) {
      // 지뢰X
      case 0:
        noMine(N-2, 1);
        break;
      // 지뢰O
      case 1:
        yesMine(N-2, 1);
        break;
    }

    switch(arr[N-1][N-1]) {
      // 지뢰X
      case 0:
        noMine(N-2, N-2);
        break;
      // 지뢰O
      case 1:
        yesMine(N-2, N-2);
        break;
    }
  }

  private static void noMine(int x, int y) {
    arr[x][y] = NOMINE;
  }

  private static void yesMine(int x, int y) {
    answer++;
    arr[x][y] = MINE;
  }
}
