package BOJ15685;

import java.io.*;
import java.util.*;

public class Main {

    static final int BOARDSIZE = 101;

    static class DragonCurve {
        int x, y, d, g;

        DragonCurve(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }

    static int N;
    static int[] dx = {1,0,-1,0}, dy = {0,-1,0,1};
    static boolean[][] board = new boolean[BOARDSIZE][BOARDSIZE];
    static DragonCurve[] curves;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        curves = new DragonCurve[N];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curves[i] = new DragonCurve(x, y, d, g);
        }
    }

    static void drawCurve(DragonCurve c) {
        List<Integer> list = new ArrayList<>();

        list.add(c.d);
        for(int i = 0; i<c.g; i++) {
            for(int j = list.size()-1; j>=0; j--) {
                list.add((list.get(j)+1)%4);
            }
        }

        int curX = c.x;
        int curY = c.y;
        board[curX][curY] = true;
        for(int i : list) {
            curX += dx[i];
            curY += dy[i];
            board[curX][curY] = true;
        }
    }

    static int countSquare() {
        int num = 0;
        for(int i = 1; i<BOARDSIZE; i++) {
            for(int j = 1; j<BOARDSIZE; j++) {
                if(board[i-1][j-1]&&board[i-1][j]&&board[i][j-1]&&board[i][j])
                    num++;
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(DragonCurve c : curves)
            drawCurve(c);
        System.out.println(countSquare());
    }
}
