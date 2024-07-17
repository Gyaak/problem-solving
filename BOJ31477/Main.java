package BOJ31477;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int n, dist;

        Node(int n, int dist) {
            this.n = n;
            this.dist = dist;
        }
    }

    static int N;
    static boolean[] visited;
    static List<Node>[] adj;
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        visited[0] = true;
        adj = new List[N];
        for(int i = 0; i<N; i++)
            adj[i] = new ArrayList<>();
        for(int i = 0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int V = Integer.parseInt(st.nextToken());

            adj[A].add(new Node(B, V));
            adj[B].add(new Node(A, V));
        }
    }

    static int calc(int idx) {
        visited[idx] = true;
        int parentDist = 100_000_000;
        int childDist = 0;
        for(Node node : adj[idx]) {
            if(visited[node.n]) {
                parentDist = node.dist;
            } else {
                childDist += calc(node.n);
            }
        }
        if(idx!=0 && adj[idx].size()==1)
            return parentDist;
        else
            return Math.min(parentDist, childDist);
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(calc(0));
    }
}
