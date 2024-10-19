package BOJ9205;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static boolean[][] graph;
    
    static boolean isConnected(Node n1, Node n2) {
        int xDist = Math.abs(n1.x - n2.x);
        int yDist = Math.abs(n1.y - n2.y);

        return xDist + yDist <= 1000 ? true : false;
    }

    static void makeGraph(List<Node> nodes) {
        for(int i = 0; i<N+2; i++) {
            for(int j = 0; j<N+2; j++) {
                graph[i][j] = isConnected(nodes.get(i), nodes.get(j));
            }
        }
    }

    static void setInput(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N+2][N+2];
        List<Node> nodes = new ArrayList<>();
        for(int i = 0; i<N+2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes.add(new Node(x, y));
        }
        makeGraph(nodes);
    }

    static boolean BFS() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+2];
        q.add(0);
        visited[0] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(cur == N+1) {
                return true;
            }
            for(int i = 0; i<N+2; i++) {
                if(graph[cur][i] && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            System.out.println(BFS() ? "happy" : "sad");
        }
    }
}
