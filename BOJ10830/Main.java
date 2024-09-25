package BOJ10830;

import java.io.*;
import java.util.*;

public class Main {
    static final int LEN = 37;
    static int N;
    static int[][] A;
    static int[][][] cache;
    static long B;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int[][] multiply(int[][] M1, int[][] M2) {
        int[][] result = new int[N][N];
        for(int i = 0; i<N; i++) {
            for(int j =0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    result[i][j] += M1[i][k]*M2[k][j];
                }
                result[i][j] %= 1000;
            }
        }
        return result;
    }

    static void init() {
        cache = new int[LEN][N][N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                cache[0][i][j] = A[i][j];
            }
        }
        for(int i = 1; i<LEN; i++) {
            cache[i] = multiply(cache[i-1],cache[i-1]);
        }
    }

    static int[][] solve() {
        int[][] result = new int[N][N];
        for(int i = 0; i<N; i++) {
            result[i][i] = 1;
        }
        for(int i = 0; i<LEN; i++) {
            if((B & (1L<<i)) != 0) {
                result = multiply(result, cache[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        init();
        int[][] ans = solve();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}