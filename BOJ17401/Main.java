package BOJ17401;

import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;
    static int T, N, D;
    static long[][][] M, M1, M2;

    static void setInput(BufferedReader br, int idx) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            M[idx][a][b] = c;
        }
    }

    static long[][] matrixMultiply(long[][] A, long[][] B) {
        long[][] result = new long[N][N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                for(int k = 0; k<N; k++) {
                    result[i][j] += A[i][k]*B[k][j];
                    result[i][j] %= MOD;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        M = new long[T+1][N][N];
        M1 = new long[T+1][N][N];
        M2 = new long[32][N][N];
        long[][] ans = new long[N][N];
        for(int i = 0; i<T; i++) {
            setInput(br, i+1);
        }
        for(int i = 0; i<N; i++) {
            ans[i][i] = 1;
            M1[0][i][i] = 1;
            M2[0][i][i] = 1;
        }

        M1[1] = M[1];
        for(int i = 2; i<=T; i++) {
            M1[i] = matrixMultiply(M1[i-1], M[i]);
        }
        M2[1] = M1[T];
        for(int i = 2;i<32; i++) {
            M2[i] = matrixMultiply(M2[i-1], M2[i-1]);
        }

        int div = D/T;
        int res = D%T;
        int pos = 1;
        while(div>0) {
            if(div%2==1)
                ans = matrixMultiply(ans, M2[pos]);
            div >>= 1;
            pos++;
        }
        ans = matrixMultiply(ans, M1[res]);

        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++)
                sb.append(ans[i][j] + " ");
            sb.append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
