package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S6248 {
  static int[][] arr;
  static int n, m, s, t;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static List<Integer> s2T = new ArrayList<>();
  static List<Integer> t2s = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n+1][n+1];

    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int xi = Integer.parseInt(st.nextToken());
      int yi = Integer.parseInt(st.nextToken());
      arr[xi][yi] = 1;
    }

    st = new StringTokenizer(br.readLine());
    s = Integer.parseInt(st.nextToken());
    t = Integer.parseInt(st.nextToken());

    dfsS2T(s);
    dfsT2S(t);

  }

  private static void dfsS2T(int v) {
    for(int i=0; i<n+1; i++) {
      if(arr[v][i] == 1) dfsS2T(i);
    }
  }

  private static void dfsT2S() {

  }


}
