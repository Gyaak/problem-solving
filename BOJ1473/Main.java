package BOJ1473;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int stat, r, c, t;
        Node(int stat, int r, int c, int t) {
            this.stat = stat;
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    static int N, M;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] board;
    static int[][][] visited; // visited[stat][N][M]

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new int[1<<(N+M)][N][M];
        Arrays.stream(visited)
                .forEach(arr -> Arrays.stream(arr)
                    .forEach(r -> Arrays.fill(r, Integer.MAX_VALUE)));

        for(int i = 0; i<N; i++) {
            String s = br.readLine();
            for(int j = 0; j<M; j++) {
                board[i][j] = s.charAt(j)-'A';
            }
        }
        br.close();
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        return true;
    }

    static int rotate(int stat, int r, int c) {
        boolean flag = ((stat&(1<<(r+M)))>0)^((stat&(1<<(c)))>0);
        if(flag) {
            if(board[r][c]==2)      return 3;
            else if(board[r][c]==3) return 2;
            else                    return board[r][c];
        }
        return board[r][c];
    }

    static int BFS() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));
        for(int i = 0; i<(1<<(N+M)); i++)
            visited[i][0][0] = 0;
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int curR = curNode.r;
            int curC = curNode.c;
            int curStat = curNode.stat;
            int curVal = rotate(curStat, curR, curC);
            int curTime = curNode.t;
            int rotatedStat = (curStat^(1<<(curR+M)))^(1<<curC);

            if(visited[curStat][curR][curC]<curTime)
                continue;

            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];
                if(!isValid(tmpR, tmpC))
                    continue;
                
                // 위아래 방 탐색
                if(i%2==0) {
                    int tmpVal = rotate(curStat,tmpR,tmpC);
                    int rotatedTmpVal = rotate(rotatedStat, tmpR, tmpC);
                    if((curVal==0||curVal==2) && (tmpVal==0||tmpVal==2)) {
                        if(curTime+1<visited[curStat][tmpR][tmpC]) {
                            visited[curStat][tmpR][tmpC] = curTime+1;
                            q.add(new Node(curStat, tmpR, tmpC, curTime+1));
                        }
                    }
                    if((curVal==0||curVal==2) && (rotatedTmpVal==0||rotatedTmpVal==2)) {
                        if(curTime+2<visited[rotatedStat][tmpR][tmpC]) {
                            visited[rotatedStat][tmpR][tmpC] = curTime+2;
                            q.add(new Node(rotatedStat, tmpR, tmpC, curTime+2));
                        }
                    }
                }

                // 양옆 방 탐색
                if(i%2==1) {
                    int tmpVal = rotate(curStat,tmpR,tmpC);
                    int rotatedTmpVal = rotate(rotatedStat, tmpR, tmpC);
                    if((curVal==0||curVal==3) && (tmpVal==0||tmpVal==3)) {
                        if(curTime+1<visited[curStat][tmpR][tmpC]) {
                            visited[curStat][tmpR][tmpC] = curTime+1;
                            q.add(new Node(curStat, tmpR, tmpC, curTime+1));
                        }
                    }
                    if((curVal==0||curVal==3) && (rotatedTmpVal==0||rotatedTmpVal==3)) {
                        if(curTime+2<visited[rotatedStat][tmpR][tmpC]) {
                            visited[rotatedStat][tmpR][tmpC] = curTime+2;
                            q.add(new Node(rotatedStat, tmpR, tmpC, curTime+2));
                        }
                    }
                }
            }
        }
        int time = Integer.MAX_VALUE;
        for(int i = 0; i<(1<<(N+M)); i++)
            time = Math.min(time, visited[i][N-1][M-1]);
        return time==Integer.MAX_VALUE?-1:time;
    }

    public static void main(String[] args) throws IOException{
        setInput();
        System.out.println(BFS());
    }
}
