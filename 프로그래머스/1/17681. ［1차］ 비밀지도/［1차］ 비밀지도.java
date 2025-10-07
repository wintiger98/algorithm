import java.util.*;

class Solution {
    final static char BLANK = '0';
    final static char WALL = '1';
    
    final static char DECRY_BLANK = ' ';
    final static char DECRY_WALL = '#';
    
    static int N;
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        N = n;
        
        String[] answer = new String[N];
        
        // 지도1 or 지도2 둘 중 하나라도 벽이면 벽
        // 둘 다 공백이어야 공백
        for(int i=0; i<N; i++) {
            String tmpAnswer = "";
            
            String map1 = bitmasking(arr1[i]);
            String map2 = bitmasking(arr2[i]);
            
            for(int j=0; j<N; j++) {
                char ch1 = map1.charAt(j);
                char ch2 = map2.charAt(j);
                if(ch1 == ch2 && ch1 == BLANK) {
                    tmpAnswer += DECRY_BLANK;
                } else {
                    tmpAnswer += DECRY_WALL;
                }
            }
            
            answer[i] = tmpAnswer;
        }
        return answer;
    }
    
    private static String bitmasking(int origin) {
        String ret = "";
        
        for(int i=N-1; i>=0; i--) {
            if((origin & (1 << i)) != 0) {
                ret += "1";
            } else {
                ret += "0";
            }
        }
        
        return ret;
    }
}