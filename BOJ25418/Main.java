package BOJ25418;

import java.io.*;
import java.util.*;

public class Main {
    static int A, K;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        while(K>=A) {
            int diff = K-A;
            ans = Math.min(ans, diff+cnt);
            if(K%2==1)  cnt++;
            K >>= 1;
            cnt++;
        }
        System.out.println(ans);
    }
}
