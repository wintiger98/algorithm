import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int start = 1;
    static int end = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        backtracking("");
    }

    public static void backtracking(String string) {
        if(string.length() == N) {
            System.out.println(string);
            System.exit(0);
        }

        for (int i = start; i <= end; i++) {
            if(canMakeSequence(string + i)) backtracking(string + i);
        }
    }

    public static boolean canMakeSequence(String string) {
        for (int i = 1; i <= string.length() / 2; i++) {
            String front = string.substring(string.length() - i * 2, string.length() - i);
            String back = string.substring(string.length() - i);
            if(front.equals(back)) return false;
        }
        return true;
    }
}