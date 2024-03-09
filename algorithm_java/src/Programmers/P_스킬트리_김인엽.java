
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P_스킬트리_김인엽 {

}
class Solution {
  // 위상정렬!
  public int solution(String skill, String[] skill_trees) {
    int answer = 0;
    List<String> skills = new ArrayList<>(Arrays.asList(skill.split("")));
    for(String skill_tree : skill_trees) {
      int flag = 1 << skills.size() - 1;
      String[] tmpSkillTree = skill_tree.split("");
      boolean isAnswer = false;
      for(String tmpSkill : tmpSkillTree) {
        if(skills.contains(tmpSkill)) {
          if(tmpSkill.equals(skills.get(0))) {
            flag |= 1 << 0;
          } else {

          }
        }
      }
      int last = 0;
      for(int i=0; i<skills.size(); i++) {
        if((flag & (1 << i)) == 0) {
          if(last != 0) {
            break;
          }
          last = 0;
        } else {
          if(last != 1) {
            break;
          }
          last = 1;
        }
      }
      if(isAnswer) answer++;
    }

    return answer;
  }
}