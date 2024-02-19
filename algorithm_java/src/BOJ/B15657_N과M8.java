import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B15657_N과M8 {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int N, M;
  static int[] inputs, numbers;
  static boolean[] isSelected;
  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    inputs = new int[N];
    numbers = new int[M];
    isSelected = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) inputs[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(inputs);

    permutation(0, 0);

    bw.close();
  }

  public static void permutation(int depth, int start) throws IOException {
    if(depth == M) {
      for(int i=0; i<M; i++) bw.write(numbers[i] + " ");
      bw.write("\n");
      return;
    }

    for(int i=start; i<N; i++) {
      numbers[depth] = inputs[i];
      isSelected[i] = true;
      permutation(depth+1, i);
      isSelected[i] = false;
    }
  }
}
