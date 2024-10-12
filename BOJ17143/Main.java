package BOJ17143;

import java.io.*;
import java.util.*;

public class Main {

    static class Shark {
        int num, r, c, s, d, z;
        boolean isLive;
        Shark(int num, int r, int c, int s, int d, int z) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.isLive = true;
        }
    }

    static int R, C, M;
    static int[][] map;
    static int[] dr = {-1,1,0,0}, dc = {0,0,1,-1};
    static Shark[] sharks;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        sharks = new Shark[M+1];
        sharks[0] = new Shark(-1, -1, -1, -1, -1, -1);
        sharks[0].isLive = false;
        for(int i = 1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(i, r, c, s, d, z);
            map[r][c] = i;
        }
    }

    static boolean isValid(int r, int c) {
        if(r < 0 || c < 0 || r >= R || c >= C) {
            return false;
        }
        return true;
    }

    static void move(Shark shark) {
        int r = shark.r + shark.s * dr[shark.d];
        int c = shark.c + shark.s * dc[shark.d];
        while(!isValid(r, c)) {
            if(r < 0) {
                r = -r;
                shark.d = 1;
            }
            if(r >= R) {
                r = 2 * (R - 1) - r;
                shark.d = 0;
            }
            if(c < 0) {
                c = -c;
                shark.d = 2;
            }
            if(c >= C) {
                c = 2 * (C - 1) - c;
                shark.d = 3;
            }
        }
        shark.r = r;
        shark.c = c;
        if(map[r][c] > 0) {
            int num = map[r][c];
            if(shark.z < sharks[num].z) {
                shark.isLive = false;
            } else {
                map[r][c] = shark.num;
                sharks[num].isLive = false;
            }
        } else {
            map[r][c] = shark.num;
        }
    }

    static int catchShark(int pos) {
        for(int i = 0; i<R; i++) {
            if(map[i][pos] > 0) {
                int num = map[i][pos];
                sharks[num].isLive = false;
                map[i][pos] = 0;
                return sharks[num].z;
            }
        }
        return 0;
    }

    static void clearMap() {
        for(int i = 0; i<R; i++) {
            Arrays.fill(map[i], 0);
        }
    }

    static int solve() {
        int sum = 0;
        for(int i = 0; i<C; i++) {
            sum += catchShark(i);
            clearMap();
            for(Shark shark : sharks) {
                if(shark.isLive) {
                    move(shark);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
