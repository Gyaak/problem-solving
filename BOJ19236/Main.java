package BOJ19236;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Fish {
        int r, c, d;
        Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int ans = 0;
    static int[] dr = {-1,-1,0,1,1,1,0,-1}, dc = {0,-1,-1,-1,0,1,1,1};
    static int[][][] map = new int[16][4][4];
    static Fish[][] fishes = new Fish[16][17];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;
                map[0][i][j] = a;
                fishes[0][a] = new Fish(i, j, b);
            }
        }
        for(int i = 1; i<16; i++) {
            for(int j = 0; j<17; j++) {
                fishes[i][j] = new Fish(-1, -1, -1);
            }
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>3 || c>3) {
            return false;
        }
        return true;
    }

    static void moveFish(int idx) {
        for(int i = 0; i<17; i++) {
            fishes[idx][i].r = -1;
            fishes[idx][i].c = -1;
            fishes[idx][i].d = -1;
        }
        for(int i = 0; i<4; i++) {
            for(int j = 0; j<4; j++) {
                map[idx][i][j] = map[idx-1][i][j];
                if(map[idx][i][j] == -1) {
                    continue;
                }
                fishes[idx][map[idx][i][j]].r = i;
                fishes[idx][map[idx][i][j]].c = j;
                fishes[idx][map[idx][i][j]].d = fishes[idx-1][map[idx][i][j]].d;
            }
        }
        for(int i = 1; i<=16; i++) {
            for(int j = 0; j<8; j++) {
                if(fishes[idx][i].r == -1 || fishes[idx][i].c == -1) {
                    continue;
                }
                int tmpR = fishes[idx][i].r + dr[fishes[idx][i].d];
                int tmpC = fishes[idx][i].c + dc[fishes[idx][i].d];
                if(isValid(tmpR, tmpC)) {
                    int n = map[idx][tmpR][tmpC];
                    if(n > 0) {
                        map[idx][tmpR][tmpC] = i;
                        fishes[idx][n].r = fishes[idx][i].r;
                        fishes[idx][n].c = fishes[idx][i].c;
                        map[idx][fishes[idx][i].r][fishes[idx][i].c] = n;
                        fishes[idx][i].r = tmpR;
                        fishes[idx][i].c = tmpC;
                        break;
                    }
                    if(n == -1) {
                        map[idx][fishes[idx][i].r][fishes[idx][i].c] = -1;
                        map[idx][tmpR][tmpC] = i;
                        fishes[idx][i].r = tmpR;
                        fishes[idx][i].c = tmpC;
                        break;
                    }
                }
                fishes[idx][i].d = (fishes[idx][i].d + 1) % 8;
            }
        }
    }

    static void DFS(int depth, int sum) {
        ans = Math.max(ans, sum);
        if(depth == 16) {
            return;
        }
        moveFish(depth);

        int r = fishes[depth][0].r;
        int c = fishes[depth][0].c;
        int d = fishes[depth][0].d;

        int tmpR = r;
        int tmpC = c;
        while(true) {
            tmpR = tmpR + dr[d];
            tmpC = tmpC + dc[d];
            if(!isValid(tmpR, tmpC)) {
                break;
            }

            int num = map[depth][tmpR][tmpC];
            if(num < 1) {
                continue;
            }

            map[depth][r][c] = -1;
            map[depth][tmpR][tmpC] = 0;
            fishes[depth][0].d = fishes[depth][num].d;

            DFS(depth+1, sum + num);
            
            map[depth][r][c] = 0;
            map[depth][tmpR][tmpC] = num;
            fishes[depth][0].d = d;
        }
        
    }

    public static void main(String[] args) throws IOException {
        setInput();
        ans = map[0][0][0];
        map[0][0][0] = 0;
        fishes[0][0] = new Fish(0, 0, fishes[0][ans].d);
        DFS(1, ans);
        System.out.println(ans);
    }
}
