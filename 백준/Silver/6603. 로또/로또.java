import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            if(k == 0) break;

            N = k;
            arr = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0);
            bw.write("\n");
            bw.flush();
        }
        bw.close();
    }

    public static void combination(int start, int count) throws IOException {
        if(count == 6) {
            for(int i=0; i<N; i++) {
                if(!visited[i]) continue;
                bw.write(arr[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for(int i=start; i<N; i++) {
            visited[i] = true;
            combination(i+1, count+1);
            visited[i] = false;
        }
    }
}