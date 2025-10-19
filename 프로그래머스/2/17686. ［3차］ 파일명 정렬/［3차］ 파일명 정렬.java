import java.util.*;

class Solution {
    static class FileName implements Comparable<FileName> {
        int index;
        String head;
        String number;
        String tail;
        String file;
        
        public FileName(int index, String file) {
            this.index = index;
            this.file = file;
            
            this.head = "";
            this.number = "";
            this.tail = "";
            
            StringBuilder headSb = new StringBuilder();
            StringBuilder numberSb = new StringBuilder();
            
            String status = "head";
            for(int i=0; i<file.length(); i++) {
                char ch = file.charAt(i);
                
                if(ch >= '0' && ch <= '9') {
                    if(status.equals("head")) {
                        status = "number";
                        this.head = headSb.toString();
                    }
                    if(status.equals("number") && numberSb.toString().length() == 5) {
                        this.number = numberSb.toString();
                        this.tail = file.substring(i);
                        break;
                    }
                    numberSb.append(ch);
                } else if(status.equals("head")) {
                    headSb.append(ch);
                } else if(status.equals("number")) {
                    this.number = numberSb.toString();
                    this.tail = file.substring(i);
                    break;
                }
            }
            if(this.number.equals("") && !numberSb.toString().equals("")) {
                this.tail = "";
                this.number = numberSb.toString();
            }
            
        }
        
        @Override
        public int compareTo(FileName o) {
            if(this.head.toUpperCase().equals(o.head.toUpperCase())) {
                if(Integer.valueOf(this.number).compareTo(Integer.valueOf(o.number)) == 0) {
                    return this.index - o.index;
                }
                return Integer.valueOf(this.number).compareTo(Integer.valueOf(o.number));
            }
            return this.head.toUpperCase().compareTo(o.head.toUpperCase());
        }
    }
    
    public String[] solution(String[] files) {
        FileName[] fileNames = new FileName[files.length];
        for(int i=0; i<files.length; i++) {
            fileNames[i] = new FileName(i, files[i]);
        }
        
        Arrays.sort(fileNames);
        
        String[] answer = new String[files.length];
        for(int i=0; i<fileNames.length; i++) {
            answer[i] = fileNames[i].file;
        }
        return answer;
    }
}