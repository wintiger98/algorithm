class Solution {
    static int n, m, r, c, k;
    static String answer = "";
    static boolean found = false;

    static int[] dx = {1, 0, 0, -1};     // d, l, r, u
    static int[] dy = {0, -1, 1, 0};
    static String[] dir = {"d", "l", "r", "u"};

    public static void dfs(int x, int y, String route, int cnt) {
        if (found) return;

        int dist = Math.abs(x - r) + Math.abs(y - c);
        int remaining = k - cnt;

        // 가지치기: 거리보다 남은 이동횟수가 부족하거나, 짝/홀수 불일치
        if (dist > remaining || (remaining - dist) % 2 != 0) return;

        if (cnt == k) {
            if (x == r && y == c) {
                answer = route;
                found = true;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx <= 0 || nx > n || ny <= 0 || ny > m) continue;
            dfs(nx, ny, route + dir[i], cnt + 1);
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n; this.m = m; this.r = r; this.c = c; this.k = k;
        answer = "";
        found = false;

        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist > k || (k - dist) % 2 != 0) return "impossible";

        dfs(x, y, "", 0);
        return found ? answer : "impossible";
    }
}
