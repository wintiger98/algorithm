import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    final static int P = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // nCr % p = n! % p * (r!)^(p-2) % p * (n-r)!^(p-2) % p
            long answer = factorial(N) % P * power(factorial(R), P - 2) % P * power(factorial(N - R), P - 2) % P;
            bw.write("#" + test_case + " " + (answer % P) + "\n");
            bw.flush();
        }
        bw.close();
    }

    public static long power(long n, long p) {
        if(n == 1) return 1;
        if (p == 0) return 1;
        long half = power(n, p / 2) % P;
        if (p % 2 == 0) {
            return half * half % P;
        } else {
            return half * half % P * n % P;
        }
    }

    public static long factorial(long n) {
        long result = 1;
        for(int i=2; i<n+1; i++) {
            result = result*i%P;
        }
        return result;
    }
}