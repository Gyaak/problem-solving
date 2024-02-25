package BOJ12784;

import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        // to : 연결된 섬 번호
        // D : 필요한 다이너마이트 수
        int to, D;

        Edge(int to, int D) {
            this.to = to;
            this.D = D;
        }
    }
    
    static ArrayList<Edge>[] Bridge;
    static int N, M;
    static int[] cache;
    static boolean[] visited; // 0번 방향으로 역행하는걸 막기 위함

    static void setInput(BufferedReader br) throws IOException {
        String[] inputStrings = br.readLine().split(" ");
        N = Integer.parseInt(inputStrings[0]);
        M = Integer.parseInt(inputStrings[1]);
        Bridge = new ArrayList[N];
        cache = new int[N];
        visited = new boolean[N];

        for(int i = 0; i<N; i++)
            Bridge[i] = new ArrayList<>();

        for(int i = 0; i<M; i++) {
            inputStrings = br.readLine().split(" ");
            int u = Integer.parseInt(inputStrings[0]) - 1;
            int v = Integer.parseInt(inputStrings[1]) - 1;
            int d = Integer.parseInt(inputStrings[2]);
            Bridge[u].add(new Edge(v, d));
            Bridge[v].add(new Edge(u, d));
        }
    }

    static int Cache(int n) {
        if(cache[n]==0) {
            visited[n] = true;
            for(Edge e : Bridge[n]) {
                if(!visited[e.to]) {
                    cache[n] += Math.min(e.D,Cache(e.to));
                }
            }
            // 리프노드면 필요한 다이너마이트 수를 무한대로
            if(cache[n]==0)
                cache[n] = Integer.MAX_VALUE;
        }
        return cache[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            // 섬이 한개밖에 없는 경우 폭파할 다리가 없음
            if(N==1)    bw.write("0\n");
            else        bw.write(Cache(0)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
