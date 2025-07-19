class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for(int i=0; i<schedules.length; i++) {
            int schedule = schedules[i];
            int[] timelog = timelogs[i];
            int[] days = new int[7];
            days[0] = startday - 1;
            for(int j=1; j<7; j++) {
                days[j] = (days[j-1] + 1) % 7;
            }
            
            int deadline = calcTime(schedule) + 10;
            boolean isValid = true;
            for(int j=0; j<7; j++) {
                if(days[j] == 5 || days[j] == 6) continue;
                if(calcTime(timelog[j]) > deadline) {
                    isValid = false;
                    break;
                }
            }
            
            if(isValid) answer++;
        }
        
        return answer;
    }
    
    private int calcTime(int time) {
        return time / 100 * 60 + time % 100;
    }
}