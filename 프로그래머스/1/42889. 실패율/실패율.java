import java.util.*;

class Solution {
    public List<Integer> solution(int N, int[] stages) {
        Arrays.sort(stages); // 오름차순 정렬
        
        int total = stages.length;
        Map<Integer, Double> map = new HashMap<>();
        
        for(int i=1; i<=N; i++) {
            int from = lowerBound(stages, i);
            int to = lowerBound(stages, i+1);
            
            double failRate = 0.0;
            if(total - from == 0) 
                failRate = 0.0;
            else
                failRate = (double) (to - from) / (total - from);
            map.put(i, failRate);
        }
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> {
            if(map.get(o2).compareTo(map.get(o1)) == 0)
                return o1.compareTo(o2);
            else 
                return map.get(o2).compareTo(map.get(o1));
        });
        
        // for(int key : keySet) {
        //     System.out.println(key + " : " + map.get(key));
        // }
        
        return keySet;
    }
    
    private static int lowerBound(int[] input, int target) {
        int s = 0, e = input.length;
        int m;
        
        while(s < e) {
            m = (s + e) / 2;
            
            if(input[m] < target) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return e;
    }
    
    private static int upperBound(int[] input, int target) {
        int s = 0, e = input.length;
        int m;
        
        while(s < e) {
            m = (s + e) / 2;
            
            if(input[m] <= target) {
                s = m + 1;
            } else {
                e = m;
            }
        }
        return e;
    }
}