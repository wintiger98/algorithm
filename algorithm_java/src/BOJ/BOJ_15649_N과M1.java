import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15649_N과M1 {
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

    for(int i=1; i<=N; i++) inputs[i-1] = i;

    perm(0);

    bw.close();
  }

  private static void perm(int cnt) throws IOException {
    if(cnt == M) {
      for(int i=0; i<M; i++) {
        bw.write(numbers[i] + " ");
      }
      bw.write("\n");
      return;
    }

    for(int i=0; i<N; i++) {
      if(selected[i]) continue;
      numbers[cnt] = inputs[i];
      selected[i] = true;
      perm(cnt+1);
      selected[i] = false;
    }
  }
}
