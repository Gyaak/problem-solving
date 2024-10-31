package BOJ2096;

import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] max = new int[3];
        int[] min = new int[3];
        int[] tmp = new int[3];
        int[] A = new int[3];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[0] = Integer.parseInt(st.nextToken());
            A[1] = Integer.parseInt(st.nextToken());
            A[2] = Integer.parseInt(st.nextToken());
            
            tmp[0] = Math.max(max[0],max[1]) + A[0];
            tmp[1] = Math.max(max[0],Math.max(max[1],max[2])) + A[1];
            tmp[2] = Math.max(max[1],max[2]) + A[2];
            max[0] = tmp[0];
            max[1] = tmp[1];
            max[2] = tmp[2];

            tmp[0] = Math.min(min[0],min[1]) + A[0];
            tmp[1] = Math.min(min[0],Math.min(min[1],min[2])) + A[1];
            tmp[2] = Math.min(min[1],min[2]) + A[2];
            min[0] = tmp[0];
            min[1] = tmp[1];
            min[2] = tmp[2];
        }

        int M = Math.max(max[0],Math.max(max[1],max[2]));
        int m = Math.min(min[0],Math.min(min[1],min[2]));
        System.out.println(M + " " + m);
    }
}
