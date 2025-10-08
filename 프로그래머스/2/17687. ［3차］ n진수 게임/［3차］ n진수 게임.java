import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuffer sb = new StringBuffer();
        
        int cnt = 0;
        int turn = 1; // 차례
        int num = 0;
        while(cnt < t){ // 튜브가 말해야 하는 숫자 t개 구하기 
            String result = getNumToText(num++, n);
            for(int i = 0; i < result.length(); i++){
                if(turn == p){
                    sb.append(result.charAt(i));
                    cnt++;
                    if(cnt == t) break;
                }
                turn++;
                if(turn == m + 1) turn = 1;
            }
        }
        return sb.toString();
    }
    
    private static String getNumToText(int num, int jinsu) {
        StringBuilder sb = new StringBuilder();
        while(num >= jinsu) {
            int cur = num % jinsu;
            if(cur == 10) sb.append('A');
            else if(cur == 11) sb.append('B');
            else if(cur == 12) sb.append('C');
            else if(cur == 13) sb.append('D');
            else if(cur == 14) sb.append('E');
            else if(cur == 15) sb.append('F');
            else sb.append(cur);
            num /= jinsu;
        }
        if(num == 10) sb.append('A');
        else if(num == 11) sb.append('B');
        else if(num == 12) sb.append('C');
        else if(num == 13) sb.append('D');
        else if(num == 14) sb.append('E');
        else if(num == 15) sb.append('F');
        else sb.append(num);
        
        return sb.reverse().toString();
    }
}