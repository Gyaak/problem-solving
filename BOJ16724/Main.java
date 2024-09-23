package BOJ16724;

import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    // U D L R
    static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
    static int[][] map;
    
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i<N; i++) {
            String line = br.readLine();
            for(int j = 0; j<M; j++) {
                char c = line.charAt(j);
                switch (c) {
                    case 'U':
                        map[i][j] = 0;
                        break;
                    case 'D':
                        map[i][j] = 1;
                        break;
                    case 'L':
                        map[i][j] = 2;
                        break;
                    case 'R':
                        map[i][j] = 3;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    static int pairToInt(int r, int c) {
        return 1000 * r + c;
    }

    static int[] intToPair(int val) {
        int[] arr = new int[2];
        arr[0] = val / 1000;
        arr[1] = val % 1000;
        return arr;
    }
    
    static boolean DFS(int r, int c, int[][] visited, int num) {
        boolean hasSafeZone = false;
        int curR = r;
        int curC = c;
        while (visited[curR][curC]==0) {
            visited[curR][curC] = num;
            curR += dr[map[curR][curC]];
            curC += dc[map[curR][curC]];
        }
        if(visited[curR][curC]!=num) {
            hasSafeZone = true;
        }
        return hasSafeZone;
    }

    static int solve() {
        int cnt = 0, num = 1;
        int[][] visited = new int[N][M];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(visited[i][j]==0) {
                    cnt += DFS(i,j,visited,num++)?0:1;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
