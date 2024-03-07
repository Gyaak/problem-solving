package BOJ2075;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] curIdx = new int[N];
        Arrays.fill(curIdx, N-1);
        int[][] map = new int[N][N];
        for(int i = 0; i<N; i++) {
            String[] inpuStrings = br.readLine().split(" ");
            for(int j = 0; j<N; j++)
                map[i][j] = Integer.parseInt(inpuStrings[j]);
        }
        br.close();

        PriorityQueue<Integer> pq = new PriorityQueue<>((i,j)->(map[curIdx[j]][j]-map[curIdx[i]][i]));
        for(int i = 0; i<N; i++)
            pq.add(i);

        int ans = 0;
        for(int i = 0; i<N; i++) {
            int tmpIdx = pq.poll();
            ans = map[curIdx[tmpIdx]][tmpIdx];
            curIdx[tmpIdx]--;
            if(curIdx[tmpIdx]>-1)
                pq.add(tmpIdx);
        }

        System.out.println(ans);
    }
}
