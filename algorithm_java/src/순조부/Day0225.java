package 순조부;

import java.util.Arrays;

public class Day0225 {
  static int N, R, input[], numbers[];
  static boolean[] selected;

  public static void main(String[] args) {
    N = 5;
    R = 3;
    input = new int[] {1,2,3,4,5};
    numbers = new int[R];
    selected = new boolean[N];

    // 순열
    System.out.println("5P3 순열 결과!!!");
    permutation(0);

    numbers = new int[R];
    selected = new boolean[N];
    // 조합
    System.out.println("5C3 조합 결과!!!");
    combination(0, 0);

    // 부분집합
    System.out.println("부분 집합 결과!!!");
    subset(0);

    // np
    System.out.println("NP 결과!!!");
    Arrays.sort(input);

    do {
      System.out.println(Arrays.toString(input));
    } while(np(input));

    // 유니온 파인드
    int[] parents = new int[N];
    make(parents); // make
    System.out.println("(유니온 전) 0과 1 유니온 가능? : " + (parents[0] != parents[1]));
    union(parents,0, 1);
    System.out.println("(유니온 후) 0과 1 유니온 가능? : " + (parents[0] != parents[1]));
  }

  public static void permutation(int cnt) {
    if(cnt == R) {
      System.out.println(Arrays.toString(numbers));
      return;
    }
    for(int i=0; i<N; i++) {
      if(selected[i]) continue;
      numbers[cnt] = input[i];
      selected[i] = true;
      permutation(cnt+1);
      selected[i] = false;
    }
  }

  public static void combination(int cnt, int start) {
    if(cnt == R) {
      System.out.println(Arrays.toString(numbers));
      return;
    }
    for(int i=start; i<N; i++) {
      if(selected[i]) continue;
      numbers[cnt] = input[i];
      selected[i] = true;
      combination(cnt + 1, i + 1);
      selected[i] = false;
    }
  }

  public static void subset(int depth) {
    if(depth == N) {
      for(int i=0; i<N; i++) {
        if(!selected[i]) continue;
        System.out.print(input[i] + " ");
      }
      System.out.println();
      return;
    }
    selected[depth] = true;
    subset(depth + 1);

    selected[depth] = false;
    subset(depth+1);
  }

  public static boolean np(int[] p) {
    final int N = p.length;
    int i = N - 1;
    while(i > 0 && p[i-1] >= p[i]) i--;
    if(i == 0) return false;

    int j = N - 1;
    while(p[i-1] >= p[j]) j--;

    swap(p, i-1, j);

    int k = N - 1;
    while(i < k) swap(p, i++, k--);
    return true;
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void make(int[] parents) {
    for(int i=0; i< parents.length; i++) {
      parents[i] = i;
    }
  }

  public static int find(int[] parents, int x) {
    if(x == parents[x]) return x;
    else return parents[x] = find(parents, parents[x]);
  }

  public static boolean union(int[] parents, int a, int b) {
    int aRoot = find(parents, a);
    int bRoot = find(parents, b);
    if(aRoot == bRoot) return false;

    parents[bRoot] = aRoot;
    return true;
  }
}
