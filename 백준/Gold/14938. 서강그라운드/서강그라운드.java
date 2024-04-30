import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        int[][] distances = new int[N+1][N+1];
        int INF = 150_000;
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(i == j) distances[i][j] = 0;
                else distances[i][j] = INF;
            }
        }
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            distances[a][b] = c;
            distances[b][a] = c;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
        int answer = 0;
        for(int i=1; i<=N; i++) {
            int tmpAnswer = 0;
            for(int j=1; j<=N; j++) {
                if(distances[i][j]<=M) {
                    tmpAnswer += items[j];
                }
            }
            answer = Math.max(answer, tmpAnswer);
        }
        System.out.println(answer);
    }
}
