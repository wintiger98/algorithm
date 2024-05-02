import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Room implements Comparable<Room> {
      long start, end;

      public Room(long start, long end) {
        this.start = start;
        this.end = end;
      }

      @Override
      public int compareTo(Room o) {
        if(this.end == o.end) {
          return Long.compare(this.start, o.start);
        }
        return Long.compare(this.end, o.end);
      }
    }
    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      Room[] rooms = new Room[N];
      StringTokenizer st;
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        long start = Integer.parseInt(st.nextToken());
        long end = Integer.parseInt(st.nextToken());
        rooms[i] = new Room(start, end);
      }

      Arrays.sort(rooms);

      int answer = 0;
      long last_end = 0;
      for(Room room : rooms){
        if(last_end <= room.start) {
          answer++;
          last_end = room.end;
        }
      }
      System.out.println(answer);
    }
}

