package BOJ5525;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();
        int ans = 0;
        for(int i = 0; i<M; i++) {
            if(S.charAt(i)=='I') {
                int cur = i;
                int len = 0;
                while(cur<M-2 && S.charAt(cur+1)=='O' && S.charAt(cur+2)=='I') {
                    cur += 2;
                    len++;
                }
                if(len>=N)
                    ans += len-N+1;
                i = cur;
            }
        }
        System.out.println(ans);
    }
}
