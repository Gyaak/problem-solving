package BOJ21610;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dr = {0,-1,-1,-1,0,1,1,1}, dc = {-1,-1,0,1,1,1,0,-1};
    static int[][] A, query;
    static boolean[][] cloud;
    static Queue<Integer> q1, q2;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        query = new int[M][2];
        cloud = new boolean[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            query[i][0] = Integer.parseInt(st.nextToken()) - 1;
            query[i][1] = Integer.parseInt(st.nextToken());
        }
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        q1.add(100 * (N-1));
        q1.add(100 * (N-1) + 1);
        q1.add(100 * (N-2));
        q1.add(100 * (N-2) + 1);
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N) {
            return false;
        }
        return true;
    }

    static int countDiagonalBucket(int r, int c) {
        int cnt = 0;
        for(int i = 0; i<4; i++) {
            int tmpR = r + dr[2 * i + 1];
            int tmpC = c + dc[2 * i + 1];
            if(isValid(tmpR, tmpC) && A[tmpR][tmpC]>0) {
                cnt++;
            }
        }
        return cnt;
    }

    static void moveCloud(int d, int s) {
        // 이동 하고 이동한 칸의 물 + 1
        while(!q1.isEmpty()) {
            int r = (q1.peek() / 100 + s * dr[d] + 50 * N) % N;
            int c = (q1.poll() % 100 + s * dc[d] + 50 * N) % N;
            q2.add(100 * r + c);
            cloud[r][c] = true;
            A[r][c]++;
        }
        // 구름이 있던 칸의 대각칸들을 조사해서 물 복사 시전
        while(!q2.isEmpty()) {
            int r = q2.peek() / 100;
            int c = q2.poll() % 100;
            A[r][c] += countDiagonalBucket(r, c);
        }
    }

    static void generateCloud() {
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if(!cloud[i][j] && A[i][j] >= 2) {
                    A[i][j] -= 2;
                    q1.add(100 * i + j);
                }
                if(cloud[i][j]) {
                    cloud[i][j] = false;
                }
            }
        }
    }

    static int countTotalWater() {
        int cnt = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                cnt += A[i][j];
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(int[] q : query) {
            moveCloud(q[0], q[1]);
            generateCloud();
        }
        System.out.println(countTotalWater());
    }

}
