package BOJ1015;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        Integer[] B = new Integer[N];
        Integer[] P = new Integer[N];
        for(int i = 0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = i;
            P[i] = i; 
        }
        Arrays.sort(B, (i,j)->A[i]==A[j]?(i-j):(A[i]-A[j]));
        Arrays.sort(P, (i,j)->B[i]-B[j]);

        for(int i = 0; i<N; i++)
            bw.write(P[i]+" ");
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}