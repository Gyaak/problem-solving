package BOJ1966;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Queue<Integer> q = new ArrayDeque<>();
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] priority = new int[N];
            int[] sortedPriority = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++) {
                q.add(i);
                priority[i] = Integer.parseInt(st.nextToken());
                sortedPriority[i] = priority[i];
            }
            Arrays.sort(sortedPriority);
            int cur = N-1;
            int num = 1;
            while(cur>=0) {
                while(sortedPriority[cur]!=priority[q.peek()]) {
                    q.add(q.poll());
                }
                if(q.peek()==M)
                    break;
                q.poll();
                num++;
                cur--;
            }
            ans.append(num+"\n");
        }
        System.out.print(ans);
    }
}
