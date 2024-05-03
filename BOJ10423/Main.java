package BOJ10423;

import java.io.*;
import java.util.*;


public class Main {

    static class Node implements Comparable<Node> {
        int num, cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost-o.cost;
        }
    }

    static int N, M, K;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Node>[] cables;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        cables = new ArrayList[N];
        for(int i = 0; i<N; i++)
            cables[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            int generator = Integer.parseInt(st.nextToken())-1;
            pq.add(new Node(generator, 0));
        }

        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            cables[u].add(new Node(v, w));
            cables[v].add(new Node(u, w));
        }

        br.close();
    }

    static int solve() {
        int totalCost = 0;

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            if(visited[curNode.num])
                continue;
            visited[curNode.num] = true;
            totalCost += curNode.cost;
            for(Node c : cables[curNode.num]) {
                if(!visited[c.num]) {
                    pq.add(new Node(c.num, c.cost));
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}