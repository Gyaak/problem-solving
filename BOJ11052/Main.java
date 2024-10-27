package BOJ11052;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] P, cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];
        cache = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solve() {
        cache[1] = P[1];
        for(int i = 2; i<=N; i++) {
            for(int j = 0; j<i; j++) {
                cache[i] = Math.max(cache[i], cache[j] + P[i-j]);
            }
        }
        return cache[N];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}