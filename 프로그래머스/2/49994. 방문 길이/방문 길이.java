import java.util.*;

class Solution {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    
    public int solution(String dirs) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('U', 3);
        map.put('D', 1);
        map.put('R', 0);
        map.put('L', 2);
        
        int answer = 0;
        
        int[] cur = {0, 0};
        
        Set<String> set = new HashSet<>();
        
        for(int i=0; i<dirs.length(); i++) {
            char ch = dirs.charAt(i);
            
            int curIdx = map.get(ch);
            int cx = cur[0] + dx[curIdx];
            int cy = cur[1] + dy[curIdx];
            if(!(cx >= -5 && cx <= 5 && cy >= -5 && cy <= 5)) continue;
            String key = cur[0]+","+cur[1]+","+cx+","+cy;
            String key2 = cx+","+cy+","+cur[0]+","+cur[1];
            if(!set.contains(key) && !set.contains(key2)) {
                set.add(key);
                set.add(key2);
                answer++;
            }
            cur[0] = cx; cur[1] = cy;
        }
        
        
        return answer;
    }
}