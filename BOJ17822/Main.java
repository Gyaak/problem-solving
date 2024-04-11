package BOJ17822;

import java.io.*;
import java.util.*;

public class Main {

    static final int EMPTY = -1;
    static int N, M, T;
    static int[][] disk, tmpDisk;

    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        disk = new int[N][M];
        tmpDisk = new int[N][M];
        
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                disk[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void Rotate(int x, int d, int k) {
        if(d==1) k = M - k;
        int[] tmp = new int[M];
        for(int i = 0; i<M; i++)
            tmp[(i+k)%M] = disk[x][i];
        for(int i = 0; i<M; i++)
            disk[x][i] = tmp[i];
    }

    static void calc() {

        boolean changed = false;

        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if(disk[i][j]==EMPTY) {
                    tmpDisk[i][j] = EMPTY;
                    continue;
                }
                boolean isSame = false;
                if(i!=0 && disk[i][j]==disk[(i+N-1)%N][j])
                    isSame = true;
                if(i!=N-1 && disk[i][j]==disk[(i+1)%N][j])
                    isSame = true;
                if(disk[i][j]==disk[i][(j+M-1)%M])
                    isSame = true;
                if(disk[i][j]==disk[i][(j+1)%M])
                    isSame = true;
                tmpDisk[i][j] = isSame?EMPTY:disk[i][j];
                changed |= isSame;
            }
        }

        for(int i = 0; i<N; i++)
            disk[i] = Arrays.copyOf(tmpDisk[i], M);
        
        if(!changed) {
            int avg = 0;
            int num = 0;
            for(int i = 0; i<N; i++)
                for(int j = 0; j<M; j++)
                    if(disk[i][j]!=EMPTY) {
                        avg += disk[i][j];
                        num++;
                    }
                    
            for(int i = 0; i<N; i++)
                for(int j = 0; j<M; j++) {
                    if(disk[i][j]==EMPTY)
                        continue;
                    if(avg>disk[i][j]*num)
                        disk[i][j]++;
                    else if(avg<disk[i][j]*num)
                        disk[i][j]--;
                }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        setInput(br);
        for(int idx = 0; idx<T; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for(int i = x-1; i<N; i+=x)
                Rotate(i, d, k);
            calc();
        }

        int sum = 0;
        for(int i = 0; i<N; i++)
            for(int j = 0; j<M; j++)
                if(disk[i][j]!=EMPTY)
                    sum += disk[i][j];
        System.out.println(sum);
    }
}
