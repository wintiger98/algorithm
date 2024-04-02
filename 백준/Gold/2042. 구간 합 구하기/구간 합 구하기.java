import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N+1];
        FenwickTree fenwickTree = new FenwickTree(N);

        for(int i=1; i<=N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            fenwickTree.update(i,  arr[i]);
        }

        for(int i=0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1) {
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());
                fenwickTree.update(b, c-arr[b]);
                arr[b] = c;
            } else if(a == 2) {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                bw.write((fenwickTree.sum(c)- fenwickTree.sum(b-1)) + "\n");
            }
        }

        bw.close();
    }

    static class FenwickTree {
        long[] tree;

        public FenwickTree(int N) {
            this.tree = new long[N+1];
        }

        public void update(int idx, long num) {
            while(idx < tree.length) {
                tree[idx] += num;
                idx += (idx & -idx);
            }
        }

        public long sum(int idx) {
            long result = 0;
            while(idx > 0) {
                result += tree[idx];
                idx -= (idx & -idx);
            }
            return result;
        }
    }
}