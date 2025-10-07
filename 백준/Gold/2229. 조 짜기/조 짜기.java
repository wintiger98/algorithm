import java.io.*;
import java.util.*;

class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<i; j++) {
				dp[i] = Math.max(dp[i], dp[j-1] + getScore(j, i, arr));
			}
		}

		System.out.println(dp[N]);
	}

	private static int getScore(int start, int end, int[] arr) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i=start-1; i<=end-1; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
		return max - min;
	}
}