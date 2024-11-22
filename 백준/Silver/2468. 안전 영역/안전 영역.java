import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int answer = 1;
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		StringTokenizer st;

		int minHeight = 101;
		int maxHeight = -1;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
				minHeight = Math.min(minHeight, num);
				maxHeight = Math.max(maxHeight, num);
			}
		}

		for(int height = minHeight; height <= maxHeight; height++) {
			check(height);
		}

		System.out.println(answer);
	}

	private static void check(int height) {
		int cnt = 0;
		visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(isInWater(i, j, height))
					continue;
				if(visited[i][j])
					continue;
				dfs(i, j, height);
				cnt++;
			}
		}

		answer = Math.max(answer, cnt);
	}

	private static void dfs(int x, int y, int height) {
		visited[x][y] = true;

		for(int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			if(cx < 0 || cx >= N || cy < 0 || cy >= N)
				continue;
			if(visited[cx][cy])
				continue;
			if(isInWater(x, y, height))
				continue;
			dfs(cx, cy, height);
		}
	}

	private static boolean isInWater(int x, int y, int height) {
		return arr[x][y] - height <= 0;
	}
}
