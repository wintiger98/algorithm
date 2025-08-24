import java.io.*;
import java.util.*;

class Main {
	static int N;
	static int[][] arr;

	static int[] dx = {0,	1,	0,	-1};
	static int[] dy = {-1,	0,	1,	0};

	static int answer = 0;

	static class Tornado {
		int x, y;
		int curDir = 0;		// 현재 방향 (왼쪽부터 시작)
		int steps = 0;		// 현재 방향으로 이동한 횟수
		int maxSteps = 1;	// 현재 방향으로 이동해야 할 횟수
		int dirChanges = 0;	// 방향을 바꾼 총 횟수

		public Tornado(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void move() {
			while(!(x == 0 && y == 0)) {
				// 현재 위치에서 모래 분산
				sandAway(x, y, curDir);
				
				// 다음 위치로 이동
				x += dx[curDir];
				y += dy[curDir];
				steps++;
				
				// 해당 방향으로 충분히 이동했으면 방향 전환
				if(steps == maxSteps) {
					curDir = (curDir + 1) % 4;
					dirChanges++;
					steps = 0;
					
					// 두 번의 방향 전환마다 이동 거리 증가
					// (1-1-2-2-3-3-4-4... 패턴)
					if(dirChanges % 2 == 0) {
						maxSteps++;
					}
				}
			}
		}

		public void sandAway(int cx, int cy, int dir) {
			// Y 위치 (모래가 있는 위치)
			int yx = cx + dx[dir];
			int yy = cy + dy[dir];
			
			if(!isInArea(yx, yy) || arr[yx][yy] == 0) return;
			
			int origin = arr[yx][yy];
			arr[yx][yy] = 0; // 원래 모래 제거
			
			// A 위치 (Y에서 한 칸 더)
			int ax = yx + dx[dir];
			int ay = yy + dy[dir];
			
			// 상하 방향 계산 (현재 이동 방향의 수직 방향)
			int upDir = (dir + 1) % 4;
			int downDir = (dir + 3) % 4;

			int sum = 0;
			
			// 1% (현재 위치 기준 상하)
			int p1 = origin / 100;
			sum += putSand(cx, cy, upDir, 1, p1);
			sum += putSand(cx, cy, downDir, 1, p1);
			
			// 7% (Y 위치 기준 상하)
			int p7 = origin * 7 / 100;
			sum += putSand(yx, yy, upDir, 1, p7);
			sum += putSand(yx, yy, downDir, 1, p7);
			
			// 2% (Y 위치에서 상하로 2칸)
			int p2 = origin * 2 / 100;
			sum += putSand(yx, yy, upDir, 2, p2);
			sum += putSand(yx, yy, downDir, 2, p2);
			
			// 10% (A 위치 기준 상하)
			int p10 = origin * 10 / 100;
			sum += putSand(ax, ay, upDir, 1, p10);
			sum += putSand(ax, ay, downDir, 1, p10);
			
			// 5% (A 위치에서 한 칸 더)
			int p5 = origin * 5 / 100;
			sum += putSand(ax, ay, dir, 1, p5);
			
			// α (A 위치) - 남은 모래
			int alpha = origin - sum;
			sum += putSand(ax, ay, dir, 0, alpha);
		}

		public int putSand(int x, int y, int dir, int dirCnt, int sand) {
			if(sand <= 0) return 0;
			
			int cx = x + dirCnt * dx[dir];
			int cy = y + dirCnt * dy[dir];
			
			if(isInArea(cx, cy)) {
				arr[cx][cy] += sand;
			} else {
				answer += sand;
			}
			return sand;
		}

		public boolean isInArea(int x, int y) {
			return x >= 0 && x < N && y >= 0 && y < N;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
			}
		}

		Tornado tornado = new Tornado(N/2, N/2);
		tornado.move(); // 시작

		System.out.println(answer);
	}
}