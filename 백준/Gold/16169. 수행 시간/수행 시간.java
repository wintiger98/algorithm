import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] totalTime; // 각 컴퓨터의 총 동작 완료 시간
    static int[] times;     // 각 컴퓨터의 동작 시간
    static List<List<Integer>> ranks; // 각 계급에 해당하는 컴퓨터 목록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        totalTime = new int[n + 1];
        times = new int[n + 1];
        ranks = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            ranks.add(new ArrayList<>());
        }

        // 컴퓨터의 계급과 동작 시간 입력
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int rank = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            ranks.get(rank).add(i); // 계급에 컴퓨터 추가
            times[i] = time;        // 컴퓨터 동작 시간 저장
        }

        // 해결 함수 호출
        solve();

        // 가장 큰 동작 완료 시간 출력
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, totalTime[i]);
        }
        System.out.println(result);
    }

    static void solve() {
        // 첫 번째 계급에 있는 컴퓨터의 동작 완료 시간 설정
        for (int i : ranks.get(1)) {
            totalTime[i] = times[i];
        }

        // 계급별로 처리
        for (int i = 1; i < n; i++) {
            for (int node : ranks.get(i)) {
                for (int next : ranks.get(i + 1)) {
                    // 다음 계급 컴퓨터의 총 동작 완료 시간 계산
                    totalTime[next] = Math.max(totalTime[next], 
                        (next - node) * (next - node) + totalTime[node] + times[next]);
                }
            }
        }
    }
}