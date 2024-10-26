package BOJ31434;

import java.io.*;
import java.util.*;

public class Main {

    static int N, K, maxB = 0;
    static int[] A, B;
    static int[][] cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new int[N];
        
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            maxB = Math.max(maxB, B[i]);
        }
        maxB *= K;
        cache = new int[K+1][maxB + 1];
    }

    static int solve() {
        int ans = 0;
        for(int i = 1; i<=K; i++) {
            Arrays.fill(cache[i], -1);
        }
        cache[1][1] = 1;
        for(int i = 2; i<=K; i++) {
            for(int j = 1; j<=maxB; j++) {
                cache[i][j] = cache[i-1][j]>=0 ? cache[i-1][j] + j : -1;
                for(int k = 0; k<N; k++) {
                    if(j<B[k])  continue;
                    cache[i][j] = Math.max(cache[i][j], cache[i-1][j-B[k]]-A[k]);
                }
            }
        }
        for(int i = 0; i<=maxB; i++) {
            ans = Math.max(ans, cache[K][i]);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }

}
