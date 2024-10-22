package BOJ15673;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] interests, leftMax, leftMin, rightMax, rightMin, leftMaxCache, leftMinCache, rightMaxCache, rightMinCache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        interests = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            interests[i] = Integer.parseInt(st.nextToken());
        }
        leftMax = new long[N];
        leftMin = new long[N];
        rightMax = new long[N];
        rightMin = new long[N];
        leftMaxCache = new long[N];
        leftMinCache = new long[N];
        rightMaxCache = new long[N];
        rightMinCache = new long[N];
    }

    static void calcMaxMin() {
        leftMax[0] = interests[0];
        leftMin[0] = interests[0];
        for(int i = 1; i<N; i++) {
            leftMax[i] = interests[i] + Math.max(leftMax[i-1],0);
            leftMin[i] = interests[i] + Math.min(leftMin[i-1],0);
        }
        rightMax[N-1] = interests[N-1];
        rightMin[N-1] = interests[N-1];
        for(int i = N-2; i>=0; i--) {
            rightMax[i] = interests[i] + Math.max(rightMax[i+1],0);
            rightMin[i] = interests[i] + Math.min(rightMin[i+1],0);
        }
    }

    static void calcCache() {
        leftMaxCache[0] = leftMax[0];
        leftMinCache[0] = leftMin[0];
        for(int i = 1; i<N; i++) {
            leftMaxCache[i] = Math.max(leftMax[i], leftMaxCache[i-1]);
            leftMinCache[i] = Math.min(leftMin[i], leftMinCache[i-1]);
        }
        rightMaxCache[N-1] = rightMax[N-1];
        rightMinCache[N-1] = rightMin[N-1];
        for(int i = N-2; i>=0; i--) {
            rightMaxCache[i] = Math.max(rightMax[i], rightMaxCache[i+1]);
            rightMinCache[i] = Math.min(rightMin[i], rightMinCache[i+1]);
        }
    }

    static long solve() {
        long result = Long.MIN_VALUE;
        for(int i = 0; i<N-1; i++) {
            result = Math.max(result, leftMaxCache[i]*rightMaxCache[i+1]);
            result = Math.max(result, leftMinCache[i]*rightMinCache[i+1]);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        calcMaxMin();
        calcCache();
        System.out.println(solve());
    }
}
