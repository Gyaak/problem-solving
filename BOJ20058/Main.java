package BOJ20058;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c;
        Node(int r, int c) {
            this.r = r;
            this.c =c;
        }
    }

    static int N, Q;
    static int[] dr = {0,1,0,-1}, dc = {1,0,-1,0};
    static int[] L;
    static int[][] A, tmpA;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = 1 << Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        tmpA = new int[N][N];
        L = new int[Q];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void rotate(int r, int c, int l) {
        if(l<2) {
            return;
        }
        Queue<Integer> q = new ArrayDeque<>();
        int curR = r, curC = c;
        for(int dirc = 0; dirc<4; dirc++) {
            for(int i = 0; i<l-1; i++) {
                q.add(A[curR][curC]);
                curR += dr[dirc];
                curC += dc[dirc];
            }
        }
        curR = r;
        curC = c + l - 1;
        for(int dirc = 1; dirc<5; dirc++) {
            for(int i = 0; i<l-1; i++) {
                A[curR][curC] = q.poll();
                curR += dr[dirc%4];
                curC += dc[dirc%4];
            }
        }
        rotate(r+1, c+1, l-2);
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N) {
            return false;
        }
        return true;
    }

    static void melt() {
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                int cnt = 0;
                for(int d = 0; d<4; d++) {
                    int tmpR = i + dr[d];
                    int tmpC = j + dc[d];
                    if(isValid(tmpR, tmpC) && A[tmpR][tmpC]>0) {
                        cnt++;
                    }
                }
                if(cnt<3) {
                    tmpA[i][j] = -1;
                }
            }
        }
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                A[i][j] += tmpA[i][j];
                A[i][j] = Math.max(A[i][j], 0);
            }
        }
        for(int i = 0; i<N; i++) {
            Arrays.fill(tmpA[i], 0);
        }
    }

    static void fireStorm() {
        for(int l : L) {
            int len = 1 << l;
            for(int i = 0; i<N; i+=len) {
                for(int j = 0; j<N; j+=len) {
                    rotate(i, j, len);
                }
            }
            melt();
        }
    }

    static int countIce() {
        int cnt = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                cnt += A[i][j];
            }
        }
        return cnt;
    }

    static int BFS(int r, int c, boolean[][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visited[r][c] = true;
        int cnt = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            cnt++;
            for(int i = 0; i<4; i++) {
                int tmpR = cur.r + dr[i];
                int tmpC = cur.c + dc[i];
                if(isValid(tmpR, tmpC) && !visited[tmpR][tmpC] && A[tmpR][tmpC]>0) {
                    q.add(new Node(tmpR, tmpC));
                    visited[tmpR][tmpC] = true;
                }
            }
        }
        return cnt;
    }

    static int findMaxArea() {
        boolean[][] visited = new boolean[N][N];
        int maxArea = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if(A[i][j]>0 && !visited[i][j]) {
                    maxArea = Math.max(maxArea, BFS(i, j, visited));
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        fireStorm();
        System.out.println(countIce());
        System.out.println(findMaxArea());
    }
}