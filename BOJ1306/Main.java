package BOJ1306;

import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N, M;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>((i,j)->(arr[j]-arr[i]));
        for(int i = 1; i<=Math.min(2*M-1,N); i++)
            pq.add(i);
        bw.write(arr[pq.peek()] + " ");
        for(int i = M+1; i<=N-M+1; i++) {
            pq.add(i+M-1);
            while(Math.abs(i-pq.peek())>=M)
                pq.poll();
            bw.write(arr[pq.peek()]+" ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}