import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        // 1번 수포자 : 1,2,3,4,5 반복
        // 2번 수포자 : 2,1,2,3,2,4,2,5 반복
        // 3번 수포자 : 3,3,1,1,2,2,4,4,5,5 반복
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        
        int[] scores = {0,0,0};
        
        for(int i=0; i<answers.length; i++) {
            int answer = answers[i];
            int oneAnswer = one[i%one.length];
            int twoAnswer = two[i%two.length];
            int threeAnswer = three[i%three.length];
            
            if(answer == oneAnswer) scores[0]++;
            if(answer == twoAnswer) scores[1]++;
            if(answer == threeAnswer) scores[2]++;
        }
        
        int maxScore = Arrays.stream(scores).max().orElse(0);
        
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(maxScore == scores[i]) answer.add(i+1);
        }
        
        int[] arrayAnswer = new int[answer.size()];
        for(int i=0; i<answer.size(); i++) {
            arrayAnswer[i] = answer.get(i);
        }
        
        return arrayAnswer;
    }
}