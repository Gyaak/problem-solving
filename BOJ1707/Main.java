package BOJ1707;

import java.io.*;
import java.util.*;

public class Main {
    static int K, V, E;
    static List<Integer>[] graph;

    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V];
        for(int i = 0; i<V; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
    }

    static boolean solve() {
        int[] visited = new int[V];
        for(int i = 0; i<V; i++) {
            if(visited[i]!=0) {
                continue;
            }
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            visited[i] = 1;
            while(!q.isEmpty()) {
                int cur = q.poll();
                for(int v : graph[cur]) {
                    if(visited[v]==visited[cur]) {
                        return false;
                    }
                    if(visited[v]==0) {
                        q.add(v);
                        visited[v] = -visited[cur];
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i<K; i++) {
            setInput(br);
            bw.write(solve()?"YES\n":"NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
