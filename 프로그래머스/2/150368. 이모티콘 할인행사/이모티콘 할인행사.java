import java.util.*;

class Solution {
    static int[] discountRates = {10, 20, 30, 40};
    static int[] emoticons;
    static int[][] users;
    static int[] answer = {0, 0};
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        int[] emoticonsDiscounts = new int[emoticons.length];
        
        subset(emoticonsDiscounts, 0);
        
        return answer;
    }
    
    public void subset(int[] emoticonDiscounts, int index) {
        if(index == emoticons.length) {
            buyEmoticons(emoticonDiscounts);
            return;
        }
        
        for(int i=0; i<4; i++) {
            emoticonDiscounts[index] = discountRates[i];
            subset(emoticonDiscounts, index + 1);
        }
    }
    
    public void buyEmoticons(int[] emoticonDiscounts) {
        int emoticonPlusCnt = 0;    // 이모티콘 플러스 명수
        int emoticonAmount = 0;     // 이모티콘 매출액
        
        // 사용자별 이모티콘 플러스 할지 or 이모티콘을 살 지 정하기
        for(int[] user : users) {
            int rate = user[0];
            int money = user[1];
            
            // 전체 코스트
            int totalCost = 0;
            
            for(int i=0; i<emoticons.length; i++) {
                int cost = emoticons[i];
                int discountRate = emoticonDiscounts[i];
                
                // 이모티콘의 할인율이 기준 이상이면, 구매
                if(discountRate >= rate) {
                    totalCost += cost - cost*discountRate/100;
                }
            }
            
            // 비용이 감당안되면 이모티콘 플러스!
            if(totalCost >= money) {
                emoticonPlusCnt++;
            } else { // 감당되면 매출액
                emoticonAmount += totalCost;
            }
        }
        
        int[] tmpAnswer = {emoticonPlusCnt, emoticonAmount};
        
        if(tmpAnswer[0] > answer[0]) {
            answer = tmpAnswer;
        } else if(tmpAnswer[0] == answer[0]) {
            if(tmpAnswer[1] > answer[1]) {
                answer = tmpAnswer;
            }
        }
    }
}