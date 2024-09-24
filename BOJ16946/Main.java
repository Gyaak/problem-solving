package BOJ16946;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] map, ans;
    static boolean[][] visited, visitedWall;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];
        visited = new boolean[N][M];
        visitedWall = new boolean[N][M];
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M) {
            return false;
        }
        return true;
    }

    static void BFS(int r, int c) {
        int num = 1;
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> walls = new ArrayList<>();
        
        q.add(1000 * r + c);
        visited[r][c] = true;
        while(!q.isEmpty()) {
            int curR = q.peek() / 1000;
            int curC = q.poll() % 1000;
            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];
                if(isValid(tmpR, tmpC) && !visited[tmpR][tmpC] && !visitedWall[tmpR][tmpC]) {
                    if(map[tmpR][tmpC]==0) {
                        q.add(1000 * tmpR + tmpC);
                        visited[tmpR][tmpC] = true;
                        num++;
                    } else {
                        walls.add(1000 * tmpR + tmpC);
                        visitedWall[tmpR][tmpC] = true;
                    }
                }
            }
        }

        for(int w : walls) {
            int wallR = w / 1000;
            int wallC = w % 1000;
            ans[wallR][wallC] += num;
            visitedWall[wallR][wallC] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(map[i][j]==0 && !visited[i][j]) {
                    BFS(i, j);
                } 
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(map[i][j]==1) {
                    ans[i][j]++;
                }
                sb.append(ans[i][j]%10);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}