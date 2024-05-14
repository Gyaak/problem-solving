package BOJ18405;

import java.io.*;
import java.util.*;

public class Main {

    static class Virus {
        int r, c, num, time;
        Virus(int r, int c, int num, int time) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.time = time;
        }
    }

    static int N, K, S, X, Y;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] board;
    static Queue<Virus> virusQueue = new ArrayDeque<>();

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Virus> virusList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]!=0)
                    virusList.add(new Virus(i, j, board[i][j], 0));
            }
        }
        Collections.sort(virusList, (i,j)->(i.num-j.num));
        virusQueue.addAll(virusList);

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N)
            return false;
        if(board[r][c]!=0)
            return false;
        return true;
    }

    static void BFS() {
        while(!virusQueue.isEmpty()) {
            Virus cur = virusQueue.poll();
            if(cur.time==S) break;

            for(int i = 0; i<4; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[i];
                if(isValid(tmpR, tmpC)) {
                    board[tmpR][tmpC] = cur.num;
                    virusQueue.add(new Virus(tmpR, tmpC, cur.num, cur.time+1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        BFS();
        System.out.println(board[X][Y]);
    }
}
