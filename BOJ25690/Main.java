package BOJ25690;

import java.io.*;
import java.util.*;

public class Main {

    static final int WHITE = 0, BLACK = 1;
    static int n;
    static long[][] cost, cache;
    static List<Integer>[] child;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new long[n][2];
        cache = new long[n][2];
        child = new List[n];
        for(int i = 0; i<n; i++) {
            child[i] = new ArrayList<>();
        }
        for(int i = 0; i<n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            child[p].add(c);
        }
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Long.parseLong(st.nextToken());
            cost[i][1] = Long.parseLong(st.nextToken());
        }
    }

    static long Cache(int n, int color) {
        if(cache[n][color] == 0) {
            cache[n][color] = cost[n][color];
            for(int c : child[n]) {
                if(color == BLACK) {
                    cache[n][BLACK] += Cache(c, WHITE);
                } else {
                    cache[n][WHITE] += Math.min(Cache(c, BLACK), Cache(c, WHITE));
                }
            }
        }
        return cache[n][color];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(Math.min(Cache(0, BLACK), Cache(0, WHITE)));
    }
}
