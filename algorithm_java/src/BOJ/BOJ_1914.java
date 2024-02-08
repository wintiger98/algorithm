package KIY;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1914 {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static long answer;
  static int N;
  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(br.readLine());


    if(N<=20) {
      hanoi(N, 1, 2, 3);
    } else {
      answer = (long)1 << N - 1;
    }
    System.out.println(answer);
    bw.close();
    br.close();
  }

  private static void hanoi(int n, int from, int tmp, int to) throws IOException {
    if(n == 1) {
      if(N<=20) bw.write(from + " " + to + "\n");
      answer++;
      return;
    }
    hanoi(n-1, from, to, tmp);
    hanoi(1, from, 0, to);
    hanoi(n-1, tmp,  from, to);
  }
}
