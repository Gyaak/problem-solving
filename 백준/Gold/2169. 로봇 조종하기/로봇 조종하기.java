//package BOJ2169;

import java.io.*;
import java.util.*;

public class Main {
    static final int EMPTY = Integer.MIN_VALUE, IMPOSSIBLE = Integer.MIN_VALUE >> 1;
    static int N, M;
    static int[][] value;
    static int[][][] cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        value = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                value[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void initCache() {
        // cache[i][j][0] : (i,j)에 왼쪽에서 도착했을 때 최대값
        // cache[i][j][1] : (i,j)에 오른쪽에서 도착했을 때 최대값
        // cache[i][j][2] : (i,j)에 위쪽에서 도착했을 때 최대값
        cache = new int[N][M][3];

        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                cache[i][j][0] = EMPTY;
                cache[i][j][1] = EMPTY;
                cache[i][j][2] = EMPTY;
            }
        }
        for(int i = 0; i<M; i++) {
            cache[N-1][i][1] = IMPOSSIBLE;
        }
        for(int i = 0; i<N; i++) {
            cache[i][0][0] = IMPOSSIBLE;
            cache[i][M-1][1] = IMPOSSIBLE;
        }
        cache[0][0][0] = value[0][0];
        cache[0][0][1] = value[0][0];
        cache[0][0][2] = value[0][0];
        for(int i = 1; i<M; i++) {
            cache[0][i][0] = value[0][i] + cache[0][i-1][0];
            cache[0][i][1] = IMPOSSIBLE;
            cache[0][i][2] = IMPOSSIBLE;
        }
    }

    static int Cache(int n, int m, int d) {
        if(cache[n][m][d] == EMPTY) {
            
            // 왼쪽에서 오는 경우
            if(d == 0) {
                cache[n][m][d] = Math.max(cache[n][m][d], Cache(n, m-1, 0));
                cache[n][m][d] = Math.max(cache[n][m][d], Cache(n, m-1, 2));
            }
            // 오른쪽에서 오는 경우
            else if(d == 1) {
                cache[n][m][d] = Math.max(cache[n][m][d], Cache(n, m+1, 1));
                cache[n][m][d] = Math.max(cache[n][m][d], Cache(n, m+1, 2));
            }
            // 위에서 오는 경우
            else {
                for(int i = 0; i<3; i++) {
                    cache[n][m][d] = Math.max(cache[n][m][d], Cache(n-1, m, i));
                }
            }
            cache[n][m][d] += value[n][m];
        }
        return cache[n][m][d];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        initCache();
        int ans = Cache(N-1, M-1, 0);
        ans = Math.max(ans, Cache(N-1, M-1, 1));
        ans = Math.max(ans, Cache(N-1, M-1, 2));
        System.out.println(ans);
    }
}
