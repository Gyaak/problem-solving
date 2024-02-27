package BOJ13975;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    static void setInput(BufferedReader br) throws IOException {
        pq.clear();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens())
            pq.add(Long.parseLong(st.nextToken()));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            long sum = 0;
            while(pq.size()>1) {
                long file1 = pq.poll();
                long file2 = pq.poll();
                sum += file1 + file2;
                pq.add(file1+file2);
            }
            bw.write(sum+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
