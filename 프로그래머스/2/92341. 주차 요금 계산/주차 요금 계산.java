import java.util.*;

class Solution {
    static int[] fees;
    public int[] solution(int[] fees, String[] records) {
        this.fees = fees;
        Map<String, String> carNumToTime = new HashMap<>();
        Map<String, Integer> feeMap = new HashMap<>();
        
        for(String record : records) {
            String[] recordArr = record.split(" ");
            String time = recordArr[0];
            String carNum = recordArr[1];
            boolean isIn = recordArr[2].equals("IN") ? true : false;
            
            if(carNumToTime.containsKey(carNum)) {                
                int minuteIn = timeToMinute(carNumToTime.get(carNum));
                int minuteOut = timeToMinute(time);
                int originTime = feeMap.getOrDefault(carNum, 0);
                
                feeMap.put(carNum, originTime + minuteOut - minuteIn);
                carNumToTime.remove(carNum);
            } else {
                carNumToTime.put(carNum, time);
            }
        }
        
        for(String key: carNumToTime.keySet()) {
            int minuteIn = timeToMinute(carNumToTime.get(key));
            int minuteOut = timeToMinute("23:59");
            int originTime = feeMap.getOrDefault(key, 0);

            feeMap.put(key, originTime + minuteOut - minuteIn);
        }
        
        int[] answer = new int[feeMap.keySet().size()];
        List<String> keyList = new ArrayList<>(feeMap.keySet());
        Collections.sort(keyList);
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = calcFee(feeMap.get(keyList.get(i)));
        }
        return answer;
    }
    
    private int timeToMinute(String time) {
        String[] timeArr = time.split(":");
        return Integer.parseInt(timeArr[0])*60 + Integer.parseInt(timeArr[1]);
    }
    
    private int calcFee(int time) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int stdTime = fees[2];
        int stdFee = fees[3];
        
        if(time <= basicTime) return basicFee;
        
        return (int)Math.ceil((double)(time - basicTime) / stdTime) * stdFee + basicFee;
    }
}