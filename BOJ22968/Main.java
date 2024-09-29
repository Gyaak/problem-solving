package BOJ22968;

import java.io.*;
import java.util.Arrays;

public class Main {

    static int[] cache;

    static int Cache(int n) {
        if(cache[n] == -1) {
            cache[n] = cache[n-1] + cache[n-2] + 1;
        }
        return cache[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        cache = new int[44];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            int V = Integer.parseInt(br.readLine());
            for(int j = 0; j<44; j++) {
                if(V<Cache(j)) {
                    bw.write(String.valueOf(j-1));
                    break;
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
