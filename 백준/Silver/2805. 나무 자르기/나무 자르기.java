import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
	static long[] trees;
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 나무 채우기
		trees = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		
		binarySearch(0, Arrays.stream(trees).max().orElseThrow(NoSuchElementException::new), M);
		System.out.println(answer);
	}
	private static void binarySearch(long start, long end, int target) {
		if(start > end) return;
		
		long mid = (start + end) / 2;
		long checkedValue = check(mid);
//		if(checkedValue == target) { // 알맞게 잘랐으면 끝
//			answer = mid;
//			return; 
		if(checkedValue >= target) { // 너무 많이 잘랐으면, 높이를 더 높여도 됨
			answer = Math.max(answer, mid);
			binarySearch(mid+1, end, target);
		} else { // 너무 적게 잘랐다면, 높이를 더 낮추기
			binarySearch(start, mid-1, target); 
		}
	}
	private static long check(long cut) {
		return Arrays.stream(trees).filter(t -> t > cut).map(t -> t - cut).sum();
	}

}