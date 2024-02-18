import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 	132228	1708
public class B1202_보석도둑_김인엽 {

  static class Jewerly {

    int m;
    int v;

    public Jewerly(int m, int v) {
      this.m = m;
      this.v = v;
    }

    @Override
    public String toString() {
      return "Jewerly [m=" + m + ", v=" + v + "]";
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    PriorityQueue<Jewerly> pqJewerly = new PriorityQueue<>((j1, j2) -> {
      if (j1.m == j2.m) {
        return j2.v - j1.v; // 가치 내림차순
      }
      return j1.m - j2.m;
    });
    PriorityQueue<Integer> pqBag = new PriorityQueue<>(); // 오름차순

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      pqJewerly.add(new Jewerly(m, v));
    }

    for (int i = 0; i < K; i++) {
      pqBag.add(Integer.parseInt(br.readLine()));
    }

    long answer = 0; // 보석 가격 합 최댓값(int 범위로 해결 안됨)
    PriorityQueue<Jewerly> tmpPqJewerly = new PriorityQueue<>((j1, j2) -> { // 현 가방 무게 이하의 보석들 모음(가치 내림차순)
      if (j1.v == j2.v) {
        return j1.m - j2.m;
      }
      return j2.v - j1.v;
    }); // 이곳의 peek값이 항상 내겐 최선의 답.

    // 가방 다 쓸 때까지
    while (!pqBag.isEmpty()) {
      int tmpBag = pqBag.poll();
      int n = pqJewerly.size();
      for (int i = 0; i < n; i++) {
        Jewerly tmpJewerly = pqJewerly.poll();
        if (tmpPqJewerly.isEmpty() && tmpJewerly.m == tmpBag) { // 비어있는데 무게가 딱 맞으면, 무조건 최고의 선택
          answer += tmpJewerly.v;
          break;
        }
        if (tmpJewerly.m <= tmpBag) { // 가벼운거 찾았으면 넣고,
          tmpPqJewerly.add(tmpJewerly);
        } else { // 안 가벼우면 다시 원래꺼에 넣어주고
          pqJewerly.add(tmpJewerly);
          break;
        }
      }
      // 정답을 빼올게 비어있으면 패스
      if (tmpPqJewerly.isEmpty()) {
        continue;
      }
      // 해당 무게 안에 가장 비싼거 추가.
      answer += tmpPqJewerly.poll().v;
    }

    System.out.println(answer);
  }
}