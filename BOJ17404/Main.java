package BOJ17404;

import java.io.*;

public class Main {
    static final int MAX = 10000000;
    static int N;
    static int[][] cost, cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        for(int i = 0; i<N; i++) {
            String[] inpuStrings = br.readLine().split(" ");
            cost[i][0] = Integer.parseInt(inpuStrings[0]);
            cost[i][1] = Integer.parseInt(inpuStrings[1]);
            cost[i][2] = Integer.parseInt(inpuStrings[2]);
        }
        br.close();
    }

    static int Cache(int n, int c) {
        if(cache[n][c]==0) {
            cache[n][c] = MAX;
            for(int i = 0; i<3; i++) {
                if(i==c)    continue;
                cache[n][c] = Math.min(cache[n][c], Cache(n-1, i));
            }
            cache[n][c] += cost[n][c];
        }
        return cache[n][c];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = MAX;
        for(int i = 0; i<3; i++) {
            cache = new int[N][3];
            cache[0][i] = MAX;
            cache[0][(i+1)%3] = cost[0][(i+1)%3];
            cache[0][(i+2)%3] = cost[0][(i+2)%3];
            ans = Math.min(ans, cost[N-1][i] + Math.min(Cache(N-2, (i+1)%3), Cache(N-2, (i+2)%3)));
        }
        System.out.println(ans);
    }
}
