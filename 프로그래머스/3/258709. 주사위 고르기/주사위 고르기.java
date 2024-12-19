import java.util.*;

class Solution {
    static int N; // 주사위 개수
    static int[] answer; // 정답
    static int maxWin; // 최대 승수
    static TreeMap<Integer, Integer>[] dices; // 주사위(key 순서대로 하려고 treeMap 사용)
    static Stack<Integer> selectedDice; // 주사위 번호
    static boolean[] selected; // 주사위 번호 선택 여부
    
    public int[] solution(int[][] dice) {
        // 1. dice 1~n 까지 중에서 절반 선택하기
        // 2. a와 b 주사위 굴렸을 때 승패 결과 따져보기
        // 3. 그때 그때 승 수만 따져서 높으면 갈아끼기?
        // 4. 끝
        
        N = dice.length;
        dices = new TreeMap[N+1];
        answer = new int[N/2];
        selectedDice = new Stack<>();
        selected = new boolean[N+1];
        
        initDices(dice); // 초기화
        
        combination(1, 0); // 조합
        
        return answer;
    }
    
    // 값 : 개수로 바꾸기
    static void initDices(int[][] dice) {
        // 초기화
        for(int i=1; i<=N; i++) {
            dices[i] = new TreeMap<>();
        }
        
        // 값 : 개수 맵으로 바꾸기
        for(int i=0; i<N; i++) { // dice 인덱스
            for(int j=0; j<6; j++) { // 주사위값
                int value = dices[i+1].getOrDefault(dice[i][j], 0);
                dices[i+1].put(dice[i][j], value+1);
            }
        }
    }
    
    static void combination(int start, int cnt) {
        if(selectedDice.size() >= 1 && selectedDice.firstElement() > 1) return; // 중복 검사 방지
        if(cnt == N/2) {
            // selectedDice 가지고 놀기
            simulate();
            return;
        }
        
        for(int i=start; i<=N; i++) {
            selectedDice.push(i);
            combination(i+1, cnt+1);
            selectedDice.pop();
        }
    }
    
    static void simulate() {
        // 값 : 개수 있는거 갖고 이제 승패 시뮬 돌려보기(값 : 개수)
        // 얘는 selectedDice꺼.
        TreeMap<Integer, Integer> selectedMap = new TreeMap<>();
        TreeMap<Integer, Integer> unselectedMap = new TreeMap<>();
        
        Stack<Integer> unselectedDice = new Stack<>();
        for(int i=1; i<=N; i++) {
            if(selectedDice.contains(i)) continue; // 선택했던 친구면 패스
            unselectedDice.push(i);
        }
        
        // selectedMap 채우기
        fillMap(selectedMap, selectedDice, 0, 0, 1);
        
        // unselectedMap 채우기
        fillMap(unselectedMap, unselectedDice, 0, 0, 1);
        
        // selectedMap과 unselectedMap 비교하기
        Set<Integer> selectedKeys = selectedMap.keySet();
        Set<Integer> unselectedKeys = unselectedMap.keySet();
        
        int totalWin = 0;
        int totalDefeat = 0;
        for(int selectedKey : selectedKeys) {
            int win = 0;
            int defeat = 0;
            for(int unselectedKey : unselectedKeys) {
                if(selectedKey > unselectedKey) {
                    win += selectedMap.get(selectedKey) * unselectedMap.get(unselectedKey);
                } else if(selectedKey < unselectedKey) {
                    defeat += selectedMap.get(selectedKey) * unselectedMap.get(unselectedKey);
                }
            }
            totalWin += win;
            totalDefeat += defeat;
        }
        
        if(Math.max(totalWin, totalDefeat) > maxWin) {
            if(totalWin > totalDefeat) {
                maxWin = totalWin;
                answer = makeAnswer(selectedDice);
            } else {
                maxWin = totalDefeat;
                answer = makeAnswer(unselectedDice);
            }
        }
    }
    
    static int[] makeAnswer(Stack<Integer> dice) {
        int[] output = new int[N/2];
        for(int i=0; i<N/2; i++) {
            output[i] = dice.get(i);
        }
        return output;
    }
    
    static void fillMap(TreeMap<Integer, Integer> map, Stack<Integer> dice, int depth, int sum, int count) {
        if(depth == N/2) {
            int value = map.getOrDefault(sum, 0);
            map.put(sum, value+count);
            return;
        }
        
        int nowDice = dice.get(depth);
        
        for(int key : dices[nowDice].keySet()) {
            fillMap(map, dice, depth+1, sum+key, count*dices[nowDice].get(key));
        }
    }
}