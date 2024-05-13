package BOJ1277;

import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first;
        double second;
        Pair(int first, double second) {
            this.first = first;
            this.second = second;
        }
    }

    static int N, W;
    static double M;
    static boolean[] visited;
    static List<Integer>[] connections;
    static Pair[] plants;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        M = Double.parseDouble(br.readLine());
        visited = new boolean[N];
        connections = new List[N];
        plants = new Pair[N];
        for(int i = 0; i<N; i++)
            connections[i] = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            plants[i] = new Pair(x, y);
        }
        for(int i = 0; i<W; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            connections[u].add(v);
            connections[v].add(u);
        }
    }

    static double findDist(Pair p1, Pair p2) {
        double xDist = p1.first - p2.first;
        double yDist = p1.second - p2.second;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }

    static int Dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>((i,j)->(int)(i.second-j.second));
        pq.add(new Pair(0, 0));
        visited[0] = true;
        while(!pq.isEmpty()) {
            int curNum = pq.peek().first;
            double curDist = pq.poll().second;
            if(curNum==N-1)
                return (int)(1000*curDist);
            for(int p : connections[curNum]) {
                if(!visited[p])
                    pq.add(new Pair(p, curDist));
                    visited[p] = true;
            }
            for(int i = 0; i<N; i++) {
                if(!visited[i]) {
                    double tmpDist = findDist(plants[curNum], plants[i]);
                    if(tmpDist<=M)
                    pq.add(new Pair(i, curDist+tmpDist));
                }
            }
            visited[curNum] = true;
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(Dijkstra());
    }
}
