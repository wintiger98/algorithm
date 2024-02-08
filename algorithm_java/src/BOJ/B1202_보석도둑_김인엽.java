import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 실패
public class B1202_보석도둑_김인엽 {

  static class Jewerly implements Comparable<Jewerly> {

    int m;
    int v;

    public Jewerly(int m, int v) {
      this.m = m;
      this.v = v;
    }

    @Override
    public int compareTo(Jewerly o) {
      if(this.m == o.m)
        return o.v - this.v; // 가치 내림차순
      return this.m - o.m;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    Jewerly[] jewInfos = new Jewerly[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      jewInfos[i] = new Jewerly(m, v);
    }

    Arrays.sort(jewInfos); // 보석 정보를 가치 기준 내림차순

    int[] bags = new int[K];
    for (int i = 0; i < K; i++) {
      bags[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(bags); // 가방 무게 기준 오른차순

    long answer = 0; // 보석 가격 합 최댓값(int 범위로 해결 안됨)
    boolean[] selected = new boolean[N]; // 해당 보석이 선택되었는지 기록
    for (int bag : bags) { // 낮은 무게 가방 순으로 탐색 : 가능한 가장 높은 가치의 보석부터
      for (int i = 0; i < N; i++) {
        if (!selected[i]
            && jewInfos[i].m <= bag) { // 선택되지 않은 요소 중 높은 요소를 찾았으면, selected 처리 및 정답에 추가
          selected[i] = true;
          answer += jewInfos[i].v;
          break;
        }
      }
    }
    System.out.println(answer);
  }
}
