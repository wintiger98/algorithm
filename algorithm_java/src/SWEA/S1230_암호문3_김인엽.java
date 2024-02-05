import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class S1230_암호문3_김인엽 {
  static class LinkedList {
    private Node head = new Node();
    private Node tail = new Node();
    private int size;

    {
      head.next = tail;
      tail.prev = head;
    }

    int[] getElementsValue(int idx) {
      // 앞에서 idx번째까지 values 반환
      int[] result = new int[idx];
      Node cur = head;
      for(int i=0; i<idx; i++) {
        cur = cur.next;
        result[i] = cur.value;
      }

      return result;
    }

    void insert(int idx, Node[] nodes) {
      // 현재 노드 찾기
      Node cur = head;
      for(int i=0; i<idx; i++) {
        cur = cur.next;
      }

      // 들어올 노드끼리 연결하기
      // 첫번째 요소는 next, 마지막 요소는 prev만
      if(nodes.length > 2) {
        nodes[0].next = nodes[1];
        for(int i=1; i<nodes.length-1; i++) {
          nodes[i].next = nodes[i+1];
          nodes[i].prev = nodes[i-1];
        }
        nodes[nodes.length-1].prev = nodes[nodes.length-2];
      } else if(nodes.length == 2) {
        nodes[0].next = nodes[1];
        nodes[1].prev = nodes[0];
      }

      int lastIndex = nodes.length-1;
      Node nextNode = cur.next;
      // 노드들을 현재 뒤에다가 달아두기
      // 1. 이 노드들의 끝의 다음을 현재 노드의 다음꺼로 두기
      nodes[lastIndex].next = nextNode;
      // 2. 현재 노드의 다음꺼의 이전을 이 노드들의 끝으로 두기
      nextNode.prev = nodes[lastIndex];
      // 3. 이 노드들의 처음의 이전을 현재 노드로 두기
      nodes[0].prev = cur;
      // 4. 현재 노드의 다음을 이 노드들의 처음으로 두기
      cur.next = nodes[0];
      // 사이즈 늘려주기
      size += nodes.length;
    }

    void delete(int start, int count) {
      // 현재 노드 찾기
      Node cur = head;
      for(int i=0; i<start; i++) {
        cur = cur.next;
      }
      Node prevNode = cur;
      Node tmpCur = cur;
      cur = cur.next;
      for(int i=0; i<count; i++) {
        tmpCur = tmpCur.next;
      }
      Node nextNode = tmpCur.next;
      // cur ~ tmpCur : 삭제 대상 노드들
      // prevNode: 삭제 대상 노드들의 이전 노드
      // nextNode: 삭제 대상 노드들의 다음 노드
      // prevNode.next -> nextNode
      // nextNode.prev -> prevNode
      // cur.prev -> null
      // tmpCur.next -> null
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      cur.prev = null;
      tmpCur.next = null;

      size -= count;
    }

    void add(Node[] nodes) {
      insert(size, nodes);
      size += nodes.length;
    }

    int size() {
      return size;
    }
  }
  // 연결리스트 만들자
  static class Node{
    int value;
    Node prev;
    Node next;

    public Node() {

    }

    public Node(int value) {
      super();
      this.value = value;
    }
    public Node(int value, Node prev, Node next) {
      super();
      this.value = value;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" +
          "value=" + value +
          '}';
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    for(int test_case=1; test_case<=10; test_case++) {
      int N = Integer.parseInt(br.readLine());
      LinkedList ll = new LinkedList();
      StringTokenizer st = new StringTokenizer(br.readLine());
      Node[] codes = new Node[N];
      // 암호문
      for(int i=0; i<N; i++) {
        codes[i] = new Node(Integer.parseInt(st.nextToken()));
      }
      // 링크드리스트에 암호문 넣기
      ll.insert(0, codes);
      // 명령어 개수
      int M = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());

      String order;
      int x, y; // 위치 x, 개수 y
      Node[] nodes;
      for(int i=0; i<M; i++) {
        order = st.nextToken(); // I -> x, y, s / D -> x, y / A -> y, s
        switch(order) {
          case "I":
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            nodes = new Node[y]; // 넣을 숫자들
            for(int j=0; j<y; j++) {
              nodes[j] = new Node(Integer.parseInt(st.nextToken()));
            }
            ll.insert(x, nodes); // 삽입
            break;
          case "D":
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            ll.delete(x, y);
            break;
          case "A":
            y = Integer.parseInt(st.nextToken());
            nodes = new Node[y]; // 넣을 숫자들
            for(int j=0; j<y; j++) {
              nodes[j] = new Node(Integer.parseInt(st.nextToken()));
            }
            break;
        }

      }

      bw.write("#"+test_case+ " ");

      for(int value: ll.getElementsValue(10)) {
        bw.write(value + " ");
      }
      bw.write("\n");
      bw.flush();
    }
    bw.close();
  }

}
