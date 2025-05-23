import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 어떻게 풀까?
 * N 번째 큰 수 -> 위에서 N번째 수. 얘보다 큰 수는 N-1개 있음
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 행렬 크기 & N번째 수

		PriorityQueue<Integer> pq = new PriorityQueue<>(); // N 크기의 pq

		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(pq.size() < N){
					pq.add(num);
					continue;
				}

				int min = pq.peek();
				
				if(num < min) { // 최소값보다 작으면 continue
					continue;
				} else { // 그 외에는 빼고 넣기
					pq.poll();
					pq.add(num);
				}
			}
		}

		System.out.println(pq.poll());
	}
}
