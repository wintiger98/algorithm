import java.io.*;
import java.util.*;

public class Main {
    // 최대 N개의 종류의 알파벳을 가진 연속된 문자열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String words = br.readLine();

        int cnt = 0;
        int answer = 0;

        int[] visited = new int[26];

        for(int start = 0, end = 0; end < words.length(); end++) {
            if(visited[words.charAt(end) - 'a']++ == 0) cnt++;

            // count > N, 줄어들때까지 start 위치 잡기
            while(N < cnt && start < end) {
                if(--visited[words.charAt(start++) - 'a'] == 0) cnt--;
            }

            answer = Math.max(answer, end - start + 1);
        }

        System.out.println(answer);
    }
}