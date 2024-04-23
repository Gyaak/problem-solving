package BOJ17835;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        
        int to;
        long cost;
        
        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, K;
    static long[] dist;
    static List<Node>[] cities;
    static PriorityQueue<Node> pq = new PriorityQueue<>((i,j)->(i.cost<j.cost?-1:1));

    static void setInput() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cities = new List[N];
        for(int i = 0; i<N; i++)
            cities[i] = new ArrayList<>();
        dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            cities[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++) {
            int idx = Integer.parseInt(st.nextToken()) - 1;
            pq.add(new Node(idx, 0));
        }

        br.close();
    }

    static void Dijkstra() {

        boolean[] visited = new boolean[N];

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.to;

            if(visited[cur])
                continue;
            visited[cur] = true;
            dist[cur] = curNode.cost;

            for(Node tmpNode : cities[cur]) {
                if(!visited[tmpNode.to]) {
                    pq.add(new Node(tmpNode.to, dist[cur]+tmpNode.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        setInput();
        int ansIdx = -1;
        long ansCost = -1;
        Dijkstra();
        for(int i = 0; i<N; i++) {
            if(ansCost<dist[i]) {
                ansCost = dist[i];
                ansIdx = i+1;
            }
            if(ansCost==dist[i])
                ansIdx = Math.min(ansIdx,i+1);
        }
        System.out.println(ansIdx + "\n" + ansCost);
    }
}
