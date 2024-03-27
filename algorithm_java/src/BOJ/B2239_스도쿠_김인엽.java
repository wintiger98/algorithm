import java.io.*;
import java.util.Arrays;

public class B2239_스도쿠_김인엽 {
  final static int N = 9;
  static int[][] answer = new int[N][N];
  static boolean[][] isZeros;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    boolean[][] visited = new boolean[N][N+1]; // 1~9에서 없는 번호 구하기
    isZeros = new boolean[N][N];
    int[] zeroCnt = new int[N]; // 행별 zero 개수
    int[][] arr = new int[N][N];

    for(int i=0; i<N; i++) {
      String[] strs = br.readLine().split("");
      for(int j=0; j<N; j++){
        arr[i][j] = Integer.parseInt(strs[j]);
        visited[i][arr[i][j]] = true;
        if(arr[i][j] == 0) {
          zeroCnt[i]++;
          isZeros[i][j] = true;
        }
      }
    }
    int[][] ps = new int[N][]; // 없는 숫자들 모은 배열
    for(int i=0; i<N; i++) {
      int[] p = new int[zeroCnt[i]]; // 없는 숫자들 넣을 배열
      int idx = 0;
      for(int j=1; j<N+1; j++) {
        if(!visited[i][j]) p[idx++] = j;
      } // 현재 p는 없는 숫자들만 있는 상태
      ps[i] = p;
    }

    dfs(N-1, arr, ps);

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        bw.write(answer[i][j] + "");
      }
      bw.write("\n");
    }
    bw.close();
  }

  public static int[][] deepCopy(int[][] origin) {
    int[][] result = new int[origin.length][];
    for(int i=0; i<origin.length; i++) {
      result[i] = origin[i].clone();
    }
    return result;
  }
  public static void dfs(int depth, int[][] arr, int[][] ps) {
    if(answer[0][0] != 0) return; // 이미 정답이 나왔다면 끝
    if(depth == -1) { // 마지막까지 검사했을 때
      return;
    }
    int[][] originArr = deepCopy(arr);
    int[][] originPs = deepCopy(ps);
    do {
      if(isValid(deepCopy(arr), ps)) {
        return;
      }
    } while(np(ps[depth]));
    dfs(depth-1, originArr, originPs);
  }

  public static boolean isValid(int[][] arr, int[][] ps) {
    // arr 채우기
    for(int i=0; i<N; i++) {
      int idx = 0;
      for(int j=0; j<N; j++) {
        if(isZeros[i][j]) arr[i][j] = ps[i][idx++];
      }
    }

    // 가로 확인
    for(int i=0; i<N; i++) {
      int flag = (1 << 10) - 1; // 9자리 확인
      for(int j=0; j<N; j++) {
        flag &= ~(1 << arr[i][j]);
      }
      if(!(flag == 1)) return false; // 만약 flag가 0이아니면 = 하나라도 남아있는 숫자가 있으면
    }

    // 세로 확인
    int[] flags = new int[N]; // 각 열별로 flag 저장
    Arrays.fill(flags, (1 << 10) - 1);
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        flags[i] &= ~(1 << arr[i][j]);
      }
    }
    // 만약 flags 중에 0이 아닌게 있으면 false
    if(Arrays.stream(flags).filter(f -> f != 1).count() >= 1) return false;

    // 3x3 확인
    flags = new int[N];
    Arrays.fill(flags, (1 << 10) - 1);
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        flags[i/3*3 + j/3] &= ~(1 << arr[i][j]);
      }
    }
    // 만약 flags 중에 0이 아닌게 있으면 false
    if(Arrays.stream(flags).filter(f -> f != 1).count() >= 1) return false;
    answer = arr;
    return true;
  }
  public static boolean np(int[] p) { // next permutation
    int n = p.length;

    int i = n - 1;
    while(i > 0 && p[i-1] >= p[i]) i--;

    if(i == 0) return false;

    int j = n - 1;
    while(p[i-1] >= p[j]) j--;

    swap(p, i-1, j);

    int k = n - 1;
    while(i<k) swap(p, i++, k--);
    return true;
  }
  public static void swap(int[] p, int a, int b) {
    int tmp = p[a];
    p[a] = p[b];
    p[b] = tmp;
  }
}