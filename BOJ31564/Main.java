package BOJ31564;

import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int r, c, d;
        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int N, M, K;
    static int[] dr = {0,1,1,0,-1,-1};
    static int[][] dc = {{1,0,-1,-1,-1,0},{1,1,0,-1,0,1}};
    static boolean[][] obstacles;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        obstacles = new boolean[N][M];

        for(int i = 0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            obstacles[r][c] = true;
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        return true;
    }

    static int BFS() {
        boolean[][] visited = new boolean[N][M];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,0));
        visited[0][0] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.r==N-1 && cur.c==M-1) {
                return cur.d;
            }
            for(int i = 0; i<6; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[cur.r%2][i];
                if(
                    isValid(tmpR, tmpC) &&
                    !obstacles[tmpR][tmpC] &&
                    !visited[tmpR][tmpC]
                    ) {
                    q.add(new Node(tmpR,tmpC,cur.d+1));
                    visited[tmpR][tmpC] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
