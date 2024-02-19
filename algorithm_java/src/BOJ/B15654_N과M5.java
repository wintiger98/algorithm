import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B15654_N과M5 {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N, M;
  static int[] inputs, numbers;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    inputs = new int[N];
    numbers = new int[M];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) inputs[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(inputs);

    permutation(0, 0);

    bw.close();
  }

  public static void permutation(int depth, int flag) throws IOException {
    if(depth == M) {
      for(int i=0; i<M; i++) bw.write(numbers[i] + " ");
      bw.write("\n");
      return;
    }

    for(int i=0; i<N; i++) {
      if((flag & (1 << i)) != 0) continue;
      numbers[depth] = inputs[i];
      permutation(depth+1, flag | 1 << i);
    }
  }
}
