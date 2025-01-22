import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] dpBig = new int[N];
        int[] dpSmall = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(arr[j] < arr[i]) {
                    dpBig[i] = Math.max(dpBig[i], dpBig[j] + 1);
                }
            }
        }

        for(int i=N-1; i>=0; i--) {
            for(int j=N-1; j>i; j--) {
                if(arr[j] < arr[i]) {
                    dpSmall[i] = Math.max(dpSmall[i], dpSmall[j] + 1);
                }
            }
        }

        for(int i=0; i<N; i++) {
            dpBig[i] += dpSmall[i];
        }
        System.out.println(Arrays.stream(dpBig).max().orElse(0)+1);
    }

}