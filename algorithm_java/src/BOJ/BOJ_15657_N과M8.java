import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15657_N과M8 {
  final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int N, M;
  static int[] inputs, numbers;
  static boolean[] selected;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    inputs = new int[N];
    numbers = new int[M];
    selected = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) inputs[i] = Integer.parseInt(st.nextToken());
    Arrays.sort(inputs);
    perm(0, 0);

    bw.close();
  }

  private static void perm(int cnt, int start) throws IOException {
    if(cnt == M) {
      for(int i=0; i<M; i++) {
        bw.write(numbers[i] + " ");
      }
      bw.write("\n");
      return;
    }

    for(int i=start; i<N; i++) {
      numbers[cnt] = inputs[i];
      perm(cnt+1, i);
    }
  }
}
