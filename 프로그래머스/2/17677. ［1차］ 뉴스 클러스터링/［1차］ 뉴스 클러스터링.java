import java.util.*;

class Solution {
    public int solution(String str1, String str2) {      
        Map<String, Integer> A = makeStringSet(str1.toUpperCase());
        Map<String, Integer> B = makeStringSet(str2.toUpperCase());
        
        if(A.keySet().isEmpty() && B.keySet().isEmpty()) return 65536;
        
        double answer = calcJacade(A, B);
        
        return (int) (answer * 65536);
    }
    
    private double calcJacade(Map<String, Integer> A, Map<String, Integer> B) {
        int intersection = 0;   // 교집합
        int union = 0;          // 합집합
        
        Set<String> intersectionKeys = new HashSet<>();
        
        for(String aKey : A.keySet()) {
            for(String bKey : B.keySet()) {
                if(aKey.equals(bKey)) { // 같다면
                    intersection += Math.min(A.get(aKey), B.get(bKey));
                    union += Math.max(A.get(aKey), B.get(bKey));
                    intersectionKeys.add(aKey);
                }
            }
        }
        
        for(String aKey : A.keySet()) {
            if(intersectionKeys.contains(aKey)) continue;
            union += A.get(aKey);
        }
        
        for(String bKey : B.keySet()) {
            if(intersectionKeys.contains(bKey)) continue;
            union += B.get(bKey);
        }
        return (double) intersection / union;
    }
    
    private Map<String, Integer> makeStringSet(String str) {
        Map<String, Integer> str2cnt = new HashMap<>();
        for(int i=0; i<str.length()-1; i++) {
            char one = str.charAt(i);
            char two = str.charAt(i+1);
            
            if(!Character.isAlphabetic(one) || !Character.isAlphabetic(two)) continue;
            
            String key = one + "" + two;
            int cnt = str2cnt.getOrDefault(key, 0);
            
            str2cnt.put(key, cnt + 1);
        }
        return str2cnt;
    }
}