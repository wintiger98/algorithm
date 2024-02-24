import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// 32868	216
public class B15663_N과M9 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N, M;
  static int[] arr;
  static int[] numbers;
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    arr = new int[N];
    numbers = new int[M];
    visited = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(arr);

    permutation(0);

    bw.close();
  }

  public static void permutation(int cnt) throws IOException {
    if(cnt == M) {
      for(int i=0; i<M; i++) {
        bw.write(numbers[i] + " ");
      }
      bw.write("\n");
      return;
    }
    int lastNum = -1;
    for(int i=0; i<N; i++) {
      if(visited[i] || lastNum == arr[i]) continue;
      lastNum = arr[i];
      numbers[cnt] = arr[i];
      visited[i] = true;
      permutation(cnt+1);
      visited[i] = false;
    }
  }
}
