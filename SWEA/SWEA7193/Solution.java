package SWEA.SWEA7193;

import java.util.*;
import java.io.*;
 
class Solution {
     
    static int solve(int N, String X) {
        if(N==2)    return 0;
        int res = 0;
        for(int i = 0; i<X.length(); i++) {
            res = (res+(X.charAt(i)-'0'))%(N-1);
        }
        return res;
    }
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
         
        StringTokenizer st;
        for(int i = 1; i<=T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String X = st.nextToken();
            bw.write("#"+i+" "+solve(N,X)+"\n");
        }
         
        bw.flush();
        bw.close();
        br.close();
    }
}