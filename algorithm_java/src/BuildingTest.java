import java.util.Scanner;

public class BuildingTest {
  public static int check(String[][] ground, int x, int y) {
    int n = ground.length;
    int[] dx = {0,0,1,1,1,-1,-1,-1};
    int[] dy = {1,-1,1,-1,0,0,-1,1};
    boolean hasG = false;
    int result = 0;

    for(int i=0; i < dx.length; i++) {
      int cx = x + dx[i];
      int cy = y + dy[i];
      // 범위를 벗어나면 넘어가기
      if(cx<0 || cx>=n || cy<0 || cy>=n) {
        continue;
      }
      // 만약 g가 있다면 flag = true
      if(ground[cx][cy].equals("G")) {
        hasG = true;
        break;
      }
    }
    // g가 있다면 2 리턴
    if(hasG) return 2;
    // g가 없다면 가로 세로 세기
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(i==x || j == y) {
          if(ground[i][j].equals("B")) result++;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for(int test_case = 1; test_case <= T; test_case++) {
      int n = sc.nextInt();
      int answer = 0;

      String[][] ground = new String[n][n];
      
      for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {

            String tmp = sc.next();
            ground[i][j] = tmp;

        }
      }

      for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
          if(ground[i][j].equals("B")) {
            int tmpAnswer = check(ground, i, j);
            answer = (answer < tmpAnswer) ? tmpAnswer : answer;
          }
        }
      }
      
      System.out.println("#"+test_case+" " + answer);
    }
  }
}
