package BOJ13398;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] seq;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(calc());
    }

    private static int calc() {
        int ans = seq[0];
        int[] cache = new int[N];
        int[] reverseCache = new int[N];
        cache[0] = seq[0];
        for(int i = 1; i<N; i++) {
            cache[i] = Math.max(cache[i-1],0) + seq[i];
            ans = Math.max(ans, cache[i]);
        }

        reverseCache[N-1] = seq[N-1];
        for(int i = N-2; i>=0; i--) {
            reverseCache[i] = Math.max(reverseCache[i+1],0) + seq[i];
            ans = Math.max(ans, reverseCache[i]);
        }

        for(int i = 1; i<N-1; i++) {
            ans = Math.max(ans, cache[i-1]+reverseCache[i+1]);
        }

        return ans;
    }
}
