package SWEA.SWEA1208;

import java.util.*;
import java.io.*;
 
class Solution
{
    private static int N;
    private static int[] height;
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int test_case = 1; test_case <= 10; test_case++) {
            setInput(br);
            bw.write("#" + test_case + " " + solve()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
     
    private static void setInput(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        height = new int[100];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<100; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }
    }
     
    private static int solve() {
        for(int i = 0; i<N; i++) {
            Arrays.sort(height);
            height[0]++;
            height[99]--;
        }
        Arrays.sort(height);
        return height[99] - height[0];
    }
}