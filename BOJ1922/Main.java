package BOJ1922;

import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int a, b, c;
        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N];
        for(int i = 0; i<N; i++) {
            parent[i] = i;
        }
        pq = new PriorityQueue<>((i,j)->i.c-j.c);
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
    }

    static boolean isConnected(int a, int b) {
        while(a!=parent[a]) {
            a = parent[a];
        }
        while(b!=parent[b]) {
            b = parent[b];
        }
        return a==b;
    }

    static void setParent(int target, int val) {
        if(target!=parent[target]) {
            setParent(parent[target],val);
        }
        parent[target] = val;
    }

    static int solve() {
        int cost = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(isConnected(cur.a,cur.b)) {
                continue;
            }
            setParent(cur.a, Math.min(cur.a,cur.b));
            setParent(cur.b, Math.min(cur.a,cur.b));
            cost += cur.c;
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
