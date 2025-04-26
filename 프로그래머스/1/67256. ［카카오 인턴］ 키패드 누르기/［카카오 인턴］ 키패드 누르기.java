import java.util.*;

class Solution {
    /**
    * 1 2 3
    * 4 5 6
    * 7 8 9
    * * 0 #
    **/
    public String solution(int[] numbers, String hand) {
        Map<Integer, Pair> num2Pair = new HashMap<>();
        num2Pair.put(1, new Pair(0, 0));
        num2Pair.put(2, new Pair(0, 1));
        num2Pair.put(3, new Pair(0, 2));
        num2Pair.put(4, new Pair(1, 0));
        num2Pair.put(5, new Pair(1, 1));
        num2Pair.put(6, new Pair(1, 2));
        num2Pair.put(7, new Pair(2, 0));
        num2Pair.put(8, new Pair(2, 1));
        num2Pair.put(9, new Pair(2, 2));
        num2Pair.put(0, new Pair(3, 1));
        
        Pair leftHand = new Pair(3, 0);
        Pair rightHand = new Pair(3, 2);
        
        StringBuilder answer = new StringBuilder();
        for(int number : numbers) {
            Pair togo = num2Pair.get(number);
            Pair toUse = null;
            if(number % 3 == 1) {
                toUse = leftHand;
            } else if(number > 0 && number % 3 == 0) {
                toUse = rightHand;
            } else {
                int leftDist = leftHand.calcDist(togo);
                int rightDist = rightHand.calcDist(togo);
                
                if(leftDist == rightDist) {
                    toUse = hand.equals("left") ? leftHand : rightHand;
                } else {
                    toUse = leftDist < rightDist ? leftHand : rightHand;
                }
            }
            toUse.move(togo);
            if(toUse == leftHand) answer.append("L");
            else answer.append("R");
        }
        return answer.toString();
    }
    
    static class Pair {
        int x, y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int calcDist(Pair other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
        
        public void move(Pair pair) {
            this.x = pair.x;
            this.y = pair.y;
        }
    }
}