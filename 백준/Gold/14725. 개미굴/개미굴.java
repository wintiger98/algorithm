import java.io.*;
import java.util.*;

public class Main {
    final static String FLOOR = "--";
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] infos = new String[K];
            for (int j = 0; j < K; j++) {
                String info = st.nextToken();
                infos[j] = info;
            }
            trie.insert(infos);
        }
        trie.print(trie.root, 0);
        bw.close();
    }

    static class Node {
        TreeMap<String, Node> child;
        boolean isEnd;

        public Node() {
            this.child = new TreeMap<>((s1, s2) -> s1.compareTo(s2));
            this.isEnd = false;
        }

        @Override
        public String toString() {
            return "Node{" +
                "child=" + child +
                ", isEnd=" + isEnd +
                '}';
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String[] strings) {
            // 시작노드
            Node node = this.root;

            for(String string : strings) {
                // 자식이 있으면 가져오고 없으면 넣기
                node.child.putIfAbsent(string, new Node());
                // 다음 노드로 이동
                node = node.child.get(string);
            }
            // 마지막 true
            node.isEnd = true;
        }

        void print(Node node, int depth) throws IOException {
            for(String child : node.child.keySet()) {
                for(int i=0; i<depth; i++) { // 층 개수
                    bw.write(FLOOR);
                }
                bw.write(child + "\n");
                print(node.child.get(child), depth+1);
            }
        }
    }
}