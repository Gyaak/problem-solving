package BOJ1854;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int num;
        long dist;

        Node(int num, long dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static int n, m, k;
    static long[] kDist;
    static List<Node> cities[];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        kDist = new long[n];
        Arrays.fill(kDist,-1);
        cities = new List[n];
        for(int i = 0; i<n; i++) {
            cities[i] = new ArrayList<>();
        }
        for(int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            cities[u].add(new Node(v,c));
        }
    }

    static void Dijkstra(int start) {
        int[] visitedCount = new int[n];
        visitedCount[start] = 1;
        if(k==1) {
            kDist[start] = 0;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((i,j)->Long.compare(i.dist, j.dist));
        for(Node p : cities[start]) {
            pq.add(p);
        }
        while(!pq.isEmpty()) {
            int curNum = pq.peek().num;
            long curDist = pq.poll().dist;
            visitedCount[curNum]++;
            if(visitedCount[curNum]==k) {
                kDist[curNum] = curDist;
            } else if(visitedCount[curNum]>k) {
                continue;
            }
            for(Node p : cities[curNum]) {
                if(visitedCount[p.num]<k) {
                    pq.add(new Node(p.num, p.dist+curDist));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        Dijkstra(0);
        StringBuilder ans = new StringBuilder();
        for(long l : kDist) {
            ans.append(l+"\n");
        }
        System.out.print(ans);
    }
}
