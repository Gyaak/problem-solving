package SWEA.SWEA20728;

import java.io.*;
import java.util.*;

public class Solution {

    static int T, N, K;
    static int[] box;

    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        box = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box);
    }

    static int solve() {
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i+K<=N; i++) {
            diff = Math.min(diff, box[i+K-1]-box[i]);
        }
        return diff;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int i = 1; i<=T; i++) {
            setInput(br);
            bw.write("#"+i+" "+solve()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
