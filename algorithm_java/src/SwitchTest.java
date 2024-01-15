import java.util.Scanner;

public class SwitchTest {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    boolean[] switches = new boolean[n+1];
    for(int i=0; i<m; i++) {
      int click = sc.nextInt();
      for(int j=1; j<switches.length; j++) {
        if(j%click == 0){
          switches[j] = !switches[j];
        }
      }
    }
    for (int i = 1; i < switches.length; i++) {
      System.out.print((switches[i]?1:0)+" ");
    }
  }
}
