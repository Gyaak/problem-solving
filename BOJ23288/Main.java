package BOJ23288;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] dr = {0,1,0,-1}, dc = {1,0,-1,0};
    static int[][] map, score;

    static class Dice {
        int t, b, n, s, w, e;
        int curD; // 현재 방향
        int curR, curC; // 현재 위치
        Dice() {
            t = 1;
            b = 6;
            n = 2;
            s = 5;
            w = 4;
            e = 3;
            curD = 0;
            curR = 0;
            curC = 0;
        }

        void rotate(int dirc) {
            int tmp = t;
            if(dirc==0) {
                t = w;
                w = b;
                b = e;
                e = tmp;
            } else if(dirc==1) {
                t = n;
                n = b;
                b = s;
                s = tmp;
            } else if(dirc==2) {
                t = e;
                e = b;
                b = w;
                w = tmp;
            } else if(dirc==3) {
                t = s;
                s = b;
                b = n;
                n = tmp;
            }
        }

        int move() {
            int tmpR = curR + dr[curD];
            int tmpC = curC + dc[curD];
            if(isValid(tmpR, tmpC)) {
                rotate(curD);
                curR = tmpR;
                curC = tmpC;
                if(map[curR][curC]<b)      curD = (curD+1)%4; // 90도 시계 회전
                else if(map[curR][curC]>b) curD = (curD+3)%4; // 90도 반시계 회전
                return score[curR][curC];
            } else {
                curD = (curD+2)%4; // 방향 반대로
                return move();
            }
        }
    }
    
    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=M)
            return false;
        return true;
    }

    static void BFS() {
        boolean[][] visited = new boolean[N][M];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(!visited[i][j]) {
                    List<Integer> list = new ArrayList<>();
                    int s = 1;
                    int n = map[i][j];
                    q.add(100*i+j);
                    list.add(100*i+j);
                    visited[i][j] = true;
                    while(!q.isEmpty()) {
                        int curR = q.peek()/100;
                        int curC = q.poll()%100;
                        for(int k = 0; k<4; k++) {
                            int tmpR = curR + dr[k];
                            int tmpC = curC + dc[k];
                            if(isValid(tmpR, tmpC) && !visited[tmpR][tmpC] && map[tmpR][tmpC]==n) {
                                q.add(100*tmpR+tmpC);
                                list.add(100*tmpR+tmpC);
                                visited[tmpR][tmpC] = true;
                                s++;
                            } 
                        }
                    }
                    for(int l : list) {
                        int curR = l/100;
                        int curC = l%100;
                        score[curR][curC] = map[curR][curC]*s;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        score = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        BFS();
        Dice dice = new Dice();
        int answer = 0;
        for(int i = 0; i<K; i++) {
            answer += dice.move();
        }
        System.out.println(answer);
    }
}
