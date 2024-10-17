package BOJ2015;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Integer.parseInt(st.nextToken());
        Map<Long, Long> sumMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        long ans = 0;
        long[] cumulative = new long[N+1];
        sumMap.put(0L, 1L);
        for(int i = 1; i<=N; i++) {
            long A = Long.parseLong(st.nextToken());
            cumulative[i] = A + cumulative[i-1]; 
            if(sumMap.containsKey(cumulative[i]- K)) {
                ans += sumMap.get(cumulative[i]- K);
            }
            long cnt = 1;
            if(sumMap.containsKey(cumulative[i])) {
                cnt += sumMap.get(cumulative[i]);
            }
            sumMap.put(cumulative[i], cnt);
        }
        System.out.println(ans);
    }
}
