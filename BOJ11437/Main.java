package BOJ11437;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] parent;
    static int[][] query;
    static boolean[] visited;
    static List<Integer>[] graph;
    
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        visited = new boolean[N];
        graph = new List[N];
        for(int i = 0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        M = Integer.parseInt(br.readLine());
        query = new int[M][2];
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            query[i][0] = a;
            query[i][1] = b;
        }
    }

    static void findParent() {
        Queue<Integer> q = new ArrayDeque<>();
        Arrays.fill(visited, false);
        parent[0] = 0;
        visited[0] = true;
        q.add(0);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int c : graph[cur]) {
                if(!visited[c]) {
                    q.add(c);
                    parent[c] = cur;
                    visited[c] = true;
                }
            }
        }
    }

    static int findLCA(int a, int b) {
        if(a == 0 || b == 0) {
            return 1;
        }
        if(a == b) {
            return a + 1;
        }
        Arrays.fill(visited, false);
        visited[a] = true;
        visited[b] = true;
        while(true) {
            int parentA = parent[a];
            int parentB = parent[b];
            if(parentA == parentB) {
                return parentA + 1;
            }
            if(visited[parentA]) {
                return parentA + 1;
            }
            if(visited[parentB]) {
                return parentB + 1;
            }
            if(parentA > 0) {
                visited[parentA] = true;
                a = parentA;
            }
            if(parentB > 0) {
                visited[parentB] = true;
                b = parentB;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        findParent();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int[] q : query) {
            bw.write(findLCA(q[0], q[1]) + "\n");
        }
        bw.flush();
        bw.close();
    }
}
