import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2140_지뢰찾기_김인엽 {
  static int N;
  static int[][] arr; // 한칸이 뜻하는 지뢰 최대가 3개니 5부터 사용. 5: 지뢰. 6 : 지뢰X
  static int answer;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    StringTokenizer st;
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        String tmp = st.nextToken();
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
    checkEdges();

    System.out.println(answer);
  }

  private static void checkEdges() {
    // 맨 위
    for(int i=1; i<= N-2; i++) {
      switch(arr[0][i]) {
        case 0:

          break;
      }
    }

    // 맨 왼쪽
    for(int i=1; i<= N-2; i++) {

    }

    // 맨 오른쪽
    for(int i=1; i<= N-2; i++) {

    }

    // 맨 아래
    for(int i=1; i<=N-2; i++) {

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
        noMine(1, N-2);
        break;
      // 지뢰O
      case 1:
        yesMine(1, N-2);
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
    arr[x][y] = 6;
  }

  private static void yesMine(int x, int y) {
    answer++;
    arr[x][y] = 5;
  }
}
