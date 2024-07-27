package BOJ4179;

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
    static int R, C;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    // 0 : 빈공간, 1 : 벽, 2 : 불
    static int maze[][];
    static Deque<Node> q;
    
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maze = new int[R][C];
        q = new ArrayDeque<>();
        
        for(int i = 0; i<R; i++) {
            String s = br.readLine();
            for(int j = 0; j<C; j++) {
                switch (s.charAt(j)) {
                    case '#':
                        maze[i][j] = 1;
                        break;
                    case 'J':
                        q.addLast(new Node(i, j, 1));
                        break;
                    case 'F':
                        maze[i][j] = 2;
                        q.addFirst(new Node(i, j, -1));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        // 미로 밖으로 나가면 false
        if(r<0 || c<0 || r>=R || c>=C) {
            return false;
        }
        // 빈공간이 아니면 false
        if(maze[r][c]!=0) {
            return false;
        }
        return true;
    }

    static boolean isEscaped(int r , int c) {
        if(r==0 || c==0 || r==R-1 || c==C-1) {
            return true;
        }
        return false;
    }

    static int BFS() {
        boolean[][] visited = new boolean[R][C];
        visited[q.peekLast().r][q.peekLast().c] = true;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.d!=-1 && isEscaped(cur.r, cur.c)) {
                return cur.d;
            }
            for(int i = 0; i<4 ; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[i];
                if(isValid(tmpR, tmpC)) {
                    if(cur.d==-1) {
                        q.add(new Node(tmpR, tmpC, cur.d));
                        maze[tmpR][tmpC] = 2;
                    } else if(!visited[tmpR][tmpC]) {
                        q.add(new Node(tmpR, tmpC, cur.d+1));
                        visited[tmpR][tmpC] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = BFS();
        System.out.println(ans==-1?"IMPOSSIBLE":ans);
    }
}
