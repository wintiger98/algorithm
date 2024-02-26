package Jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class J1863_종교_김인엽 {
  static int N, M;
  static boolean[] visited;
  static int[] parents;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    parents = new int[N+1];
    make();

    int answer = N;
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if(union(a, b)) answer--;
    }

    System.out.println(answer);
  }

  private static void make() {
    for(int i=1; i<parents.length; i++) {
      parents[i] = i;
    }
  }
  private static int find(int x) {
    if(x == parents[x]) return x;
    return parents[x] = find(parents[x]);
  }

  private static boolean union(int a, int b) {
    int aRoot = find(a);
    int bRoot = find(b);
    if(aRoot == bRoot) return false;

    parents[bRoot] = aRoot;
    return true;
  }
}
