import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S3289_서로소집합_김인엽 {
  static int[] arr, parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int T = Integer.parseInt(br.readLine());

    StringTokenizer st;

    for(int test_case = 1; test_case <= T; test_case++) {
      bw.write("#"+test_case+" ");

      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      arr = new int[n+1];
      parent = new int[n+1];

      for(int i=1; i<n+1; i++) {
        arr[i] = i;
        makeSet(i);
      }
      for(int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());
        int isUnion = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        // 합집합이면,
        if(isUnion == 0) {
          union(a, b);
        } else { // 찾기라면,,
          bw.write((findSet(a)==findSet(b)?1:0)+"");
        }
      }
      bw.write("\n");
      bw.flush();
    }
    bw.close();
  }

  public static void makeSet(int x) {
    parent[x] = -1;
  }

  public static int findSet(int x) {
    if(parent[x] < 0) return x;
    return parent[x] = findSet(parent[x]);
  }

  public static void union(int x, int y) {
    if(findSet(x) == findSet(y)) return;

    parent[findSet(y)] = findSet(x);
  }
}