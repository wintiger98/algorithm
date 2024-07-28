import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int stand = (1 << N) - 1; // N자리가 1인 수

            String answer = "OFF";
            if((M & stand) == stand) { // 만약 N자리만 남겼을 때 모두 1이면 ON
                answer = "ON";
            }

            bw.write("#" + test_case + " " + answer + "\n");
            bw.flush();
        }
        bw.close();
    }
}