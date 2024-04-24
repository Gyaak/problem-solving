package SWEA.SWEA2819;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Solution {

    static int answer;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] grid = new int[4][4];
    static boolean[] visited = new boolean[10000000];

    static void setInput(BufferedReader br) throws IOException {

        answer = 0;
        Arrays.fill(visited, false);

        for(int i = 0; i<4; i++) {
            String inputString = br.readLine();
            grid[i][0] = inputString.charAt(0)-'0';
            grid[i][1] = inputString.charAt(2)-'0';
            grid[i][2] = inputString.charAt(4)-'0';
            grid[i][3] = inputString.charAt(6)-'0';
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>3 || c>3)
            return false;
        return true;
    }

    static void DFS(int r, int c, int num, int len) {
        // 7자리가 되면 탐색 종료
        if(len==7) {
            answer += visited[num]?0:1;
            visited[num] = true;
            return;
        }
        for(int i = 0; i<4; i++) {
            int curR = r + dr[i];
            int curC = c + dc[i];
            if(isValid(curR, curC)) {
                DFS(curR, curC, 10*num+grid[curR][curC], len+1);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++) {
            setInput(br);
            for(int i = 0; i<4; i++) {
                for(int j = 0; j<4; j++) {
                    DFS(i, j, grid[i][j], 1);
                }
            }
            bw.write("#"+t+" "+answer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
