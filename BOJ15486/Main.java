package BOJ15486;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] T, P, cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = new int[N+2];
        P = new int[N+2];
        cache = new int[N+2];

        for(int i = 1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(int i = 1; i<=N+1; i++) {
            cache[i] = Math.max(cache[i],cache[i-1]);
            if(i+T[i]<=N+1) {
                cache[i+T[i]] = Math.max(cache[i+T[i]], cache[i]+P[i]); 
            }
        }
        System.out.println(cache[N+1]);
    }
}
