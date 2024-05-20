package BOJ9207;

import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int N;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] board = new int[5][9];
    static Pair ans;

    static void setInput(BufferedReader br) throws IOException {
        ans = new Pair(0, 0);
        for(int i = 0; i<5; i++) {
            String input = br.readLine();
            for(int j = 0; j<9; j++) {
                if(input.charAt(j)=='#')    board[i][j] = -1;
                if(input.charAt(j)=='.')    board[i][j] = 0;
                if(input.charAt(j)=='o') {
                    board[i][j] = 1;
                    ans.first++;
                }
            }
        }
    }

    static boolean isValid(int r, int c, boolean flag) {
        if(r<0 || c<0 || r>=5 || c>=9)
            return false;
        if(flag && board[r][c]!=1)
            return false;
        if(!flag && board[r][c]!=0)
            return false;
        return true;
    }

    static void DFS(int p, int n) {
        boolean isMoved = false;

        for(int i = 0; i<5; i++) {
            for(int j = 0; j<9; j++) {
                if(board[i][j]==1) {
                    for(int k = 0; k<4; k++) {
                        int tmpR1 = i + dr[k];
                        int tmpC1 = j + dc[k];
                        int tmpR2 = i + 2*dr[k];
                        int tmpC2 = j + 2*dc[k];
                        if(isValid(tmpR1, tmpC1, true) && isValid(tmpR2, tmpC2, false)) {
                            isMoved = true;
                            board[i][j] = 0;
                            board[tmpR1][tmpC1] = 0;
                            board[tmpR2][tmpC2] = 1;
                            DFS(p-1, n+1);
                            board[i][j] = 1;
                            board[tmpR1][tmpC1] = 1;
                            board[tmpR2][tmpC2] = 0;
                        }
                    }
                }
            }
        }

        if(!isMoved) {
            if(p<ans.first){
                ans.first = p;
                ans.second = n;
            }
            else if(p==ans.first && n<ans.second){
                ans.first = p;
                ans.second = n;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++) {
            setInput(br);
            DFS(ans.first, 0);
            bw.write(ans.first+" "+ans.second+"\n");
            if(i!=N-1)  br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
