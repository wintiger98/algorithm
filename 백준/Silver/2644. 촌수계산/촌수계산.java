import java.io.*;
import java.util.*;

class Main {
	static int answer = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] adjArr = new int[N+1][N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int person1 = Integer.parseInt(st.nextToken());
		int person2 = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adjArr[x][y] = 1;
			adjArr[y][x] = 1;
		}

		bfs(adjArr, person1, person2);

		System.out.println(answer);
	}

	private static void bfs(int[][] adjArr, int from, int to) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[adjArr.length];
		visited[from] = true;
		q.add(new int[]{from, 0});

		while(!q.isEmpty()) {
			int[] current = q.poll();
			int cur = current[0];
			int cnt = current[1];
			if(cur == to) {
				answer = cnt;
				continue;
			}
			
			for(int i=1; i<adjArr.length; i++) {
				int val = adjArr[cur][i];
				if(val == 0) continue; // 연결 X -> 패스
				if(visited[i]) continue; // 방문 -> 패스
				q.add(new int[]{i, cnt+1});
				visited[i] = true;
			}
		}
	}
}