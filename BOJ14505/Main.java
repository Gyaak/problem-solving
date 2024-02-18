package BOJ14505;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] cache;
    static char[] S;

    static int Cache(int l, int r) {
        if(l>r)
            return 0;
        if(cache[l][r]==-1) {
            if(S[l]==S[r]) {
                cache[l][r] = Cache(l+1, r-1) + 1;
            } else {
                cache[l][r] = 0;
            }
            cache[l][r] += Cache(l+1, r) + Cache(l, r-1) - Cache(l+1, r-1);
            
        }
        return cache[l][r];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        cache = new int[S.length][S.length];
        for(int[] a : cache)
            Arrays.fill(a,-1);
        System.out.println(Cache(0, S.length-1));

    }
}
