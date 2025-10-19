import java.util.*;

class Solution {
    final static int ENTER = 1;
    final static int LEAVE = 2;
    final static int CHANGE = 3;
    
    public String[] solution(String[] record) {
        List<Integer> acts = new ArrayList<>();
        List<String> userIds = new ArrayList<>();
        Map<String, String> userId2name = new HashMap<>();
        
        for(int i=0; i<record.length; i++) {
            String r = record[i];
            String[] strs = r.split(" ");
            String act = strs[0];
            String userId = strs[1];
            
            int curAct = act.equals("Enter") ? ENTER : (act.equals("Leave") ? LEAVE : CHANGE);
            
            if(curAct == LEAVE) {
                acts.add(curAct);
                userIds.add(userId);
            }
            else if(curAct == ENTER) {
                String nickname = strs[2];
                acts.add(curAct);
                userIds.add(userId);
                userId2name.put(userId, nickname);
            } else {
                String nickname = strs[2];
                userId2name.put(userId, nickname);
            }
            
        }
        
        String[] answer = new String[acts.size()];
        for(int i=0; i<answer.length; i++) {
            String nickname = userId2name.get(userIds.get(i));
            StringBuilder ment = new StringBuilder();
            ment.append(nickname + "님이 ");
            if(acts.get(i) == ENTER) {
                ment.append("들어왔습니다.");
            } else {
                ment.append("나갔습니다.");
            }
            answer[i] = ment.toString();
        }
        return answer;
    }
}