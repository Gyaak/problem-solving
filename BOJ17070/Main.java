package BOJ17070;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][][] cache;
    static boolean[][] house;
    
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N][N][3];
        for(int i = 0; i<N; i++)
            for(int j = 2; j<N; j++) {
                cache[i][j][0] = -1;
                cache[i][j][1] = -1;
                cache[i][j][2] = -1;
            }
        cache[0][1][0] = 1;
        house = new boolean[N][N];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                if(Integer.parseInt(st.nextToken())==1)
                    house[i][j] = true;
            }
        }
    }

    static boolean isValid(int r, int c, int dirc) {
        if(dirc==0) {
            if(c<1 || house[r][c-1])
                return false;
        }
        if(dirc==1) {
            if(r<1 || house[r-1][c])
                return false;
        }
        if(dirc==2) {
            if(r<1 || c<1 || (house[r][c-1]||house[r-1][c]||house[r-1][c-1]))
                return false;
        }
        return true;
    }

    static int Cache(int r, int c, int dirc) {
        if(!isValid(r, c, dirc))  return 0;
        if(cache[r][c][dirc]==-1) {
            cache[r][c][dirc] = 0;
            if(dirc==0) {
                cache[r][c][dirc] += Cache(r, c-1, 0);
                cache[r][c][dirc] += Cache(r, c-1, 2);
            } else if(dirc==1) {
                cache[r][c][dirc] += Cache(r-1, c, 1);
                cache[r][c][dirc] += Cache(r-1, c, 2);
            } else if(dirc==2) {
                cache[r][c][dirc] += Cache(r-1, c-1, 0);
                cache[r][c][dirc] += Cache(r-1, c-1, 1);
                cache[r][c][dirc] += Cache(r-1, c-1, 2);
            }
        }
        return cache[r][c][dirc];
    }

    public static void main(String[] args) throws IOException{
        setInput();
        int ans = house[N-1][N-1]?0:Cache(N-1, N-1, 0)+Cache(N-1, N-1, 1)+Cache(N-1, N-1, 2);
        System.out.println(ans);
    }
}
