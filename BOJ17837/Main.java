package BOJ17837;

import java.io.*;
import java.util.*;

public class Main {

    static class Piece {
        int r, c, d, n;

        Piece(int r, int c, int d, int n) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.n = n;
        }
    }

    static int N, K;
    static int[] dr = {0,0,-1,1}, dc = {1,-1,0,0};
    static int[][] board;
    static Piece[] pieces;
    static Deque<Piece>[][] boardStat;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        pieces = new Piece[K];
        boardStat = new ArrayDeque[N][N];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                boardStat[i][j] = new ArrayDeque<>();
            }
        }


        for(int i = 0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            pieces[i] = new Piece(r, c, d, i);
            boardStat[r][c].add(pieces[i]);
        }
    }

    static Character isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N)
            return 'B';
        if(board[r][c]==0)
            return 'W';
        if(board[r][c]==1)
            return 'R';
        if(board[r][c]==2)
            return 'B';
        return ' ';
    }

    static boolean move(int pieceNum) {
        int curR = pieces[pieceNum].r;
        int curC = pieces[pieceNum].c;
        int curD = pieces[pieceNum].d;
        if(isValid(curR+dr[curD], curC+dc[curD])=='B') {
            if(curD%2==0)   curD++;
            else            curD--;
            pieces[pieceNum].d = curD;
        }
        int tmpR = curR + dr[curD];
        int tmpC = curC + dc[curD];
        if(isValid(tmpR, tmpC)=='W') {
            Deque<Piece> dq = new ArrayDeque<>();
            while(boardStat[curR][curC].peekLast().n != pieceNum) {
                dq.addLast(boardStat[curR][curC].pollLast());
                dq.peekLast().r = tmpR;
                dq.peekLast().c = tmpC;
            }
            dq.addLast(boardStat[curR][curC].pollLast());
            while(!dq.isEmpty())
                boardStat[tmpR][tmpC].add(dq.pollLast());
            pieces[pieceNum].r = tmpR;
            pieces[pieceNum].c = tmpC;
        } else if (isValid(tmpR, tmpC)=='R') {
            while(boardStat[curR][curC].peekLast().n != pieceNum) {
                boardStat[tmpR][tmpC].addLast(boardStat[curR][curC].pollLast());
                boardStat[tmpR][tmpC].peekLast().r = tmpR;
                boardStat[tmpR][tmpC].peekLast().c = tmpC;
            }
            boardStat[tmpR][tmpC].addLast(boardStat[curR][curC].pollLast());
            pieces[pieceNum].r = tmpR;
            pieces[pieceNum].c = tmpC;
        }
        if(boardStat[pieces[pieceNum].r][pieces[pieceNum].c].size()>=4)
            return true;
        return false;
    }

    static int solve() {
        int turn = 1;
        while(turn<=1000) {
            for(int i = 0; i<K; i++)
                if(move(i))
                    return turn;
            turn++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
