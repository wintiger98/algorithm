import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B1991_트리순회_김인엽 {
  static int N;
  static int[][] arr;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());
    arr = new int[N][N];

    for(int i=1; i<N+1; i++) {
      char[] tmp = br.readLine().toCharArray();
      int a = tmp[0] - 'A';
      int b = tmp[2] == '.' ? 0 : tmp[2] - 'A';
      int c = tmp[4] == '.' ? 0 : tmp[4] - 'A';
    }

    System.out.println((int)'a');
  }

  public static void preorder(int v) throws IOException {

  }

  public static void inorder() throws IOException {

  }

  public static void postorder() throws IOException {

  }
}
