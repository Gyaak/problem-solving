package BOJ1823;

import java.io.*;

public class Main {
    static int N;
    static int[] v;
    static int[][] cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new int[N];
        cache = new int[N][N];
        for(int i = 0; i<N; i++) {
            v[i] = Integer.parseInt(br.readLine());
        }
        br.close();
    }

    static int Cache(int l, int r) {
        if(l>r)
            return 0;
        if(cache[l][r]==0) {
            int num = N-(r-l);
            cache[l][r] = Math.max(Cache(l+1, r)+num*v[l],Cache(l, r-1)+num*v[r]);
        }
        return cache[l][r];
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(Cache(0, N-1));
    }
}
