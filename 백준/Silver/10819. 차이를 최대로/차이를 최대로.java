import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] numbers;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        visited = new boolean[N];
        numbers = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0);
        System.out.println(answer);
    }
    
    public static void permutation(int count) {
        if(count == N) {
            check();
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            numbers[count] = arr[i];
            visited[i] = true;
            permutation(count+1);
            visited[i] = false;
        }
    }

    public static void check() {
        int sum = 0;
        for (int i = 0; i < N-1; i++) {
            sum += Math.abs(numbers[i] - numbers[i+1]);
        }
        answer = Math.max(sum, answer);
    }
}