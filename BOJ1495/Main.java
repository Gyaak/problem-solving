package BOJ1495;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int n, v;

        Node(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
    static int N, S, M;
    static int[] V;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        V = new int[N];
        for(int i = 0; i<N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int BFS() {
        int maxVolume = -1;
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N+1][M+1];
        q.add(new Node(0, S));
        visited[0][S] = true;

        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int n = curNode.n;
            int v = curNode.v;
            if(n == N) {
                maxVolume = Math.max(maxVolume, curNode.v);
                continue;
            }
            if(v+V[n]<=M && !visited[n+1][v+V[n]]) {
                q.add(new Node(n+1, v+V[n]));
                visited[n+1][v+V[n]] = true;
            }
            if(v-V[n]>=0 && !visited[n+1][v-V[n]]) {
                q.add(new Node(n+1, v-V[n]));
                visited[n+1][v-V[n]] = true;
            }
        }
        return maxVolume;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
