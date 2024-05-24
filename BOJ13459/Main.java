package BOJ13459;

import java.io.*;
import java.util.*;

public class Main {

    static class Pair<T> {
        T first, second;

        Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }
    }
    
    static class Marble {
        int r, c;

        Marble(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            Marble m = (Marble)obj;
            if(this.r==m.r && this.c==m.c)
                return true;
            return false;
        }
    }

    static int N, M;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static Marble redMarble, blueMarble;
    static Pair<Integer> exit;
    static boolean[][] board;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new boolean[N][M];
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++) {
                if(input.charAt(j)!='#')
                    board[i][j] = true;
                if(input.charAt(j)=='R')
                    redMarble = new Marble(i, j);
                if(input.charAt(j)=='B')
                    blueMarble = new Marble(i, j);
                if(input.charAt(j)=='O')
                    exit = new Pair<Integer>(i, j);
            }
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        if(!board[r][c])
            return false;
        return true;
    }

    static Pair<Marble> move(int dirc) {
        Marble tmpRed = new Marble(redMarble.r, redMarble.c);
        Marble tmpBlue = new Marble(blueMarble.r, blueMarble.c);
        Pair<Marble> result = new Pair<Main.Marble>(tmpRed, tmpBlue);
        boolean isSuccess = false;
        
        boolean redFirst = false;
        if(dirc==0 && redMarble.r>=blueMarble.r)    redFirst = true;
        if(dirc==1 && redMarble.c>=blueMarble.c)    redFirst = true;
        if(dirc==2 && redMarble.r<=blueMarble.r)    redFirst = true;
        if(dirc==3 && redMarble.c<=blueMarble.c)    redFirst = true;

        if(redFirst) {
            while(isValid(tmpRed.r+dr[dirc], tmpRed.c+dc[dirc])) {
                tmpRed.r += dr[dirc];
                tmpRed.c += dc[dirc];

                if(tmpRed.r==exit.first && tmpRed.c==exit.second) {
                    isSuccess = true;
                    tmpRed.r = 777;
                    tmpRed.c= 777;
                    break;
                }
            }
            while(isValid(tmpBlue.r+dr[dirc], tmpBlue.c+dc[dirc])) {
                tmpBlue.r += dr[dirc];
                tmpBlue.c += dc[dirc];

                if(tmpRed.equals(tmpBlue)) {
                    tmpBlue.r -= dr[dirc];
                    tmpBlue.c -= dc[dirc];
                    break;
                }

                if(tmpBlue.r==exit.first && tmpBlue.c==exit.second) {
                    tmpRed.r = -1;
                    return result;
                }
            }
        } else {
            while(isValid(tmpBlue.r+dr[dirc], tmpBlue.c+dc[dirc])) {
                tmpBlue.r += dr[dirc];
                tmpBlue.c += dc[dirc];

                if(tmpBlue.r==exit.first && tmpBlue.c==exit.second) {
                    tmpRed.r = -1;
                    return result;
                }
            }
            while(isValid(tmpRed.r+dr[dirc], tmpRed.c+dc[dirc])) {
                tmpRed.r += dr[dirc];
                tmpRed.c += dc[dirc];
                
                if(tmpRed.equals(tmpBlue)) {
                    tmpRed.r -= dr[dirc];
                    tmpRed.c -= dc[dirc];
                    break;
                }

                if(tmpRed.r==exit.first && tmpRed.c==exit.second) {
                    isSuccess = true;
                    tmpRed.r = 777;
                    tmpRed.c = 777;
                    break;
                }
            }
        }
        return result;
    }

    static boolean DFS(int depth) {

        if(depth==10)
            return false;

        Marble curRed = redMarble;
        Marble curBlue = blueMarble;
        for(int i = 0; i<4; i++) {
            Pair<Marble> marbles = move(i);
            if(marbles.first.r==777) {
                return true;
            }
            if(marbles.first.r!=-1) {
                if(!redMarble.equals(marbles.first) || !blueMarble.equals(marbles.second)) {
                    redMarble = marbles.first;
                    blueMarble = marbles.second;
                    if(DFS(depth+1))    return true;
                    redMarble = curRed;
                    blueMarble = curBlue;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(DFS(0)?1:0);
    }
}
