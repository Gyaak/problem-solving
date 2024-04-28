package SWEA.SWEA5215;

import java.io.*;
import java.util.*;

class Solution {
    
    static int N, L;
    static int[] score, calorie;
    
    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        score = new int[N];
        calorie = new int[N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
            calorie[i] = Integer.parseInt(st.nextToken());
        }
    }
    
    static int solve(int selected) {
        int totalScore = 0;
        int totalCalorie = 0;
        
        for(int i = 0; i<N; i++) {
            if((selected & (1<<i)) > 0) {
                totalScore += score[i];
                totalCalorie += calorie[i];
            }
        }
        
        if(totalCalorie<=L)
            return totalScore;
        else
            return -1;
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i<=T; i++) {
            setInput(br);
            int ans = 0;
            for(int selected = 0; selected<(1<<N); selected++) {
                ans = Math.max(ans, solve(selected));
            }
            bw.write("#"+i+" "+ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}