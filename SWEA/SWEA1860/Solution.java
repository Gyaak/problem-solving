package SWEA.SWEA1860;

import java.io.*;
import java.util.*;
 
class Solution
{
    static int N, M, K;
    static int[] customers;
    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        customers = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            customers[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(customers);
    }
     
    static boolean solve() {
        int lastAdd = 0;
        int num = 0;
        for(int i = 0; i<N; i++) {
            int m = (customers[i]-lastAdd)/M;
            lastAdd += m*M;
            num += m*K;
            if(num==0)  return false;
            else            num--;
        }
        return true;
    }
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++) {
            setInput(br);
            if(solve()) bw.write("#" + t + " Possible\n");
            else        bw.write("#" + t + " Impossible\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}