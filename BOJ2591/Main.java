package BOJ2591;

import java.io.*;
import java.util.*;

public class Main {

    static int answer;
    static char[] target;

    static void DFS(int cur) {
        if(cur==target.length) {
            answer += 1;
            return;
        }
        // 맨 앞자리가 0일 수 없음
        if(target[cur]=='0')
            return;
        DFS(cur+1);
        
        if(cur<target.length-1) {
            int val = 10*(target[cur]-'0') + target[cur+1]-'0';
            if(val<=34)
                DFS(cur+2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine().toCharArray();
        
        DFS(0);

        System.out.println(answer);
        br.close();
    }
}
