package BOJ30238;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] arr;
    static void setInput(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static long solve() {
        if(N == 1) {
            return Math.max(arr[0],0);
        }
        long sum = 0;
        if(arr[0]>=0) {
            for(long a : arr) {
                sum += Math.max(a,0);
            }
        } else {
            sum = Math.max(arr[0]+arr[1],0);
            for(int i = 2; i<N; i++) {
                sum += Math.max(arr[i],0);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            bw.write(solve() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}