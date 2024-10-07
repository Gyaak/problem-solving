package BOJ3079;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long[] T;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        T = new long[N];
        for(int i = 0; i<N; i++) {
            T[i] = Long.parseLong(br.readLine());
        }
    }

    static long calcEntry(long time) {
        long num = 0;
        for(long t : T) {
            num += time / t;
            if(num >= M) {
                break;
            }
        }
        return num;
    }

    static long BinarySearch() {
        long st = 0;
        long ed = Long.MAX_VALUE >> 1;
        while(st < ed) {
            long mid = (st + ed) >> 1;
            if(calcEntry(mid) < M) {
                st = mid + 1;
            } else {
                ed = mid;
            }
        }
        return st;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BinarySearch());
    }
}
