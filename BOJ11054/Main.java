package BOJ11054;

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
        int ans = 1;
        int[] inc = new int[N];
        int[] dec = new int[N];

        for(int i = 0; i<N; i++) {
            inc[i] = 1;
            for(int j = i-1; j>=0; j--) {
                if(A[i]>A[j]) {
                    inc[i] = Math.max(inc[i], inc[j] + 1);
                }
            }
        }
        for(int i = N-1; i>=0; i--) {
            dec[i] = 1;
            for(int j = i; j<N; j++) {
                if(A[i]>A[j]) {
                    dec[i] = Math.max(dec[i], dec[j] + 1);
                }
            }
        }
        for(int i = 0; i<N; i++) {
            ans = Math.max(ans, inc[i] + dec[i] - 1);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
