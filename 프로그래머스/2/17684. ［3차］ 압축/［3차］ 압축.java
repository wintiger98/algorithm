import java.util.*;

class Solution {
    public List<Integer> solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        
        Map<String, Integer> dictionary = new HashMap<>();
        init(dictionary);
        int curIdx = 27;
        
        int i = 0;
        while(i < msg.length()) {
            String w = "";
            int lastFound = -1;
            
            // 사전에 있는 가장 긴 문자열 w 찾기
            for(int j = i; j < msg.length(); j++) {
                String temp = msg.substring(i, j + 1);
                
                if(dictionary.containsKey(temp)) {
                    w = temp;
                    lastFound = dictionary.get(temp);
                } else {
                    // w + c를 사전에 추가
                    dictionary.put(temp, curIdx++);
                    break;
                }
            }
            
            // w에 해당하는 색인 번호 출력
            if(lastFound != -1) {
                answer.add(lastFound);
            }
            
            // 다음 위치로 이동
            i += w.length();
        }
        return answer;
    }
    
    private void init(Map<String, Integer> map) {
        for(int i=65; i<65+26; i++) {
            map.put((char) (i) + "", i-64);
        } 
    }
}