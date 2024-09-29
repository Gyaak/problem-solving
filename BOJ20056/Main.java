package BOJ20056;

import java.io.*;
import java.util.*;

public class Main {

    static class FireBall {
        int r, c, m, s, d;
        int moveCnt;

        FireBall(int r, int c, int m, int s, int d, int moveCnt) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.moveCnt = moveCnt;
        }
    }

    static class Cell {
        int r, c;
        int firstFireBallDirc;
        int fireBallNum, dircSum, massSum, speedSum, moveCnt;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.firstFireBallDirc = 0;
            this.fireBallNum = 0;
            this.dircSum = 0;
            this.massSum = 0;
            this.speedSum = 0;
            this.moveCnt = 0;
        }

        void addFireBall(FireBall fireBall) {
            if(fireBallNum == 0 ) {
                firstFireBallDirc = fireBall.d;
            }
            if((fireBall.d & 1) == 0) {
                dircSum |= 2;
            } else {
                dircSum |= 1;
            }
            massSum += fireBall.m;
            speedSum += fireBall.s;
            moveCnt = fireBall.moveCnt + 1;
            fireBallNum++;
        }

        void divideFireBalls() {
            if(fireBallNum > 1) {
                int mass = massSum / 5;
                if(mass > 0) {
                    int speed = speedSum / fireBallNum;
                    int dirc = dircSum == 3 ? 1 : 0;
                    for(int i = 0; i<4; i++) {
                        fireBalls.add(new FireBall(r, c, mass, speed, dirc+2*i, moveCnt));
                    }
                }
            } else if (fireBallNum == 1) {
                fireBalls.add(new FireBall(r, c, massSum, speedSum, firstFireBallDirc, moveCnt));
            }
            clear();
        }

        private void clear() {
            this.firstFireBallDirc = 0;
            this.fireBallNum = 0;
            this.dircSum = 0;
            this.massSum = 0;
            this.speedSum = 0;
            this.moveCnt = 0;
        }
    }
    
    static int N, M, K;
    static int[] dr = {-1,-1,0,1,1,1,0,-1}, dc = {0,1,1,1,0,-1,-1,-1};
    static Cell[][] map;
    static Queue<FireBall> fireBalls;
    static Queue<Cell> cells;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Cell[N][N];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                map[i][j] = new Cell(i, j);
            }
        }
        fireBalls = new ArrayDeque<>();
        cells = new ArrayDeque<>();
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(r, c, m, s, d, 0));
        }
    }

    static void moveFireBalls() {
        while(!fireBalls.isEmpty()) {
            FireBall cur = fireBalls.poll();
            cur.r = (cur.r + dr[cur.d] * (cur.s % N) + N) % N;
            cur.c = (cur.c + dc[cur.d] * (cur.s % N) + N) % N;
            map[cur.r][cur.c].addFireBall(cur);
            cells.add(map[cur.r][cur.c]);
        }
    }

    static void divideFireBalls() {
        while(!cells.isEmpty()) {
            Cell cur = cells.poll();
            if(cur.fireBallNum > 0) {
                cur.divideFireBalls();
            }
        }
    }

    static int solve() {
        int ans = 0;
        for(int i = 0; i<K; i++) {
            moveFireBalls();
            divideFireBalls();
        }
        while(!fireBalls.isEmpty()) {
            ans += fireBalls.poll().m;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }

}
