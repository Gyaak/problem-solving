package BOJ1967;

import java.io.*;
import java.util.*;

public class Main {

    static class Node{
        int n, w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    static int N;
    static List<Node>[] tree;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new List[N];
        for(int i = 0; i<N; i++) {
            tree[i] = new ArrayList<>();
        }
        for(int i = 0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            tree[u].add(new Node(v, w));
            tree[v].add(new Node(u, w));
        }
    }

    static Node BFS(int startIdx) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        q.add(new Node(startIdx, 0));
        visited[startIdx] = true;

        Node maxNode = new Node(0, 0);
        while (!q.isEmpty()) {
            Node curNode = q.poll();
            if(maxNode.w<curNode.w) {
                maxNode = curNode;
            }
            for(Node node : tree[curNode.n]) {
                if(!visited[node.n]) {
                    q.add(new Node(node.n, node.w+curNode.w));
                    visited[node.n] = true;
                }
            }
        }
        return maxNode;
    }

    public static void main(String[] args) throws IOException{
        setInput();
        System.out.println(BFS(BFS(0).n).w);
    }
}
