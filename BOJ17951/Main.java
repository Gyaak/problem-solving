package BOJ17951;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] score;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int findGroupNum(int s) {
        int groupNum = 0;
        int sum = 0;
        for(int i = 0; i<N; i++) {
            sum += score[i];
            if(sum>=s) {
                groupNum++;
                sum = 0;
            }
        }
        return groupNum;
    }

    static int BinarySearch() {
        int left = 0;
        int right = 20 * N + 1;
        while(left<right) {
            int mid = (left + right) >> 1;
            if(findGroupNum(mid)<K) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BinarySearch());
    }
}
