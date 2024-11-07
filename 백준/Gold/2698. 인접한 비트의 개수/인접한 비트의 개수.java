//package BOJ2698;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] endWithZero, endWithOne;

    static void init() {
        endWithZero = new int[101][101];
        endWithOne = new int[101][101];
        endWithZero[1][0] = 1;
        endWithOne[1][0] = 1;
        for(int i = 2; i<101; i++) {
            for(int j = 0; j<i; j++) {
                endWithZero[i][j] = endWithZero[i-1][j] + endWithOne[i-1][j];
                endWithOne[i][j] = endWithZero[i-1][j] + (j>0?endWithOne[i-1][j-1]:0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            bw.write((endWithZero[n][k] +endWithOne[n][k]) + "\n");
        }
        bw.flush();
    }
}