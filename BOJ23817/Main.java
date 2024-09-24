package BOJ23817;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c, move, visited, num;

        Node(int r, int c, int move, int visited, int num) {
            this.r = r;
            this.c = c;
            this.move = move;
            this.visited = visited;
            this.num = num;
        }
    }

    static class Visited{
        int r, c, restaurant;

        Visited(int r, int c, int restaurant) {
            this.r = r;
            this.c = c;
            this.restaurant = restaurant;
        }

        @Override
        public boolean equals(Object obj) {
            Visited v = (Visited)obj;
            if(this.r==v.r && this.c==v.c && this.restaurant==v.restaurant)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r,this.c,this.restaurant);
        }
    }

    static int N, M, rNum;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] map;
    static Set<Visited> visitedSet = new HashSet<>();

    static Node setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        Node start = null;
        rNum = 1;
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++) {
                if(input.charAt(j)=='K') {
                    map[i][j] = rNum++;
                } else if(input.charAt(j)=='X') {
                    map[i][j] = -1;
                } else if (input.charAt(j)=='S') {
                    start = new Node(i, j, 0, 0, 0);
                }
            }
        }
        return start;
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        if(map[r][c]==-1)
            return false;
        return true;
    }

    static int BFS(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(start);
        visitedSet.add(new Visited(start.r, start.c, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0; i<4; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[i];
                int tmpVisited = cur.visited;
                int tmpNum = cur.num;
                if(!isValid(tmpR, tmpC))    continue;
                if(map[tmpR][tmpC]>0 && (tmpVisited&(1<<map[tmpR][tmpC]))==0) {
                    tmpVisited |= 1<<map[tmpR][tmpC];
                    tmpNum++;
                }
                if(tmpNum==5) {
                    return cur.move+1;
                }
                Visited v = new Visited(tmpR, tmpC, tmpVisited);
                if(!visitedSet.contains(v)) {
                    q.add(new Node(tmpR, tmpC, cur.move+1, tmpVisited, tmpNum));
                    visitedSet.add(v);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(BFS(setInput()));
    }
}
