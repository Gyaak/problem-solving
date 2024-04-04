package BOJ16948;

import java.io.*;
import java.util.*;

public class Main {

    static int solve(int r, int c) {
        if(r%2==1)  return -1;
        int cnt = r/2;
        int diffC = Math.abs(c-cnt);
        if(diffC%2==1)  return -1;
        if(c>cnt)   return cnt + diffC/2;
        return cnt + Math.max(diffC/2-cnt,0);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r0 = Integer.parseInt(st.nextToken());
        int c0 = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        r = Math.abs(r-r0);
        c = Math.abs(c-c0);
        System.out.println(solve(r, c));
    }
}
