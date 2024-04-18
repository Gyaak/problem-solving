package SWEA.SWEA217643;

import java.io.*;
import java.util.*;

public class Solution {

    static final int MAX = 19;
    static int firstBit = 0;
    static int[] cache = {
                            1, 3, 9, 27, 81,
        					243, 729, 2187, 6561, 19683,
        					59049, 177147, 531441, 1594323, 4782969,
                            14348907, 43046721, 129140163, 387420489, 1162261467
                        };

    static int solve(int n) {

        n = Math.abs(n);
        int selected = 0;

        for(int i = MAX; i>=0; i--) {
            if((cache[i]-1)/2<n) {
                n = Math.abs(cache[i]-n);
                selected |= (1<<i);
                firstBit = Math.max(firstBit,i);
            }
            if(n==0)    return selected;
        }
        return -1;
    }
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 1; i<=T; i++) {
			firstBit = 0;
            bw.write("#"+i+" ");

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if((x==0&&y==0)||(solve(x)+solve(y)==(1<<(firstBit+1))-1)) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}
