package BOJ31715;

import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;    
    static int N, M;
    static int[][] classroom;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        classroom = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                classroom[i][j] = Integer.parseInt(st.nextToken());
                classroom[i][j] += D * i;
            }
            Arrays.sort(classroom[i]);
        }
    }

    static long solve() {
        long num = 1;
        for(int i = 1; i<=M; i++)
            num = (num*i)%MOD;
        for(int i = 1; i<N; i++) {
            long tmp = 1;
            int idx = 0;
            for(int j = 0; j<M; j++) {
                while(idx<M && classroom[i-1][idx]<classroom[i][j])
                    idx++;
                tmp = (tmp*(idx-j))%MOD;
            }
            if(tmp==0)  return 0;
            else        num = (num*tmp)%MOD;
        }
        return num;
    }
    
    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
