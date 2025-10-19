import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int[] point = new int[4];
        int index = 1;
        
        for(int i=0; i<dartResult.length(); i++) {
            char ch = dartResult.charAt(i);
            
            if(ch == '0' && point[index - 1] == 1) {
                point[index - 1] *= 10;
                continue;
            } else if(ch >= '0' && ch <= '9') {
                point[index++] = ch - '0';
                continue;
            }
            
            if(ch == 'D') {
                point[index - 1] = (int) Math.pow(point[index - 1], 2);
            } else if(ch == 'T') {
                point[index - 1] = (int) Math.pow(point[index - 1], 3);
            } else if(ch == '*') {
                point[index - 1] *= 2;
                point[index - 2] *= 2;
            } else if(ch == '#') {
                point[index - 1] *= -1;
            }
        }
        int sum = 0;
        for(int p : point) sum += p;
        
        return sum;
    }
}