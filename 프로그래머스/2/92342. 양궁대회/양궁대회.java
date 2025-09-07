import java.util.*;

class Solution {
    static int maxScore = 0;    // 점수 차이
    static int[] apeach;        // 어피치
    static int[] answer = new int[11];        // 정답
    static int N;
    
    public int[] solution(int n, int[] info) {
        N = n;
        apeach = info;
        
        // 부분집합
        boolean[] isWin = new boolean[11]; // 10~0 점을 이길지 말지
        subset(isWin, 0);
        if(maxScore == 0) answer = new int[]{-1};
        return answer;
    }
    
    // 부분집합 만들기(해당 친구를 이기기)
    public void subset(boolean[] isWin, int index) {
        if(index == isWin.length) {
            // 어피치를 이길 수 있다면, ...
            calcAnswer(isWin);
            return;
        }
        
        isWin[index] = true;
        subset(isWin, index + 1);
        
        isWin[index] = false;
        subset(isWin, index + 1);
    }
    
    // 어피치를 이길 수 있는지 확인
    public void calcAnswer(boolean[] isWin) {
        int[] tmpAnswer = new int[11];
        int total = 0;
        
        // 10-i 점 추가
        for(int i=0; i<11; i++) {
            if(isWin[i]) {
                total += 10 - i;            // 이길 때
                tmpAnswer[i] += apeach[i] + 1;
            }
            else if(apeach[i] > 0) {
                total -= 10 -i;             // 질 때
            }
        }
        
        int cnt = 0;
        for(int i=0; i<11; i++) {
            cnt += tmpAnswer[i];
        }
        if(cnt > N) return; // 더 많이 써야되면 바로 컷
        else if(cnt < N) { // 더 적게 썼으면 채우기
            int toFill = N - cnt;
            // 뒤에서부터(낮은 거 부터)
            for(int i=10; i>=0; i--) {
                int fill = Math.max(0, apeach[i] - tmpAnswer[i]);
                toFill -= fill;
                tmpAnswer[i] += fill;
                if(toFill < 0) {
                    tmpAnswer[i] -= toFill * (-1);
                    break;
                }
            }
        }
        
        if(maxScore < total) {
            maxScore = total;
            answer = tmpAnswer;
        }
        else if(maxScore == total) {
            boolean isPriority = false;
            for(int i=10; i>=0; i--) {   
                if(tmpAnswer[i] > answer[i]) {
                    isPriority = true;
                    break;
                } else if(tmpAnswer[i] < answer[i]) {
                    isPriority = false;
                    break;
                }
            }
            if(isPriority) answer = tmpAnswer;
        }
    }
    
}