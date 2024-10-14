package BOJ1197;

import java.io.*;
import java.util.*;

public class Main {

    static class Edge{
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    
    static int V, E;
    static int[] parent;
    static PriorityQueue<Edge> edges;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        edges = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
        for(int i = 0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
        }
    }

    static int findParent(int n) {
        int p = n;
        while(p != 0) {
            n = p;
            p = parent[p];
        }
        return n;
    }

    static int solve() {
        int sum = 0;
        for(int i = 0; i<V-1;) {
            Edge cur = edges.poll();
            int parentU = findParent(cur.u);
            int parentV = findParent(cur.v);
            if(parentU == parentV) {
                continue;
            }
            parent[parentV] = parentU;
            sum += cur.w;
            i++;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
