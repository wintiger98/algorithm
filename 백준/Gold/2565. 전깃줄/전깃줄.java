import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ElecPair[] elecPairs = new ElecPair[N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            elecPairs[i] = new ElecPair(left, right);
        }
        // 1. left 기준 오름차순 정렬
        Arrays.sort(elecPairs);
        // 2. right에 대해 가장 긴 증가하는 부분 순열 개수 찾기
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(elecPairs[j].right < elecPairs[i].right) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        System.out.println(N -Arrays.stream(dp).max().orElse(1000));
    }

    static class ElecPair implements Comparable<ElecPair> {
        int left, right;

        public ElecPair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(ElecPair o) {
            return this.left - o.left; // left 기준 오름차순
        }
    }
}