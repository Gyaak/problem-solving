package BOJ1149;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost, cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        cache = new int[N][3];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void calc() {
        cache[0][0] = cost[0][0];
        cache[0][1] = cost[0][1];
        cache[0][2] = cost[0][2];
        for(int i = 1; i<N; i++) {
            for(int j = 0; j<3; j++) {
                cache[i][j] = Math.min(cache[i-1][(j+1)%3],cache[i-1][(j+2)%3]) + cost[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        calc();
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<3; i++) {
            ans = Math.min(ans,cache[N-1][i]);
        }
        System.out.println(ans);
    }
}
