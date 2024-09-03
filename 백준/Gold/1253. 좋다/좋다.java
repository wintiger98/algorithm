import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 배열 넣기
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 배열 정렬
		Arrays.sort(arr);

		// 개수세기
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int cur = arr[i];

			int start = 0;
			int end = N - 1;
			boolean isCan = false;

			while(start < end) {
				if(start == i) {
					start++;
					continue;
				}
				if(end == i) {
					end--;
					continue;
				}

				int sum = arr[start] + arr[end];
				if(sum == cur) {
					answer++;
					break;
				} else if(sum < cur) {
					start++;
				} else {
					end--;
				}
			}
		}
		System.out.println(answer);
	}
}
