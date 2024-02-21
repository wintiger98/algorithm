import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 	55540	332
public class B15683_감시_김인엽 {
  static int N, M;
  static List<CCTV> cctvs;

  static int[] dx = {-1,0,1,0}; // 위부터 90도씩 순서. (상->우->하->좌)
  static int[] dy = {0,1,0,-1};
  static int answer;

  final static int WATCHED = -1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    answer = N*M;
    int[][] arr = new int[N][M];
    // 각 버젼별 시시티비 좌표 저장
    cctvs = new ArrayList<>();

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        int tmp = Integer.parseInt(st.nextToken());
        arr[i][j] = tmp;
        if(tmp == 0 || tmp == 6) continue;
        // 시시티비 저장(위치, 종류)
        CCTV cctv = new CCTV(new Pair(i, j), tmp);
        cctvs.add(cctv);
      }
    }

    // 그때마다 최선을 다해 감시하는 방향으로
    dfs(0, arr);

    System.out.println(answer);
  }

  static void dfs(int depth, int[][] arr) {
    // 끝까지 탐색했으면 정답 찾고 끝
    if(depth == cctvs.size()) {
//      System.out.println();
//      for (int[] a : arr) {
//        System.out.println(Arrays.toString(a));
//      }
//      System.out.println();
      answer = Math.min(answer, calcAnswer(arr));
      return;
    }

    // 해당 시시티비 가능한 경우만큼 dfs()
    for(int[] canDir : cctvs.get(depth).canDirs){
      cctvs.get(depth).curDir = canDir;
      dfs(depth+1, cctvs.get(depth).watch(arr));
    }
  }

  static int calcAnswer(int[][] arr) {
    int answer = 0;

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(arr[i][j] == 0) answer++;
      }
    }
    return answer;
  }

  // 좌표
  static class Pair {
    int x, y;

    public Pair(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }
  }

  static class CCTV {
    Pair pair; // 위치
    List<int[]> canDirs; // 볼 수 있는 방향들
    int[] curDir; // 볼 방향
    int type; // 종류

    void setCanDirs() {
      canDirs = new ArrayList<>();
      switch(type) {
        case 1:
          // 1번은 0 / 1 / 2 / 3 방향인덱스에 대해 가능
          canDirs.add(new int[] {0});
          canDirs.add(new int[] {1});
          canDirs.add(new int[] {2});
          canDirs.add(new int[] {3});
          break;
        case 2:
          // 2번은 0,2 / 1,3 방향인덱스에 대해 가능
          canDirs.add(new int[] {0,2});
          canDirs.add(new int[] {1,3});
          break;
        case 3:
          // 3번은 0,1 / 1,2 / 2,3 / 3,0 에대해 가능
          canDirs.add(new int[] {0,1});
          canDirs.add(new int[] {1,2});
          canDirs.add(new int[] {2,3});
          canDirs.add(new int[] {3,0});
          break;
        case 4:
          canDirs.add(new int[] {0,1,2});
          canDirs.add(new int[] {1,2,3});
          canDirs.add(new int[] {2,3,0});
          canDirs.add(new int[] {3,0,1});
          break;
        case 5:
          canDirs.add(new int[] {0,1,2,3});
          break;
      }
    }

    public CCTV(Pair pair, int type) {
      this.pair = pair;
      this.type = type;
      this.setCanDirs(); // 가능한 방향들 설정해주기
    }

    int[][] watch(int[][] arr) {
      int[][] newArr = new int[N][M];
      for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
          newArr[i][j] = arr[i][j];
        }
      }
      // 본방향 채우기
      for(int i: curDir) {
        fill(newArr, i);
      }

      return newArr;
    }

    void fill(int[][] newArr, int di) { // 채우기
      int x = pair.x;
      int y = pair.y;
      while(true) {
        x += dx[di];
        y += dy[di];
        // 범위 벗어나면 탈출
        if(x < 0 || x >= N || y < 0 || y >= M) break;
        // 벽 만나면 탈출
        if(newArr[x][y] == 6) break;
        // 0일 때만 보기
        else if(newArr[x][y] == 0) newArr[x][y] = WATCHED;

      }
    }
  }
}