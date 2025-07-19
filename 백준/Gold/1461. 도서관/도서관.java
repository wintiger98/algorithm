import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> minus = new ArrayList<>();    // 음수
        List<Integer> plus = new ArrayList<>();     // 양수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int book = Integer.parseInt(st.nextToken());
            if (book < 0) minus.add(-book); // 절댓값으로 저장
            else plus.add(book);
        }

        // 내림차순 정렬
        Collections.sort(minus, Collections.reverseOrder());
        Collections.sort(plus, Collections.reverseOrder());

        int max = 0; // 제일 먼 곳
        if (!minus.isEmpty()) max = Math.max(max, minus.get(0));
        if (!plus.isEmpty())  max = Math.max(max, plus.get(0));

        int result = 0;

        // 각각 M개씩
        for (int i = 0; i < minus.size(); i += M) {
            // 왕복
            result += minus.get(i) * 2;
        }
        for (int i = 0; i < plus.size(); i += M) {
            result += plus.get(i) * 2;
        }

        // 가장 멀리 있는 거는 편도 처리
        result -= max;

        System.out.println(result);
    }
}