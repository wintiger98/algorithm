import java.io.*;
import java.util.*;

public class Main {
    /**
     * 민식이는 회사 매니저
     * 모든 직원은 한 명의 직속 상사 존재
     * 자기자신은 자신의 직/간접 상사가 아님, 모든 직원은 민식이의 직/간접 부하임
     * 민식이는 자기 부하한테 한 번에 한 사람씩 전화 검
     * 각 부하는 자기 부하한테 한 번에 한 사람씩 전화 검
     * 모든 직원이 뉴스를 들을 때까지 지속
     * 전화는 정확히 "1분" 걸림
     * 모든 직원이 소식 돋는데 걸리는 시간의 최솟값은?
     * 오민식의 사원 번호는 0이고, 다른 사원은 1부터 시작
    **/
    static int N; // 직원 수
    static int[] master; // 상사 번호
    static Worker[] workers; // 직원 배열
    static int answer; // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 직원 수 입력
        N = Integer.parseInt(br.readLine());
        // 직원 배열 초기화
        workers = new Worker[N];
        // 사원 번호 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 직원 만들기
            Worker worker = new Worker(i);
            // 직원 배열에 직원 넣기
            workers[i] = worker;
            // 0번 직원은 상사가 없어서 -1임을 주의
            if(num == -1) continue;
            // 해당 직원의 상사 배정
            workers[i].master = workers[num];
            // 상사의 부하직원에 추가
            workers[num].slaves.add(worker);
        }

        // 부하직원 수 설정
        for(int i=N-1; i>=0; i--) {
            workers[i].setHeight();
        }

        System.out.println(workers[0].height);
    }

    static class Worker implements Comparable<Worker> {
        int number; // 직원 번호
        Worker master; // 상사
        List<Worker> slaves; // 부하직원
        int totalSlavesCnt; // 부하직원 총 명수
        int height; // 해당 서브 트리의 높이

        public String toString() {
            return number + "번 사원 : " + height + "높이 갖고있음";
        }

        // 초기화
        public Worker(int number) {
            this.number = number;
            master = null;
            slaves = new ArrayList<>();
            this.totalSlavesCnt = 0;
        }

        public void setHeight() {
            if(slaves.isEmpty()) {
                this.height = 0;
                return;
            }

            Collections.sort(slaves);

            for(int i=0; i<slaves.size(); i++) {
                Worker slave = slaves.get(i);
                height = Math.max(slave.height + i + 1, height);
            }
        }


        @Override
        public int compareTo(Worker o) {
            return Integer.compare(o.height, this.height);
        }
    }
}