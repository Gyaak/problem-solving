package BOJ14501;

import java.io.*;
import java.util.*;

public class Main {

    static int N, ans;
    static int[] T, P, cache;
    static boolean[] visited = new boolean[15];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        cache = new int[N];
        Arrays.fill(cache, -1);

        StringTokenizer st;
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

    // brute-force
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

    // DP
    static int Cache(int n) {
        if(n<0) return 0;
        if(cache[n]==-1) {
            cache[n] = 0;
            for(int i = 0; i<=n; i++) {
                int tmp = Cache(i-1);
                if(i+T[i]-1==n) {
                    tmp += P[i];
                }
                cache[n] = Math.max(cache[n], tmp);
            }
        }
        return cache[n];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        //DFS(0,0);
        ans = Cache(N-1);
        System.out.println(ans);
    }
}
