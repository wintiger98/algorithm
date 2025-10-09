import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String num = change(n, k);
        
        String[] numbers = num.split("0");
        //System.out.println(Arrays.toString(numbers));
        for(String number : numbers) {
            if(number.equals("")) continue;
            if(isPrimeNumber(Long.parseLong(number))) 
                answer++;
        }
        
        return answer;
    }
    
    private String change(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        while(n >= k) {
            int cur = n % k;
            sb.append(cur);
            n /= k;
        }
        sb.append(n);
        return sb.reverse().toString();
    }
    
    private boolean isPrimeNumber(long num) {
        if(num == 1) return false;
        if(num == 2 || num == 3) return true;
        for(long i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}