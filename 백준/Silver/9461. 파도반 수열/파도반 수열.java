import java.io.*;
import java.util.*;

public class Main {
    static long[] P;
    public static void main(String[] args) throws IOException {
        solve();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            bw.write(P[N] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    
    private static void solve() {
        P = new long[101];
        P[1] = 1L;
        P[2] = 1L;
        P[3] = 1L;
        P[4] = 2L;
        P[5] = 2L;
        for(int i = 6; i<=100; i++) {
            P[i] = P[i-1] + P[i-5];
        }
    }
}