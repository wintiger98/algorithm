import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static int[] cost;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cost = new int[N+1];
        parent = new int[N+1];
        init();

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            union(v, w);
        }

        Set<Integer> minFriends = new HashSet<>();
        for(int i=1; i<N+1; i++) {
            minFriends.add(find(i));
        }
        int answer = 0;
        for(int friend : minFriends) {
            answer += cost[friend];
        }
        if(answer > K) System.out.println("Oh no");
        else System.out.println(answer);
    }

    static void init() {
        for(int i=1; i<N+1; i++) {
            parent[i] = i;
        }
    }

    static int find(int v) {
        if(parent[v] == v) return v;
        else return parent[v] = find(parent[v]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        if(cost[aRoot] < cost[bRoot]) parent[bRoot] = aRoot;
        else parent[aRoot] = bRoot;

        return true;
    }
}