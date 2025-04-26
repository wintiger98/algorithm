import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int subin = Integer.parseInt(st.nextToken());
        int dongseng = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new ArrayDeque<>();

        int[] times = new int[100_001];
        Arrays.fill(times, Integer.MAX_VALUE);
        q.add(subin);
        times[subin] = 0;
        while(!q.isEmpty()) {
            int loc = q.poll();

            if(loc == dongseng) break;
            
            for(int i=0; i<3; i++) {
                int nextLoc = calcLoc(loc, i);
                if(nextLoc < 0 || nextLoc > 100_000) continue;
                if(times[nextLoc] == Integer.MAX_VALUE) {
                    times[nextLoc] = times[loc] + 1;
                    q.add(nextLoc);
                }
            }
        }

        System.out.println(times[dongseng]);
    }

    public static int calcLoc(int loc, int index) {
        switch(index) {
            case 0:
                return loc - 1;
            case 1:
                return loc + 1;
            case 2:
                return loc * 2;
            default:
                return 0;
        }
    }
}