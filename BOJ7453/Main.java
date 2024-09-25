package BOJ7453;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, B, C, D;
    static int[] AB, CD;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        StringTokenizer st = new StringTokenizer("");
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void init() {
        AB = new int[N*N];
        CD = new int[N*N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                AB[N*i+j] = A[i] + B[j];
                CD[N*i+j] = C[i] + D[j];
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);
    }

    static int findLowerBound(int target) {
        int left = 0, right = N*N;
        while(left<right) {
            int mid = (left+right) >> 1;
            if(CD[mid]<target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int findUpperBound(int target) {
        int left = 0, right = N*N;
        while(left<right) {
            int mid = (left+right) >> 1;
            if(CD[mid]<=target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static long solve() {
        long result = 0;
        for(int i = 0; i<N*N; i++) {
            result += (long)(findUpperBound(-AB[i]) - findLowerBound(-AB[i]));
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        init();
        System.out.println(solve());
    }
}