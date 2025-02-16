import java.util.*;

class Solution {
    static int MAX_VALUE;
    public String solution(String play_time, String adv_time, String[] logs) {
        MAX_VALUE = timeToIndex(play_time) + 1;
        long[] timeTable = new long[MAX_VALUE]; // 최대 그거
        // 로그 루프
        for(String log : logs) {
            String[] logArr = log.split("-"); // - 기준으로 split
            String startTime = logArr[0]; // 시작점
            String endTime = logArr[1];
            
            int start = timeToIndex(startTime);
            int end = timeToIndex(endTime);
            timeTable[start]++; // 시작한 곳은 +
            timeTable[end]--; // 끝난 곳은 -
        }
        
        // 누적 시청자수 구하기
        for(int i=1; i<MAX_VALUE; i++) {
            timeTable[i] += timeTable[i-1];
        }
        
        // 시청 시간의 누적합 계산
        for (int i=1; i<MAX_VALUE; i++) {
            timeTable[i] += timeTable[i-1];
        }
        
        // 광고 길이 인덱스 변환
        int advTimeIndex = timeToIndex(adv_time);

        // 초기 구간의 합 계산
        long maxSum = timeTable[advTimeIndex - 1];
        int maxStart = 0;
        
        // 슬라이딩 윈도우
        for (int start = 1; start + advTimeIndex - 1 < MAX_VALUE; start++) {
            long sum = timeTable[start + advTimeIndex - 1] - timeTable[start - 1];
            if (sum > maxSum) {
                maxSum = sum;
                maxStart = start;
            }
        }
        
        return indexToTime(maxStart);
    }
    
    
    public static int timeToIndex(String time) {
        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minute = Integer.parseInt(timeArr[1]);
        int second = Integer.parseInt(timeArr[2]);
        
        return hour * 3600 + minute * 60 + second;
    }
    
    public static String indexToTime(int index) {
        int hour = index / 3600;
        int minute = (index % 3600) / 60;
        int second = index % 60;
        return indexToTime2(hour) + ":" + indexToTime2(minute) + ":" + indexToTime2(second);
    }
    
    public static String indexToTime2(int index) {
        return index / 10 + "" + index % 10;
    }
}