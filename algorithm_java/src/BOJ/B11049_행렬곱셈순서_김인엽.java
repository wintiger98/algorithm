import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class B11049_행렬곱셈순서_김인엽 {
    static int N;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        dfs(0, 0, arr);

        System.out.println(answer);
    }
    public static void dfs(int cnt, int sum, int[][] arr) {
        if(sum > answer) return;
        if(cnt == N) {
            answer = sum;
            return;
        }
        for(int i=0; i<arr.length-1; i++) {
            dfs(cnt+1, sum+arr[i][0]*arr[i][1]*arr[i+1][1], arr);
        }
    }

}
