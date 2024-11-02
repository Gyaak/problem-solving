//package BOJ11048;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int[][] maze;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(countCandy());
    }

    private static int countCandy() {
        int[][] cache = new int[N][M];
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                cache[i][j] = maze[i][j];
                if(i>0) {
                    cache[i][j] = Math.max(cache[i][j], cache[i-1][j] + maze[i][j]);
                }
                if(j>0) {
                    cache[i][j] = Math.max(cache[i][j], cache[i][j-1] + maze[i][j]);
                }
                if(i>0 && j>0) {
                    cache[i][j] = Math.max(cache[i][j], cache[i-1][j-1] + maze[i][j]);
                }
            }
        }
        return cache[N-1][M-1];
    }
}
