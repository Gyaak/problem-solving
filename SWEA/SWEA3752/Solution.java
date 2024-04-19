package SWEA.SWEA3752;

import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[] arr;
    static boolean[] visited;
    static int[] result = new int[10001];

    static void setInput(BufferedReader br) throws IOException {
        visited = new boolean[10001];
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void printAns(BufferedWriter bw, int t, int ans) throws IOException {
        bw.write("#" + t + " " + ans + "\n");
    }

    static int solve() {
        
        visited[0] = true;
        result[0] = 0;
        int len = 1;
        for(int i = 0; i<N; i++) {
            int tmpLen = len;
            for(int j = 0; j<len; j++) {
                int val = arr[i]+result[j];
                if(!visited[val]) {
                    result[tmpLen] = val;
                    visited[val] = true;
                    tmpLen++;
                }
            }
            len = tmpLen;
        }
        return len;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i<=T; i++) {
            setInput(br);
            printAns(bw, i, solve());
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
