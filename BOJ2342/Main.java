package BOJ2342;

import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    // cache[i][j] : 왼발은 i 오른발은 j 위치에 있을때 최소 드는 힘
    static int[][] cache = new int[5][5];

    static void calc(int target) {
        int[][] tmp = new int[5][5];
        for(int[] t : tmp)
            Arrays.fill(t,MAX);
        
        for(int i = 0; i<5; i++) {
            for(int j = 0; j<5; j++) {
                if(cache[i][j]!=MAX) {
                    int cost;

                    // 왼발을 target위치로 옮겼을때 드는 힘 갱신
                    if(i==target)                       cost = 1;
                    else if(i==0)                       cost = 2;
                    else if(Math.abs(i-target)%2==1)    cost = 3;
                    else                                cost = 4;
                    tmp[target][j] = Math.min(tmp[target][j], cache[i][j]+cost);

                    // 오른발을 target위치로 옮겼을때 드는 힘 갱신
                    if(j==target)                       cost = 1;
                    else if(j==0)                       cost = 2;
                    else if(Math.abs(j-target)%2==1)    cost = 3;
                    else                                cost = 4;
                    tmp[i][target] = Math.min(tmp[i][target], cache[i][j]+cost);
                }
            }
        }
        cache = tmp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int[] c : cache)
            Arrays.fill(c,MAX);
        cache[0][0] = 0;

        while(st.hasMoreTokens()) {
            int target = Integer.parseInt(st.nextToken());
            if(target==0)  break;
            calc(target);
        }

        int ans = MAX;
        for(int i = 0; i<5; i++) {
            for(int j = 0; j<5; j++) {
                ans = Math.min(ans, cache[i][j]);
            }
        }

        System.out.println(ans);
    }
}
