import java.util.*;

class Solution {
    static class Cache {
        List<String> dbCache;
        int cacheSize;
        
        public Cache(int cacheSize) {
            this.dbCache = new ArrayList<>();
            this.cacheSize = cacheSize;
        }
        
        public void update(String content) {
            if(isIn(content)) {     // 이미 있다면, 기존꺼 제거
                dbCache.remove(content);
            } else {                // 없다면,
                if(dbCache.size() != 0 && dbCache.size() == cacheSize)  // 캐시사이즈보다 클 때,
                    dbCache.remove(0);  // 맨 앞 삭제
            }
            dbCache.add(content);
        }
        
        public boolean isIn(String content) {
            return dbCache.contains(content);
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if(cacheSize == 0) return cities.length * 5;
        
        Cache cache = new Cache(cacheSize);
        
        for(int i=0; i<cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            if(cache.isIn(city)) {  // 캐시 히트
                answer++;
            } else {                // 캐시 미스
                answer += 5;
            }
            
            cache.update(city);
        }
        
        return answer;
    }
}