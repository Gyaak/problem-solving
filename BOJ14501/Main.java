package BOJ14501;

import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static int[] T, P;
    static boolean[] visited = new boolean[15];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];

        StringTokenizer st;
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

    static void DFS(int idx, int val) {
        if(idx<=N) {
            ans = Math.max(ans, val);
        }

        for(int i = idx; i<N; i++) {
            visited[i] = true;
            DFS(i+T[i],val+P[i]);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        DFS(0,0);
        System.out.println(ans);
    }
}
