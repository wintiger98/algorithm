package 프로그래머스;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P_유전법칙_김인엽 {
  final static int RR = -1;
  final static int Rr = -2;
  final static int rr = -4;

  final static Map<Integer, String> num2String = new HashMap<>();
  static int[] parents; // 부모 모아놓기
  static int find(int[] query) {
    int n = query[0];
    int p = query[1];
    parents[n] = p%4==0?4:p%4; // 해당 트리에서 몇번째인지로 변경
    if(n <= 2) {
      System.out.println("parents : " + Arrays.toString(parents));
      return p;
    }
    return find(new int[]{n-1, p%4==0?p/4:p/4+1});
  }
  static void find2() {
    for(int i=3; i<parents.length; i++) {
      switch(parents[i]) {
        case 1:
          parents[i] = 0;
          break;
        case 2:
        case 3:
          break;
        case 4:
          break;
      }
    }
  }
  static public String[] solution(int[][] queries) {
    num2String.put(1, "RR");
    num2String.put(2, "Rr");
    num2String.put(3, "Rr");
    num2String.put(4, "rr");
    String[] answer = new String[queries.length];
    for(int i=0; i<queries.length; i++) {
      // 부모 모아놓기(인덱스 = 세대)
      parents = new int[queries[i][0]+1];
      // 첫 세대면 무조건 Rr
      if(queries[i][0] == 1) {
        answer[i] = "Rr";
        continue;
      } else if(queries[i][0] == 2) {
        answer[i] = num2String.get(queries[i][1]);
      }
      // 두번째 세대값 찾기
      switch(find(queries[i])) {
        case 1:
          answer[i] = "RR";
          break;
        case 2:
        case 3:
          find2();
          answer[i] = num2String.get(parents[i] * (-1));
          break;
        case 4:
          answer[i] = "rr";
          break;
      }
    }
    return answer;
  }
  public static void main(String[] args) {
    int[][][] queries = {
        {{3, 5}},
        {{3,8},{2,2}},
        {{3,1},{2,3},{3,9}},
        {{4,26}}
    };
    for(int[][] query : queries) {
      System.out.println("===================================");
      System.out.println(Arrays.toString(solution(query)));
    }
  }
}
