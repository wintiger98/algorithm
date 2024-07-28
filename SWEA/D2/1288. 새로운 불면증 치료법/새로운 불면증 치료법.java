import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int test_case = 1; test_case <= T; test_case++) {
            String curNum = br.readLine(); // 숫자
            int N = Integer.parseInt(curNum);
            int visited = 0; // 방문여부 (111111111 이 될때까지)
            int answer = 1; // 정답

            while(visited != ((1 << 10) - 1)) {
                // 현재 숫자에서 세기
                for(int idx = 0; idx < curNum.length(); idx++) {
                    // 각 자릿수 방문 처리
                    visited |= (1 << Integer.parseInt(String.valueOf(curNum.charAt(idx))));
                }
                answer++;
                curNum = String.valueOf(N*answer);
            }
            bw.write("#" + test_case + " " + (N*(answer-1)) + "\n");
            bw.flush();
        }
        bw.close();
    }
}