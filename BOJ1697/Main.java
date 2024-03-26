package BOJ1697;

import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int N, K;

    static int BFS() {
        boolean[] visited = new boolean[100001];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(N, 0));
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            if(cur.first==K)    return cur.second;
            if(visited[cur.first])  continue;
            visited[cur.first] = true;
            if(cur.first>0)         q.add(new Pair(cur.first-1, cur.second+1));
            if(cur.first<100000)    q.add(new Pair(cur.first+1, cur.second+1)); 
            if(cur.first*2<=100000) q.add(new Pair(cur.first*2, cur.second+1));
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        System.out.println(BFS());
    }
}
