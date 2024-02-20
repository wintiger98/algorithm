import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
// 13300	116
public class B2636_치즈_김인엽 {
  static int N, M; // 가로, 세로
  static int[][] arr; // 맵
  static int[] dx = {1,-1,0,0}; // 방향
  static int[] dy = {0,0,1,-1};
  static int startX, startY;

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

    while(bfs()) {
      time++;
    }

    System.out.println(time + "\n" + lastCheeses);
  }

  public static boolean bfs() { // 난 공기다 생각하고 탐색 시작!
    boolean[][] visited = new boolean[N][M]; // 방문여부 확인
    int curCheeses = 0; // 지금 시간에서 부순 치즈 개수
    Queue<Pair> queue = new ArrayDeque<>();
    queue.add(new Pair(startX, startY)); // 0,0 에서 시작
    visited[startX][startY] = true;

    while(!queue.isEmpty()) {
      Pair pair = queue.poll();

      // 만약 치즈에 닿으면, 부서버리기
      if(arr[pair.x][pair.y] == 1) {
        arr[pair.x][pair.y] = 0;
        curCheeses++; // 부순 치즈 개수 증가
        continue;
      }

      for(int i=0; i<dx.length; i++) {
        int cx = pair.x + dx[i];
        int cy = pair.y + dy[i];
        // 범위 벗어나면 패스
        if(cx < 0 || cx >= N || cy < 0 || cy >= M) continue;
        // 방문했다면 패스
        if(visited[cx][cy]) continue;

        queue.add(new Pair(cx,cy));
        visited[cx][cy] = true;
      }
    }
    if(curCheeses>0) lastCheeses = curCheeses; // 부쉈다면, 마지막 치즈 개수 업데이트
    return curCheeses>0; // 만약 치즈를 못부쉈으면 끝
  }

  static class Pair {
    int x, y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}