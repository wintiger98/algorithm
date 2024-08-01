import java.io.*;
import java.util.*;


class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int total = 0;

        while(start <= N && end <= N) {
            if(total >= S && answer > end - start) {
                answer = end - start;
            }

            if(total < S) {
                total += arr[end++];
            } else {
                total -= arr[start++];
            }
        }

        if(answer == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(answer);

    }
}