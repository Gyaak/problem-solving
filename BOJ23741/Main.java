package BOJ23741;

import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int n, d;
        Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
    static int N, M, X, Y;
    static List<Integer>[] graph;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken());

        graph = new List[N];
        for(int i = 0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    static String BFS() {
        boolean[][] visited = new boolean[N][Y+1];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(X, 0));
        visited[X][0] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.d < Y) {
                for(int g : graph[cur.n]) {
                    if(!visited[g][cur.d+1]) {
                        q.add(new Node(g, cur.d+1));
                        visited[g][cur.d+1] = true;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            if(visited[i][Y]) {
                sb.append((i+1) + " ");
            }
        }
        return sb.length() == 0 ? "-1" : sb.toString();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
