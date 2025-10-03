import java.io.*;
import java.util.*;

class Main {
	static int N;
	static Person[] people;

	static class Person {
		int health;
		int happy;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		people = new Person[N];
		for(int i=0; i<N; i++) {
			people[i] = new Person();
		}

		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int health = Integer.parseInt(st.nextToken());
			people[i].health = health;
		}

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int happy = Integer.parseInt(st.nextToken());
			people[i].happy = happy;
		}

		int[] dp = new int[101]; // dp(i) = 피가 i일 때, 최대 기쁨
		for(int i=0; i<N; i++) {
			for(int j=100; j>0; j--) {
				if(j <= people[i].health) dp[j] = dp[j];
				else dp[j] = Math.max(dp[j], dp[j-people[i].health] + people[i].happy);
			}
		}
		int answer = Arrays.stream(dp).max().orElse(0);
		System.out.println(answer);
	}
}