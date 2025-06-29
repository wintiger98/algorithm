import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < T; testCase++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            long answer = 0;

            for(int i=0; i<K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            while(true) {
                if(pq.size() == 1) break;

                long num1 = pq.poll();
                long num2 = pq.poll();

                answer += num1 + num2;
                pq.offer(num1 + num2);
            }
            bw.write(answer+"\n");
            bw.flush();
        }
        bw.close();
    }
}