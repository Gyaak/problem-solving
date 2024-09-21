package BOJ2206;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c, dist, broken;

        Node(int r, int c, int dist, int broken) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.broken = broken;
        }
    }

    static int N, M;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static boolean[][] map;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for(int i = 0; i<N; i++) {
            String inputString = br.readLine();
            for(int j = 0; j<M; j++) {
                if(inputString.charAt(j)=='1') {
                    map[i][j] = true;
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
        Queue<Node> q = new ArrayDeque<>();
        // 해당 좌표를 벽을 부순상태/부수지않은상태로 각각 방문했는지 체크해야함
        boolean[][][] visited = new boolean[N][M][2];
        // 시작점도 포함해서 새야하므로 1부터 시작
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.r==N-1 && cur.c==M-1) {
                return cur.dist;
            }
            for(int i = 0; i<4; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[i];
                if(isValid(tmpR, tmpC)) {
                    // 벽을 만났을때 이전에 벽을 부순적이 없고 방문한 적이 없다면 부수고 방문
                    if(map[tmpR][tmpC] && cur.broken==0 && !visited[tmpR][tmpC][1]) {
                        q.add(new Node(tmpR, tmpC, cur.dist+1, 1));
                        visited[tmpR][tmpC][1] = true;
                    }
                    if(!map[tmpR][tmpC] && !visited[tmpR][tmpC][cur.broken]) {
                        q.add(new Node(tmpR, tmpC, cur.dist+1, cur.broken));
                        visited[tmpR][tmpC][cur.broken] = true;
                    }
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
