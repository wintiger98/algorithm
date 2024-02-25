package 그래프;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class AdjArray {

  public static void main(String[] args) throws FileNotFoundException {
    System.setIn(new FileInputStream("graph_input1.txt"));
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E = sc.nextInt();

    int[][] arr = new int[V][V];
    for(int i=0; i<E; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      arr[from][to] = arr[to][from] = 1;
    }

    for (int[] a : arr) {
      System.out.println(Arrays.toString(a));
    }
  }
}
