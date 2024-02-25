package 그래프;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import 그래프.AdjList.Node;

public class EdgeList {
  static class Edge {
    int from, to;

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public String toString() {
      return "Edge{" +
          "from=" + from +
          ", to=" + to +
          '}';
    }
  }
  public static void main(String[] args) throws FileNotFoundException {
    System.setIn(new FileInputStream("graph_input1.txt"));
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E = sc.nextInt();

    Edge[] edgeList = new Edge[E];
    for(int i=0; i<E; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      edgeList[i] = new Edge(from, to);
    }

    for (Edge a : edgeList) {
      System.out.println(a);
    }
  }
}
