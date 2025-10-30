import java.util.*;

class Solution {
    
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        // 1. terms를 Map으로 변환하여 O(1) 조회가 가능하게 합니다.
        // (Key: 약관 종류, Value: 유효 개월 수)
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        // 2. '오늘 날짜'를 총 일수로 변환합니다.
        int todayTotalDays = dateToDays(today);
        
        // 3. privacies를 순회하며 파기 여부를 결정합니다.
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] parts = privacy.split(" ");
            
            String collectionDateStr = parts[0];
            String termType = parts[1];
            
            // 4. 개인정보 수집 일자를 총 일수로 변환
            int collectionTotalDays = dateToDays(collectionDateStr);
            
            // 5. 해당 약관의 유효 기간(월)을 가져와 일수로 변환 (모든 달은 28일)
            int durationInMonths = termMap.get(termType);
            int durationInDays = durationInMonths * 28;
            
            // 6. 만료되는 날짜의 총 일수 계산
            // (수집일 + 유효기간)
            int expiryTotalDays = collectionTotalDays + durationInDays;
            
            // 7. 오늘 날짜가 만료일과 같거나 만료일보다 지났다면 파기 대상
            // (예: 만료일이 5월 19일이면 5월 19일부터 파기)
            if (todayTotalDays >= expiryTotalDays) {
                answer.add(i + 1); // 번호는 1부터 시작하므로 +1
            }
        }
        
        return answer;
    }
    
    /**
     * "YYYY.MM.DD" 형식의 날짜를 총 일수로 변환하는 헬퍼 메소드
     * (모든 달은 28일로 가정)
     */
    private int dateToDays(String dateStr) {
        String[] parts = dateStr.split("\\."); // 여기서도 \\. 사용
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        // 연도를 모두 일수로, 월을 모두 일수로 변환하여 합산
        return (year * 12 * 28) + (month * 28) + day;
    }
}