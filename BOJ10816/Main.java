package BOJ10816;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder("");
        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) {
                map.replace(num, map.get(num)+1);
            } else {
                map.put(num,1);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++) {
            int val = Integer.parseInt(st.nextToken());
            if(map.containsKey(val))    ans.append(map.get(val));
            else                        ans.append("0");
            if(i<M-1)   ans.append(" ");
        }
        System.out.println(ans);
    }
}
