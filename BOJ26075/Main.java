package BOJ26075;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> S, T;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = new ArrayList<>();
        T = new ArrayList<>();
        String s = br.readLine();
        String t = br.readLine();
        for(int i = 0; i<N+M; i++) {
            if(s.charAt(i)=='1')    S.add(i);
            if(t.charAt(i)=='1')    T.add(i);
        }
    }

    static long calc() {
        long dist = 0;
        for(int i = 0; i<M; i++) {
            dist += Math.abs(S.get(i)-T.get(i));
        }
        if(dist%2==0) {
            long x = dist>>1;
            return 2*x*x;
        } else {
            long x = dist>>1;
            return (x+1)*(x+1) + x*x;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(calc());
    }
}
