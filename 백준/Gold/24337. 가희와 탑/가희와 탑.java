import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 리더
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 라이터
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        int N = Integer.parseInt(st.nextToken());
        // 정답 배열
        List<Integer> answer = new ArrayList<>();

        // 가희가 본 건물 개수
        int gahuiCnt = Integer.parseInt(st.nextToken());
        // 단비가 본 건물 개수
        int danbeeCnt = Integer.parseInt(st.nextToken());

        // 가장 높은 건물 높이의 최소값
        int maxHeight = Math.max(gahuiCnt, danbeeCnt);

        // 1. 1 ~ (a-1) 까지 건물 세우기
        for(int i=1; i<gahuiCnt; i++) {
            answer.add(i);
        }

        // 2. max height 건물 세우기
        answer.add(maxHeight);

        // 3. (b-1) ~ 1 까지 건물 세우기
        for(int i=danbeeCnt-1; i>0; i--) {
            answer.add(i);
        }

        // 4. N 조건 추가
        // (1) answer의 사이즈가 N보다 크면, 조건 불만족 => -1 출력
        // (2) 그 이외의 경우, 부족한 숫자만큼 제일 왼쪽 값의 오른쪽으로 1의 높이 건물 세우기
        if(answer.size() > N) bw.write(-1 + "");
        else {
            // 맨 왼쪽거 먼저 출력
            bw.write(answer.get(0) + " ");
            // 부족한 숫자만큼 1 높이 출력
            for(int i=0; i<N - answer.size(); i++) bw.write(1 + " ");
            // 나머지 출력
            for(int i=1; i<answer.size(); i++) bw.write(answer.get(i) + " ");
        }

        // 라이터 닫아주기 및 출력
        bw.close();
    }
}