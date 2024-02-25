package 그래프;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class AdjList {
  static class Node {
    int to;
    Node next;

    public Node(int to, Node next) {
      this.to = to;
      this.next = next;
    }

    @Override
    public String toString() {
      return "Node{" +
          "to=" + to +
          ", next=" + next +
          '}';
    }
  }
  public static void main(String[] args) throws FileNotFoundException {
    System.setIn(new FileInputStream("graph_input1.txt"));
    Scanner sc = new Scanner(System.in);
    int V = sc.nextInt();
    int E = sc.nextInt();

    Node[] adjList = new Node[V];
    for(int i=0; i<E; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      adjList[from] = new Node(to, adjList[from]);
      adjList[to] = new Node(from, adjList[to]);
    }

    for (Node a : adjList) {
      System.out.println(a);
    }
  }
}
