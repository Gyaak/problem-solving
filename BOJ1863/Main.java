package BOJ1863;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int num = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(-1);
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int y = Integer.parseInt(st.nextToken());
            if(y<=dq.peekLast()) {
                while(y<=dq.peekLast()) {
                    if(y<dq.pollLast())
                        num++;
                }
            }
            dq.add(y);
        }
        while(dq.pollLast()>0)
            num++;
        System.out.println(num);
        br.close();
    }
}
