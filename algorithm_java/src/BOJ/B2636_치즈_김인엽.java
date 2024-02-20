import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2636_치즈_김인엽 {
  static int N, M; // 가로, 세로
  static int[][] arr; // 맵

  static int startX, startY; // 탐색 시작 지점

  static int[] dx = {1,-1,0,0}; // 방향
  static int[] dy = {0,0,1,-1};

  // time: 녹아서 없어지는데 걸리는 시간(=arr에 해당 시간에 지나간거 모두 표기할때도 사용)
  // lastCheeses: 마지막 남아있던 치즈조각 개수
  static int time, lastCheeses;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    arr = new int[N][M];

    // 맵 입력받기
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    // 탐색 시작
    do {
      time--;
      print();
    }while(bfs());

    System.out.println(-(time+1) + "\n" + lastCheeses);
  }

  public static boolean bfs() { // 난 공기다 생각하고 탐색 시작!
    int curCheeses = 0;
    Queue<Pair> queue = new ArrayDeque<>();
    queue.add(new Pair(startX, startY));

    while(!queue.isEmpty()) {
      Pair pair = queue.poll();

      // 만약 치즈에 닿으면, 부서버리기
      if(arr[pair.x][pair.y] == 1) {
        arr[pair.x][pair.y] = time;
        startX = pair.x;
        startY = pair.y;
        curCheeses++;
        continue;
      }

      for(int i=0; i<dx.length; i++) {
        int cx = pair.x + dx[i];
        int cy = pair.y + dy[i];
        // 범위 벗어나면 패스
        if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue;
        // 방문했다면 패스
        if(arr[cx][cy] == time) continue;
        queue.add(new Pair(cx,cy));
      }
    }
    if(curCheeses>0) lastCheeses = curCheeses;
    return curCheeses>0;
  }

  static class Pair {
    int x, y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void print() {
    System.out.println("현재 시각 : " + -time + "시간");
    for(int[] a: arr) System.out.println(Arrays.toString(a));
    System.out.println();
  }
}