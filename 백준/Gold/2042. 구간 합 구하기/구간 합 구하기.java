import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //https://www.acmicpc.net/problem/2042
    //구간 합 구하기
    static int N, M, K;
    static long[] arr;
    static long[] tree;

    static void init() {
        for (int i = 1; i < N + 1; i++) {
            int check = i & -i;
            tree[i] = arr[i];
            while (check < N+1) {
                int count = check & -check;
                tree[i] += tree[check];
                check += count;
            }
        }
    }

    static void update(int from, long to) {
        int index = from;
        while (index <= N) {
            tree[index] += to;
            int jump = index & -index;
            index += jump;
        }
        arr[from] = to;
    }

    static long sum(int n) {
        long ans = 0L;
        while (n > 0) {
            int check = n & -n;
            ans += tree[n];
            n -= check;
        }
        return ans;
    }

    static long get(int l, int r) {
        return sum(r) - sum(l - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder ans = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(bf.readLine());
            update(i, arr[i]);
        }
//        init();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());
            int c = Integer.parseInt(st.nextToken());
            if (c == 1) {
                int from = Integer.parseInt(st.nextToken());
                long to = Long.parseLong(st.nextToken());
                update(from, to - arr[from]);
                arr[from] = to;
            } else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                ans.append(get(l, r)).append("\n");
            }
        }
        System.out.println(ans);
    }
}