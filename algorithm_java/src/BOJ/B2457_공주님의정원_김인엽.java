import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class B2457_공주님의정원_김인엽 {

  static class Day {
    List<Integer> projects = new ArrayList<>();

    @Override
    public String toString() {
      return "Day [projects=" + projects + "]";
    }

  }
  static class Month {
    Day[] day;

    @Override
    public String toString() {
      return "Month [day=" + Arrays.toString(day) + "]";
    }

  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine()); // 참여 가능한 프로젝트 개수
    StringTokenizer st;

    // 1월부터 12월까지 기록
    Month[] months = new Month[13];
    // 초기화
    for(int i=1; i<13; i++) {
      months[i] = new Month();
      switch(i) {
        case 1: case 5: case 8:
        case 3: case 7: case 10:
        case 12:
          // 31일까지 존재
          months[i].day = new Day[32];
          break;
        case 4: case 6: case 9: case 11:
          months[i].day = new Day[31];
//				// 30일까지 존재
          break;
        case 2:
          months[i].day = new Day[29];
//				// 28일까지 존재
          break;
      }
      for(int j=1; j<months[i].day.length; j++) {
        months[i].day[j] = new Day();
      }
    }

    int answer = N; // 최대 N개
    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());
      int startMonth = Integer.parseInt(st.nextToken()); // 시작 월
      int startDay = Integer.parseInt(st.nextToken()); // 시작 일
      int endMonth = Integer.parseInt(st.nextToken()); // 끝 월
      int endDay = Integer.parseInt(st.nextToken()); // 끝 일
      // 2월달에 끝나는 거면 없어도 되니 패스
      if(endMonth == 2) continue;
      // 12월 2일부터 시작하는 거는 없어도 되니 패스
      if(startMonth == 12 && startDay >= 2) continue;
      int tmpMonth = startMonth;
      int tmpDay = startDay;
      while(true) { // 주어진 기간동안의 카운트 올리기
        if(tmpMonth == endMonth && tmpDay == endDay) break;
        // 해당 날짜 프로젝트에 추가
        months[tmpMonth].day[tmpDay].projects.add(i);
        tmpDay++;

        if(tmpDay >= months[tmpMonth].day.length) { // 만약 해당 월의 일수 다 채우면 다음 달로
          tmpDay = 1;
          tmpMonth++;
        }
      }
    }
//		print(months);

    // 확인
    for(int i=1; i<13; i++) {
      for(int j=1; j<months[i].day.length; j++) {
        // 만약 3월 1일부터 11월 30일까지 없는 프로젝트가 있다면 0출력 후 끝
        if(i >= 3 && i<=11) {
          if(months[i].day[j].projects.isEmpty()) {
            System.out.println(0);
            return;
          }
        }
      }
    }

    System.out.println(answer);
  }
  public static void print(Month[] month) {
    int i = 0;
    for(Month m : month) {
      System.out.println(i + "월");
      System.out.println(m);
      i++;
    }
  }
}
