package BOJ11559;

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

    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static char[][] board = new char[12][6];
    static boolean[][] visited;

    static boolean isValid(int r, int c, char val) {
        if(r<0 || c<0 || r>=12 || c>=6)
            return false;
        if(visited[r][c])
            return false;
        if(board[r][c]!=val)
            return false;
        return true;
    }

    static boolean BFS(int r, int c) {
        Queue<Pair> q = new ArrayDeque<>();
        Queue<Pair> tmpQ = new ArrayDeque<>();
        q.add(new Pair(r, c));
        while(!q.isEmpty()) {
            int curR = q.peek().first;
            int curC = q.poll().second;
            if(visited[curR][curC])
                continue;
            tmpQ.add(new Pair(curR, curC));
            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];

                if(isValid(tmpR, tmpC, board[r][c])) {
                    q.add(new Pair(tmpR, tmpC));
                }
            }
            visited[curR][curC] = true;
        }
        if(tmpQ.size()>=4) {
            while(!tmpQ.isEmpty()) {
                int curR = tmpQ.peek().first;
                int curC = tmpQ.poll().second;
                board[curR][curC] = '.';
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean remove() {
        boolean flag = false;
        visited = new boolean[12][6];
        for(int i = 0; i<12; i++) {
            for(int j = 0; j<6; j++) {
                if(!visited[i][j] && board[i][j]!='.')
                    flag |= BFS(i,j);
            }
        }
        return flag;
    }

    static void move() {
        Queue<Character> q = new ArrayDeque<>();
        for(int i = 0; i<6; i++) {
            for(int j = 11; j>=0; j--)
                if(board[j][i]!='.') {
                    q.add(board[j][i]);
                    board[j][i] = '.';
                }
            int idx = 11;
            while(!q.isEmpty()) {
                board[idx--][i] = q.poll();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<12; i++) {
            String input = br.readLine();
            for(int j = 0; j<6; j++) {
                board[i][j] = input.charAt(j);
            }
        }
        int num = 0;
        while(remove()) {
            num++;
            move();
        }
        System.out.println(num);
    }
}