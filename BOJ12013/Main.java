package BOJ12013;

import java.io.*;

public class Main {

    static int N;
    static int[][] cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N][N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                cache[i][j] = -1;
            }
        }
        for(int i = 0; i<N; i++) {
            cache[i][i] = Integer.parseInt(br.readLine());
        }
    }

    // i <= j
    static int Cache(int i, int j) {
        if(cache[i][j] == -1) {
            cache[i][j] = 0;
            for(int k = i; k<j; k++) {
                if(Cache(i, k) != 0 && Cache(i, k)==Cache(k+1, j)) {
                    cache[i][j] = Math.max(cache[i][j], Cache(i, k) + 1);
                }
            }
        }
        return cache[i][j];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = 0;
        for(int i = 0; i<N; i++) {
            for(int j = i; j<N; j++) {
                ans = Math.max(ans,Cache(i, j));
            }
        }
        System.out.println(ans);
    }
}
