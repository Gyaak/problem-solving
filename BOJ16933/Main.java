package BOJ16933;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        /*
         * (r,c) : 현재 위치
         * k : 현재까지 벽을 부순 횟수
         * t = 0 : 낮 , t = 1 : 밤
         * d : 이동거리
         */
        int r, c, k, t, d; // 

        Node(int r, int c, int k, int t, int d) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.t = t;
            this.d = d;
        }
    }

    static int N, M, K;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] Map;
    static boolean[][][] visited;


    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++)
                Map[i][j] = input.charAt(j) - '0';
        }
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        return true;
    }

    static int BFS() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0, 1));

        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int curR = curNode.r;
            int curC = curNode.c;
            int curK = curNode.k;
            int curT = curNode.t;
            int curD = curNode.d;
            boolean flag = false;

            if(visited[curR][curC][curK])
                continue;
            if(curR==N-1 && curC==M-1)
                return curD;
                
            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];

                if(isValid(tmpR, tmpC)) {
                    if(Map[tmpR][tmpC]==0 && !visited[tmpR][tmpC][curK]) {
                        q.add(new Node(tmpR, tmpC, curK, (curT+1)%2, curD+1));
                    } else if(Map[tmpR][tmpC]==1&& curK<K && !visited[tmpR][tmpC][curK+1]) {
                        if(curT == 0) {
                            q.add(new Node(tmpR, tmpC, curK+1, 1, curD+1));
                        } else {
                            flag = true;
                        }
                    }
                }
            }
            
            if(flag)
                q.add(new Node(curR, curC, curK, (curT+1)%2, curD+1));
            else
                visited[curR][curC][curK] = true;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
