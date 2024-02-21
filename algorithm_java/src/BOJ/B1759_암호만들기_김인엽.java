import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// 	11860	80
public class B1759_암호만들기_김인엽 {
  static int L, C;
  static char[] arr;
  static boolean[] isSelected;

  final static int MIN_JAUM = 2;
  final static int MIN_MOUM = 1;

  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    StringTokenizer st = new StringTokenizer(br.readLine());
    L = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    isSelected = new boolean[C];
    arr = new char[C];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<C; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    Arrays.sort(arr); // 미리 정렬해놓고, dfs하기

    dfs(0, 0, 0, 0);

    bw.close();
  }

  public static void dfs(int depth, int cnt, int jaumCnt, int moumCnt) throws IOException {
    // 길이 다 채웠으면, 조건 확인 후 정답에 넣기
    if(cnt == L) {
      if(jaumCnt >= MIN_JAUM && moumCnt >= MIN_MOUM) {
        addAnswer();
      }
      return;
    }
    // 길이도 못채운 주제에 끝까지 갔으면, 끝
    if(depth == C) {
      return;
    }

    // 해당 인덱스 포함시킬 때
    isSelected[depth] = true;
    if(isMoum(arr[depth])) { // 모음이라면 모음만 +1

      dfs(depth+1, cnt+1, jaumCnt, moumCnt+1);
    } else { // 자음이라면 자음만 +1
      dfs(depth+1, cnt+1, jaumCnt+1, moumCnt);
    }

    // 해당 인덱스 포함시키지 않을 때
    isSelected[depth] = false;
    dfs(depth+1, cnt, jaumCnt, moumCnt);
  }

  // 정답에 추가
  public static void addAnswer() throws IOException {
    for(int i=0; i<C; i++) {
      if(isSelected[i]) bw.write(arr[i]);
    }
    bw.write("\n");
  }
  // 모음여부
  public static boolean isMoum(char c) {
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
    return false;
  }
}