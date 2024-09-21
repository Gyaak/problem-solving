package BOJ18185;

import java.io.*;
import java.util.*;

public class Main {
    static final int COST1 = 3, COST2 = 5, COST3 = 7;
    static int N;
    static int[] A, cost;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        cost = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        cost[1] = A[1] * COST1;
        cost[2] = Math.min(A[1],A[2]) * COST2 + Math.abs(A[1]-A[2]) * COST1;
    }

    static void solve() {
        for(int i = 3; i<=N; i++) {
            int localMin = Math.min(A[i-2],Math.min(A[i-1],A[i]));
            cost[i] = cost[i-3];
            if (localMin == A[i-2]) {
                int localMid = Math.min(A[i-1],A[i]);
                int localMax = Math.max(A[i-1],A[i]);
                cost[i] += localMin * COST3 + (localMid - localMin) * COST2 + (localMax - localMid) * COST1;
            } else if (localMin == A[i-1]) {
                int localMid = Math.min(A[i-2],A[i]);
                int localMax = Math.max(A[i-2],A[i]);
                cost[i] += localMin * COST3 + (localMid - localMin) * COST1 + (localMax - localMin) * COST1;
            } else {
                int localMid = Math.min(A[i-2],A[i-1]);
                int localMax = Math.max(A[i-2],A[i-1]);
                cost[i] += localMin * COST3 + (localMid - localMin) * COST2 + (localMax - localMid) * COST1;
            }
            cost[i] = Math.min(cost[i],cost[i-2] + Math.min(A[i],A[i-1]) * COST2 + Math.abs(A[i]-A[i-1]) * COST1);
            cost[i] = Math.min(cost[i],cost[i-1] + A[i] * COST1);
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        solve();
        System.out.println(cost[N]);
    }
}
