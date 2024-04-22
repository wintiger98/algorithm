import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] width, height, weight;
	static int[] memo, count, path;
	static ArrayList<Integer>[] adj;
	
	static int dfs(int index) {
		if(memo[index] != 0) return memo[index];
		memo[index] = height[index];
		count[index] = Math.max(count[index], 1);
		int maxH = 0;
		for(Integer next : adj[index]) {
			int cal = dfs(next);
			if(maxH < cal) {
				path[index] = next;
				maxH = cal;
				count[index] = count[next] + 1;
			}
			else if(maxH == cal && count[index] < count[next] + 1) {
				path[index] = next;
				maxH = cal;
				count[index] = count[next] + 1;
			}
		}
		memo[index] += maxH;
		return memo[index];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		width = new int[N + 1];
		height = new int[N + 1];
		weight = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			width[i] = Integer.parseInt(st.nextToken());
			height[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				if(width[i] < width[j] && weight[i] < weight[j]) {
					adj[i].add(j);
				}
			}
		}
		int max_height = 0, root = 0;
		memo = new int[N + 1];
		count = new int[N + 1];
		path = new int[N + 1];
		int[] max_path = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			int curr_height = dfs(i);
			if(max_height < curr_height) {
				max_height = Math.max(max_height, curr_height);
				root = i;
				int next = i;
				while(path[next] != 0) {
					max_path[next] = path[next];
					next = path[next];
				}
			}
		}
		System.out.println(count[root]);
		int next = root;
		while(next != 0) {
			System.out.println(next);
			next = max_path[next];
		}
	}

}