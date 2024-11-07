//package BOJ14863;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] cache, walk, bicycle;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cache = new int[N][K+1];
        walk = new int[N][2];
        bicycle = new int[N][2];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());
            bicycle[i][0] = Integer.parseInt(st.nextToken());
            bicycle[i][1] = Integer.parseInt(st.nextToken());
        }
        cache[0][walk[0][0]] = Math.max(cache[0][walk[0][0]], walk[0][1]);
        cache[0][bicycle[0][0]] = Math.max(cache[0][bicycle[0][0]], bicycle[0][1]);
    }

    static int solve() {
        for(int i = 1; i<N; i++) {
            for(int j = 0; j<=K; j++) {
                if(cache[i-1][j]!=0) {
                    if(j+walk[i][0]<=K) {
                        cache[i][j+walk[i][0]] = Math.max(cache[i][j+walk[i][0]], cache[i-1][j] + walk[i][1]);
                    }
                    if(j+bicycle[i][0]<=K) {
                        cache[i][j+bicycle[i][0]] = Math.max(cache[i][j+bicycle[i][0]], cache[i-1][j] + bicycle[i][1]);
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i<=K; i++) {
            ans = Math.max(ans, cache[N-1][i]);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
