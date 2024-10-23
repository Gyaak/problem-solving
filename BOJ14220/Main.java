package BOJ14220;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx, dist;
        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
    static final int MAX = 50000001;
    static int N;
    static int[][] cache;
    static List<Node>[] graph;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N][N];
        for(int i = 0; i<N; i++) {
            Arrays.fill(cache[i], MAX);
        }
        graph = new List[N];
        for(int i = 0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                int dist = Integer.parseInt(st.nextToken());
                if(dist>0) {
                    graph[j].add(new Node(i, dist));
                }
            }
        }
    }

    static int Cache(int idx, int depth) {
        if(depth==0) {
            return 0;
        }
        if(cache[idx][depth] == MAX) {
            int val = MAX;
            for(Node node : graph[idx]) {
                val = Math.min(val, node.dist + Cache(node.idx, depth-1));
            }
            cache[idx][depth] = val;
        }
        return cache[idx][depth];
    }



    public static void main(String[] args) throws IOException {
        setInput();
        int ans = MAX;
        for(int i = 0; i<N; i++) {
            ans = Math.min(ans, Cache(i,N-1));
        }
        System.out.println(ans==MAX?-1:ans);
    }
    
}
