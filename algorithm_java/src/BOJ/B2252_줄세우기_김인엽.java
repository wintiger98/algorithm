import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 	48220	480
public class B2252_줄세우기_김인엽 {
  final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  final static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  static int[] inDegrees;
  static List<Integer>[] arr;

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    arr = new ArrayList[N+1]; // 인접 리스트
    for(int i=0; i<N+1; i++) arr[i] = new ArrayList<>(); // 초기화

    inDegrees = new int[N+1];

    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      inDegrees[b]++; // 해당 번호 학생의 인접 노드 개수 증가(a -> b니까 b 증가)
      arr[a].add(b); // a -> b
    }

    // 1. 진입차수 0인 친구들 찾아서 큐에 넣어주기
    Queue<Integer> queue = new ArrayDeque<>();
    for(int i=1; i<N+1; i++) {
      if(inDegrees[i] == 0) queue.add(i);
    }

    int[] result = new int[N];
    int resultIdx = 0;
    // 2. 큐에서 꺼내서,
    while(!queue.isEmpty()) {
      int student = queue.poll();
      result[resultIdx++] = student;
      // 2.1 자신과 인접한 노드의 간선 제거. -> 인접한 노드의 진입 차수 1 감소
      for(int s : arr[student]) {
        inDegrees[s]--;
        // 3. 간선 제거 후 진입 차수가 0이 된 친구는 큐에 넣기
        if(inDegrees[s] == 0) {
          queue.add(s);
        }
      }
    }

    for(int i=0; i<N; i++) {
      bw.write(result[i] + " ");
    }
    bw.close();
  }

}