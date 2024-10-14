package BOJ1351;

import java.io.*;
import java.util.*;

public class Main {
    static long N, P, Q;
    static Map<Long, Long> map = new HashMap<>();

    static long getSeq(long n) {
        if(!map.containsKey(n)) {
            map.put(n, getSeq(n/P) + getSeq(n/Q));
        }
        return map.get(n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        map.put(0L, 1L);
        System.out.println(getSeq(N));
    }
}