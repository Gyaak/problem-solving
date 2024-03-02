package BOJ1600;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c, horseMoveCount, totalMoveCount;

        Node(int r, int c, int horseMoveCount, int totalMoveCount) {
            this.r = r;
            this.c = c;
            this.horseMoveCount = horseMoveCount;
            this.totalMoveCount = totalMoveCount;
        }
    }

    static boolean[][] map;
    static int W, H, K;
    static final int[] horseMoveR = {-1, 1, 2, 2, 1, -1, -2, -2};
    static final int[] horseMoveC = {2, 2, 1, -1, -2, -2, -1, 1};
    static final int[] moveR = {1, 0, -1, 0};
    static final int[] moveC = {0, 1, 0, -1};
    static boolean[][][] visited;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        String[] inputStrings = br.readLine().split(" ");
        W = Integer.parseInt(inputStrings[0]);
        H = Integer.parseInt(inputStrings[1]);

        map = new boolean[H][W];
        visited = new boolean[H][W][K+1];
        for(int i = 0; i<H; i++) {
            inputStrings = br.readLine().split(" ");
            for(int j = 0; j<W; j++) {
                map[i][j] = Integer.parseInt(inputStrings[j])==1?true:false;
            }
        }
        br.close();
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=H || c>=W)
            return false;
        if(map[r][c])
            return false;
        return true;
    }

    static int BFS() {
        int ans = Integer.MAX_VALUE;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0, 0));
        visited[0][0][0] = true;
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            if(curNode.r==H-1 && curNode.c==W-1) {
                ans = Math.min(ans, curNode.totalMoveCount);
                continue;
            }
            if(curNode.horseMoveCount<K) {
                for(int i = 0; i<8; i++) {
                    int tmpR = curNode.r + horseMoveR[i];
                    int tmpC = curNode.c + horseMoveC[i];
                    if(isValid(tmpR,tmpC) && !visited[tmpR][tmpC][curNode.horseMoveCount+1]) {
                        q.add(new Node(tmpR, tmpC, curNode.horseMoveCount+1, curNode.totalMoveCount+1));
                        visited[tmpR][tmpC][curNode.horseMoveCount+1] = true;
                    }
                }
            }
            for(int i = 0; i<4; i++) {
                int tmpR = curNode.r + moveR[i];
                int tmpC = curNode.c + moveC[i];
                if(isValid(tmpR,tmpC) && !visited[tmpR][tmpC][curNode.horseMoveCount]) {
                    q.add(new Node(tmpR, tmpC, curNode.horseMoveCount, curNode.totalMoveCount+1));
                    visited[tmpR][tmpC][curNode.horseMoveCount] = true;
                }
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}