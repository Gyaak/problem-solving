package BOJ2276;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] pool;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        pool = new int[N][M];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                pool[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int coordinateToInt(int r, int c) {
        return 100*r + c;
    }

    static int[] intTocoordinate(int val) {
        int[] coordinate = new int[2];
        coordinate[0] = val/100;
        coordinate[1] = val%100;
        return coordinate;
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M) {
            return false;
        }
        return true;
    }

    static int BFS(int r, int c, boolean[][] visited, int height) {
        int sum = 1;
        boolean failed = false;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(coordinateToInt(r, c));
        visited[r][c] = true;

        while(!q.isEmpty()) {
            int[] curCoordinate = intTocoordinate(q.poll());

            for(int i = 0; i<4; i++) {
                int tmpR = curCoordinate[0] + dr[i];
                int tmpC = curCoordinate[1] + dc[i];

                if(!isValid(tmpR, tmpC)) {
                    failed = true;
                    continue;
                }

                if(!visited[tmpR][tmpC] && pool[tmpR][tmpC]<=height) {
                    q.add(coordinateToInt(tmpR, tmpC));
                    visited[tmpR][tmpC] = true;
                    sum++;
                }
            }
        }
        return failed?0:sum;
    }

    static int solve(int height) {
        int sum = 0;
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(pool[i][j]<=height && !visited[i][j]) {
                    sum += BFS(i, j, visited, height);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = 0;
        for(int i = 1; i<9; i++) {
            ans += solve(i);
        }
        System.out.println(ans);
    }
}
