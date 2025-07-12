import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 합이 T
        int n = Integer.parseInt(br.readLine());
        int[] arrN = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arrN[i] = num;
        }

        int m = Integer.parseInt(br.readLine());
        int[] arrM = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=m; i++) {
            int num = Integer.parseInt(st.nextToken());
            arrM[i] = num;
        }

        // 1. 누적합으로 계산해두자(나중에 구간합 구하기 편하게)
        int[] nSum = new int[n+1];
        makePrefixSum(nSum, arrN);
        
        int[] mSum = new int[m+1];
        makePrefixSum(mSum, arrM);
        
        // 2. arrN , arrM로 만들 수 있는 모든 경우의 수를 찾아서 맵에 저장
        // {합: 개수}
        Map<Long, Long> nMap = new HashMap<>();
        fillMap(nMap, nSum);
        Map<Long, Long> mMap = new HashMap<>();
        fillMap(mMap, mSum);

        // 3. n을 기준으로 m중에서 T-n이 있는지 확인
        long answer = 0;
        Set<Long> nKeys = nMap.keySet();
        for(Long key : nKeys) {
            if(mMap.containsKey(T-key)) {
                answer += nMap.get(key) * mMap.get(T-key);
            }
        }
        System.out.println(answer);
    }

    public static void fillMap(Map<Long, Long> map, int[] prefixSum) {
        for(int i=0; i<prefixSum.length-1; i++) {
            for(int j=i; j<prefixSum.length-1; j++) {
                long tmpSum = prefixSum[j+1] - prefixSum[i];
                long orginCnt = map.getOrDefault(tmpSum, 0L);
                map.put(tmpSum, orginCnt+1);
            }
        }
    }

    public static void makePrefixSum(int[] sum, int[] origin) {
        for(int i=1; i<origin.length; i++) {
            sum[i] = sum[i-1] + origin[i];
        }
    }
}