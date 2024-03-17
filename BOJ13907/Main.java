package BOJ13907;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Edge {
        int v, dist;

        Edge(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    static class Node {
        int n, cnt, dist;

        Node(int n, int cnt, int dist) {
            this.n = n;
            this.cnt = cnt;
            this.dist = dist;
        }
    }

    static int N, M, K, S, D;
    static List<Edge>[] edges;
    static int[][] dist;
    static boolean[][] visited;

    static void setInput(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        dist = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i<N; i++) {
            edges[i] = new ArrayList<>();
            Arrays.fill(dist[i],Integer.MAX_VALUE);
        }
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        D = Integer.parseInt(st.nextToken()) - 1;
        
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, w));
            edges[b].add(new Edge(a, w));
        }
    }

    static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((i,j)->(i.dist-j.dist));
        dist[S][0] = 0;
        pq.add(new Node(S, 0, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.n][cur.cnt])
                continue;
            if(cur.cnt<N-1 && cur.n!=D) {
                for(Edge e : edges[cur.n]) {
                    if(e.dist+cur.dist<dist[e.v][cur.cnt+1]) {
                        dist[e.v][cur.cnt+1] = e.dist + cur.dist;
                        pq.add(new Node(e.v, cur.cnt+1, e.dist + cur.dist));
                    }
                }
            }
            visited[cur.n][cur.cnt] = true;
        }
    }

    static int increasingTax(int t) {
        int cost = Integer.MAX_VALUE;
        for(int i = 0; i<N; i++) {
            if(dist[D][i]==Integer.MAX_VALUE)
                continue;
            cost = Math.min(cost, dist[D][i]+t*i);
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        setInput(br);
        Dijkstra();
        int totalTax = 0;
        bw.write(increasingTax(totalTax)+"\n");
        for(int i = 0; i<K; i++) {
            totalTax += Integer.parseInt(br.readLine());
            bw.write(increasingTax(totalTax)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}