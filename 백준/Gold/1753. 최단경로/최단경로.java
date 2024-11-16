//package BOJ1753;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Edge implements Comparable<Edge> {
        int v;
        int c;

        Edge(int v, int c){
            this.v = v;
            this.c = c;
        }

        @Override
        public int compareTo(Edge e) {
            return (int)(this.c-e.c);
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int N, M, S;
    static ArrayList<Edge>[] E;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        S = Integer.parseInt(br.readLine())-1;
        E = new ArrayList[N];

        for(int i = 0; i<N; i++)
            E[i] = new ArrayList<>();
        for(int i = 0; i<M; i++) {
            s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0])-1;
            int v = Integer.parseInt(s[1])-1;
            int c = Integer.parseInt(s[2]);

            E[u].add(new Edge(v, c));
        }
        br.close();
    }

    static void Dijkstra(int start) throws IOException {
        int[] dist = new int[N];
        for(int i = 0; i<N; i++)
            dist[i] = INF;
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq.add(new Edge(start, dist[start]));
        while(!pq.isEmpty()) {
            int cur = pq.peek().v;
            int curDist = pq.poll().c;
            if(visited[cur])    continue;
            for(Edge e : E[cur]) {
                if(!visited[e.v] && curDist+e.c<dist[e.v]) {
                    dist[e.v] = curDist+e.c;
                    pq.add(new Edge(e.v, dist[e.v]));
                }
            }
            visited[cur] = true;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i<N; i++) {
            if(dist[i]==INF)    bw.write("INF\n");
            else                bw.write(dist[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        Dijkstra(S);
    }
}
