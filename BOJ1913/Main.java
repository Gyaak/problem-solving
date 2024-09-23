package BOJ1913;

import java.io.*;

public class Main {

    static int N, target;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] arr;
    static int ansR, ansC;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        arr[N/2][N/2] = 1;
    }

    static void solve() {
        int len = N-1;
        int dirc = 0;
        int cur = N*N;
        int curR = 0, curC = 0;
        ansR = N/2+1;
        ansC = N/2+1;
        while(len>0) {
            for(int i = 0; i<len; i++) {
                if(cur==0) break;
                if(cur==target) {
                    ansR = curR + 1;
                    ansC = curC + 1;
                }
                arr[curR][curC] = cur;
                curR += dr[dirc];
                curC += dc[dirc];
                cur--;
            }
            if(dirc==3) {
                curR -= dr[dirc];
                curC -= dc[dirc];
                dirc = 0;
                curR += dr[dirc];
                curC += dc[dirc];
                len -= 2;
            } else {
                dirc++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        solve();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                sb.append(arr[i][j]);
                if(j<N-1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append(ansR + " " + ansC);
        System.out.println(sb);
    }
}