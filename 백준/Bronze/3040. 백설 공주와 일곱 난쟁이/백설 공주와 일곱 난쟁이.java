import java.io.*;
import java.util.*;

public class Main {
    static int[] realHobits;
    static int[] hobits;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        hobits = new int[9];
        for (int i = 0; i < 9; i++) {
            hobits[i] = Integer.parseInt(br.readLine());
        }

        // 9C7
        realHobits = new int[7];
        combination(0, 0);
    }

    public static void combination(int start, int depth) {
        if(depth == 7) {
            int sum = Arrays.stream(realHobits).sum();
            if(sum == 100) {
                for(int hobit : realHobits) {
                    System.out.println(hobit);
                }
                System.exit(0);
            }
            return;
        }
        for (int i = start; i < 9; i++) {
            realHobits[depth] = hobits[i];
            combination(i+1, depth+1);
        }
    }
}