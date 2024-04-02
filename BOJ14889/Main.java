package BOJ14889;

import java.io.*;
import java.util.*;

public class Main {
    static int N, diff = Integer.MAX_VALUE;
    static int[][] S;
    static boolean[] selected, ansS;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        selected = new boolean[N];
        ansS = new boolean[N];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void DFS(int idx, int num) {
        if(num==N/2) {
            int val = 0;
            for(int i = 0; i<N; i++) {
                for(int j = 0; j<N; j++) {
                    if(selected[i]&selected[j]) val += S[i][j];
                    if(!selected[i]&!selected[j]) val -= S[i][j];
                }
            }
            diff = Math.min(diff, Math.abs(val));
            return;
        }
        for(int i = idx; i<N; i++) {
            selected[i] = true;
            DFS(i+1, num+1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        DFS(0,0);
        System.out.println(diff);
    }
}
