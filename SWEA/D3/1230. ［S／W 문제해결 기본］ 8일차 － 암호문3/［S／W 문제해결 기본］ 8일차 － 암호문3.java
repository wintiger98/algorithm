import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static class LinkedList {
        private Node head = new Node();
        private Node tail = new Node();
        private int size;

        {
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 앞에서 index 까지 values 반환하는 메서드
         * @param index : index
         * @return : value 배열
         */
        int[] getElementsValueUntilIndex(int index) {
            int[] result = new int[index];
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
                result[i] = cur.value;
            }
            return result;
        }

        /**
         * index 뒤에 nodes 삽입
         * @param index : index
         * @param nodes : 추가할 노드
         */
        void insert(int index, Node[] nodes) {
            // index 위치 노드 찾기
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            // 노드 끼리 연결
            if(nodes.length > 2) {
                // 첫 노드 연결
                nodes[0].next = nodes[1];
                // 2~끝-1까지 연결
                for (int i = 1; i < nodes.length - 1; i++) {
                    nodes[i].next = nodes[i+1];
                    nodes[i].prev = nodes[i-1];
                }
                // 끝의 prev는 그 전꺼에 연결
                nodes[nodes.length - 1].prev = nodes[nodes.length - 2];
            } else if(nodes.length == 2){
                nodes[0].next = nodes[1];
                nodes[1].prev = nodes[0];
            }

            int lastIndex = nodes.length - 1;
            Node nextNode = cur.next;

            // 노드들을 뒤에 달기
            // 1. 이 노드들의 끝의 다음을 현재 노드 다음꺼로 두기
            nodes[lastIndex].next = nextNode;
            // 2. 현재 노드의 다음꺼의 이전을 이 노드들의 끝으로 두기
            nextNode.prev = nodes[lastIndex];
            // 3. 이 노드들의 처음의 이전을 현재 노드로 두기
            nodes[0].prev = cur;
            // 4. 현재 노드의 다음을 이 노드들의 처음으로 두기
            cur.next = nodes[0];
            // size 늘리기
            size += nodes.length;
        }

        /**
         * start 부터 count 개만큼 삭제하는 메서드
         * @param start : 시작점
         * @param count : 삭제 개수
         */
        void delete(int start, int count) {
            // 현재 노드 찾기
            Node cur = head;
            for (int i = 0; i < start; i++) {
                cur = cur.next;
            }

            // 삭제 이전 노드
            Node prevNode = cur;
            // 임시 위치
            Node tmpCur = cur;
            // 다음 노드로 이동 (삭제 대상 노드 1)
            cur = cur.next;
            // 삭제할 마지막 노드
            for (int i = 0; i < count; i++) {
                tmpCur = tmpCur.next;
            }
            // 삭제안될 첫 노드
            Node nextNode = tmpCur.next;
            // cur ~ tmpCur : 삭제 대상 노드들
            // prevNode : 삭제 대상 노드들의 이전 노드
            // nextNode : 삭제 대상 노드들의 다음 노드
            // prevNode.next -> nextNode
            // nextNode.prev -> prevNode
            // cur.prev -> null
            // tmpCur.next -> null
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            cur.prev = null;
            tmpCur.next = null;

            // 사이즈 재조정
            size -= count;
        }

        void add(Node[] nodes) {
            insert(size, nodes);
        }
    }

    /**
     * 노드
     */
    static class Node {
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int test_case = 1; test_case <= 10; test_case++) {
            bw.write("#" + test_case + " ");

            LinkedList ll = new LinkedList();
            int N = Integer.parseInt(br.readLine()); // 암호문 개수

            Node[] cryptos = new Node[N]; // 암호문 노드 배열

            // 암호문 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cryptos[i] = new Node(Integer.parseInt(st.nextToken()));
            }
            ll.insert(0, cryptos);


            int M = Integer.parseInt(br.readLine()); // 명령문 개수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String order = st.nextToken(); // 명령 (I, D, A)
                int x, y;
                Node[] crypto2add;
                switch (order) {
                    case "I":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        crypto2add = new Node[y];
                        for (int j = 0; j < y; j++) {
                            crypto2add[j] = new Node(Integer.parseInt(st.nextToken()));
                        }
                        ll.insert(x, crypto2add);
                        break;
                    case "D":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        ll.delete(x, y);
                        break;
                    case "A":
                        y = Integer.parseInt(st.nextToken());
                        crypto2add = new Node[y];
                        for (int j = 0; j < y; j++) {
                            crypto2add[j] = new Node(Integer.parseInt(st.nextToken()));
                        }
                        ll.add(crypto2add);
                        break;
                }
            }
            for (int value: ll.getElementsValueUntilIndex(10)) {
                bw.write(value + " ");
            }
            bw.write("\n");
            bw.flush();
        }
        bw.close();
    }
}