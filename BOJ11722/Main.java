package BOJ11722;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solve() {
        int len = 0;
        int[] cache = new int[N];
        cache[0] = 1;
        for(int i = 1; i<N; i++) {
            int tmp = 0;
            for(int j = 0; j<i; j++) {
                if(A[j]>A[i]) {
                    tmp = Math.max(tmp, cache[j]);
                }
            }
            cache[i] = tmp + 1;
        }
        for(int c : cache) {
            len = Math.max(len, c);
        }
        return len;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
