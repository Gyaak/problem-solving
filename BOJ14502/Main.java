package BOJ14502;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans = 0;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> virus, emptyList;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        emptyList = new ArrayList<>();
        virus = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    emptyList.add(100 * i + j);
                }
                if(map[i][j] == 2) {
                    virus.add(100 * i + j);
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M) {
            return false;
        }
        return true;
    }

    static int BFS() {
        int cnt = emptyList.size() - 3;
        for(boolean[] v : visited) {
            Arrays.fill(v, false);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int v : virus) {
            q.add(v);
            visited[v/100][v%100] = true;
        }
        while(!q.isEmpty()) {
            int curR = q.peek() / 100;
            int curC = q.poll() % 100;
            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];
                if(isValid(tmpR, tmpC) && !visited[tmpR][tmpC] && map[tmpR][tmpC] == 0) {
                    q.add(100 * tmpR + tmpC);
                    visited[tmpR][tmpC] = true;
                    cnt--;
                }
            }
        }
        return cnt;
    }

    static void DFS(int depth, int idx, int[] wall) {
        if(depth == 3) {
            for(int i : wall) {
                int r = i / 100;
                int c = i % 100;
                map[r][c] = 1;
            }
            ans = Math.max(ans, BFS());
            for(int i : wall) {
                int r = i / 100;
                int c = i % 100;
                map[r][c] = 0;
            }
        } else {
            for(int i = idx; i<emptyList.size(); i++) {
                wall[depth] = emptyList.get(i);
                DFS(depth + 1, i + 1, wall);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        DFS(0,0,new int[3]);
        System.out.println(ans);
    }
}